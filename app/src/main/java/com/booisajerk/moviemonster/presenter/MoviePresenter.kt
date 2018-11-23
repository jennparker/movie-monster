package com.booisajerk.moviemonster.presenter

import android.annotation.SuppressLint
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.booisajerk.moviemonster.VolleySingleton
import com.booisajerk.moviemonster.models.Movie
import com.booisajerk.moviemonster.models.MoviesListResponse
import com.booisajerk.moviemonster.shared.App
import com.booisajerk.moviemonster.shared.Constants
import com.booisajerk.moviemonster.view.interfaces.MoviesView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@SuppressLint("Registered")
class MoviePresenter : App() {

    private var moviesView: MoviesView? = null
    private var resultMovieList: ArrayList<Movie> = ArrayList()
    private lateinit var gson: Gson

    fun onViewCreated(view: MoviesView) {
        moviesView = view
    }

    fun getMovies() {
        val singleton = VolleySingleton.getInstance(App.instance)

        // Create the request and handle the response.
        val movieRequest = StringRequest(
            Request.Method.GET,
            getMovieUrl(),
            Response.Listener<String> { response ->
                gson = GsonBuilder().create()
                val moviesList: MoviesListResponse = gson.fromJson(response.toString(), MoviesListResponse::class.java)

                if (moviesList.totalResults > 0) {
                    resultMovieList.addAll(moviesList.movies)
                }
                moviesView?.onMoviesLoaded(resultMovieList)
            },

            Response.ErrorListener { error ->
                // Handle error
                error.printStackTrace()
                moviesView?.onError(error)

            })

        // Add the request to the VolleyController
        singleton.requestQueue.add(movieRequest)
    }

    private fun getMovieUrl(): String {
        return Constants.MOVIE_BASE_URL + Constants.MOVIE_SORT_ORDER + Constants.MOVIE_API_KEY
    }
}