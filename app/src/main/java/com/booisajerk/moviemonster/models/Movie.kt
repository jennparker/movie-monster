package com.booisajerk.moviemonster.models

data class Movie(val title: String, val overview: String, val genre_ids: List<Int>, val poster_path: String)
