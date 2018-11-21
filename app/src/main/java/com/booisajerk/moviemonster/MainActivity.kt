package com.booisajerk.moviemonster

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.booisajerk.moviemonster.models.Movie
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : BaseActivity() {

    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        movieRecyclerView = findViewById(android.R.id.list)
        movieRecyclerView.layoutManager = LinearLayoutManager(this)

        requestMovies()

        // Get SwipeContainerLayout
        val swipeLayout: SwipeRefreshLayout = findViewById(R.id.swipe_container)

        // Add Listener
        // TODO finish hooking this up
        swipeLayout.setOnRefreshListener {
            @Override
            fun onRefresh() {
                refreshMovies()
            }

            Handler().postDelayed({
                // Stop animation (after 2 seconds)
                swipeLayout.isRefreshing = false
            }, 2000)
        }
    }

    override fun onStart() {
        super.onStart()
        // If movieList is not populated, request movies
        if (resultMovieList.size == 0) {
            requestMovies()
        }
    }

    private fun requestMovies() {
        try {
            getMovies()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getMovies() {
        // Create the request and handle the response.
        val movieRequest = StringRequest(
            Request.Method.GET,
            getMovieUrl(),
            Response.Listener<String> { response ->
                gson = GsonBuilder().create()
                val moviesList: MoviesListResponse = gson.fromJson(response.toString(), MoviesListResponse::class.java)

                if (moviesList.totalResults > 0) {
                    for (movie in moviesList.movies)
                        resultMovieList.add(movie)
                    movieRecyclerView.adapter = MovieListAdapter(resultMovieList)
                }
            },
            Response.ErrorListener { error ->
                // Handle error
                Toast.makeText(
                    this@MainActivity, "Don't forget to enter your key in Constants.kt? ERROR: %s"
                        .format(error.toString()), Toast.LENGTH_LONG
                ).show()
            })

        // Add the request to the VolleyController
        VolleyController.getInstance(this.applicationContext).addToRequestQueue(movieRequest)
    }

    private fun getMovieUrl(): String {
        return Constants.MOVIE_BASE_URL + Constants.MOVIE_SORT_ORDER + Constants.MOVIE_API_KEY
    }

    private fun refreshMovies() {
        resultMovieList.clear()
        requestMovies()
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