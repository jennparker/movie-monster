package com.booisajerk.moviemonster.shared

class Constants {
    companion object {
        const val MOVIE_BASE_URL: String = "https://api.themoviedb.org/3/discover/movie?"
        const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500"
        const val MOVIE_SORT_ORDER: String = "popularity.desc/popular"
        const val MOVIE_API_KEY: String = "&api_key=INSERT KEY HERE"
    }
}