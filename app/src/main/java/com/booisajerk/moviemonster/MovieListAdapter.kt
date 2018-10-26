package com.booisajerk.moviemonster

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieListAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false))
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.movieTitle)
        private var overview: TextView = itemView.findViewById(R.id.movieOverview)
        private var genres: TextView = itemView.findViewById(R.id.movieGenre)
        private var poster: ImageView = itemView.findViewById(R.id.movieImage)

        fun bind(movie: Movie) {
            title.text = movie.title
            overview.text = movie.overview
            genres.text = getGenreStrings(movie)
            Glide.with(itemView)
                .load(Constants.IMAGE_BASE_URL + movie.poster_path)
                .apply(RequestOptions.placeholderOf(R.drawable.monster_mask))
                .into(poster)
        }

        private fun getGenreStrings(movie: Movie): String {

            return when (movie.genre_ids[0]) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                99 -> "Documentary"
                18 -> "Drama"
                10751 -> "Family"
                14 -> "Fantasy"
                36 -> "History"
                27 -> "Horror"
                10402 -> "Music"
                9648 -> "Mystery"
                10749 -> "Romance"
                878 -> "Science Fiction"
                10770 -> "TV Movie"
                53 -> "Thriller"
                10752 -> "War"
                37 -> "Western"
                else -> "Unknown Genre"
            }
        }
    }
}
