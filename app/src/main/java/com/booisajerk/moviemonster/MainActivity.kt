package com.booisajerk.moviemonster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    private lateinit var adapter: MovieListAdapter
    private lateinit var gson: Gson
    private val resultMovieList: ArrayList<Movie> = ArrayList()

    private val MOVIE_API_URI: String = "https://api.themoviedb.org/3/discover/movie?"
    private val MOVIE_SORT_ORDER: String = "popularity.desc/popular"
    private val MOVIE_API_KEY: String = "&api_key=be309903dd5028b9fd14f39a337ebdfd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        list.layoutManager = staggeredLayoutManager

        createRequestQueue()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun createRequestQueue() {

        // Get the RequestQueue instance
        val queue = MyRequestQueue.getInstance(this.applicationContext).requestQueue

        // Formulate the request and handle the response.
        val stringRequest = StringRequest(Request.Method.GET,
            MOVIE_API_URI + MOVIE_SORT_ORDER + MOVIE_API_KEY,
            Listener<String> { response ->

                gson = GsonBuilder().create()
                val moviesList: MoviesList = gson.fromJson(response.toString(), MoviesList::class.java)

                if (moviesList.totalResults > 0) {

                    var movieCount: Int = moviesList.movies!!.size

                    for (i in 0 until movieCount) {
                        val title = moviesList.movies!![i].title
                        val overview = moviesList.movies!![i].overview
                        val genres: List<Int> = moviesList.movies!![i].genre_ids
                        val poster: String = moviesList.movies!![i].poster_path

                        resultMovieList.add(Movie(title, overview, genres, poster))
                    }

                    adapter = MovieListAdapter(resultMovieList)
                    list.adapter = adapter
                }
            },
            Response.ErrorListener { error ->
                // Handle error
                Toast.makeText(this@MainActivity, "ERROR: %s".format(error.toString()), Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
