package com.booisajerk.moviemonster

class MoviesList {
    var page: Int = 0
    var results: ArrayList<ResultMovie>? = null
    var total_pages: Int = 0
    var total_results: Int = 0

    inner class ResultMovie {
        var id: Int = 0
        var title: String = ""
        var overview: String = ""
    }
}