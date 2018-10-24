package com.booisajerk.moviemonster

object MovieData {

    // TODO remove test data
    var movieNameArray = arrayOf("Star Wars", "Raiders of the Lost Arc", "Golden Eye", "Toy Story")
    var movieLengthArray = arrayOf("123","90","133","41")
    var movieGenreArray = arrayOf("Action", "Action", "Drama", "Animation")
    var movieOverviewArray = arrayOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et fugiat nulla pariaum.")


    fun movieList(): ArrayList<Movie> {
        val list = ArrayList<Movie>()
        for (i in movieNameArray.indices) {
           val movie = Movie(movieNameArray[i], movieLengthArray[i], movieGenreArray[i], movieOverviewArray[0])

            list.add(movie)
        }
        return list
    }
}