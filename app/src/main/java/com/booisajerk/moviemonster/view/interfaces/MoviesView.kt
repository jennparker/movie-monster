package com.booisajerk.moviemonster.view.interfaces

import com.booisajerk.moviemonster.models.Movie

interface MoviesView {

    fun onMoviesLoaded(movies: List<Movie>)

    fun onError(throwable: Throwable?)

    fun hideLoading()

}