package com.booisajerk.moviemonster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: MovieListAdapter
    lateinit private var gson: Gson
    val resultMovieList: ArrayList<Movie> = ArrayList()


    val MOVIE_API_URI: String = "https://api.themoviedb.org/3/discover/movie?"
    val MOVIE_SORT_ORDER: String = "popularity.desc/popular"
    val MOVIE_API_KEY: String = "&api_key=be309903dd5028b9fd14f39a337ebdfd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        list.layoutManager = staggeredLayoutManager

        createRequestQueue()
    }

    fun createRequestQueue() {

        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        // Formulate the request and handle the response.
        val stringRequest = StringRequest(Request.Method.GET,
            MOVIE_API_URI + MOVIE_SORT_ORDER + MOVIE_API_KEY,
            Listener<String> { response ->

                gson = GsonBuilder().create()
                val moviesList: MoviesList = gson.fromJson(response.toString(), MoviesList::class.java)

                if (moviesList.total_results > 0) {

                    var movieCount: Int = moviesList.results!!.size

                    for (i in 0 until movieCount) {
                        var title = moviesList.results!![i].title
                        var overview = moviesList.results!![i].overview

                        resultMovieList.add(Movie(title, overview))
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
        requestQueue.add(stringRequest)
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
}
