package com.booisajerk.moviemonster.view.adapters

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.booisajerk.moviemonster.R
import com.booisajerk.moviemonster.models.Movie
import com.booisajerk.moviemonster.shared.Constants
import com.booisajerk.moviemonster.shared.inflate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieListAdapter : Adapter<MovieListAdapter.MovieViewHolder>() {

    private var dataSource: List<Movie>? = null
    //  private var movies: List<Movie>? = null

    fun setDataSource(dataSource: List<Movie>) {
       // Log.d(Constants.TAG, "setDataSource called")
        this.dataSource = dataSource
        notifyDataSetChanged()
    }

    fun clearDataSource() {
        dataSource = null
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = dataSource!![position]
        holder.bind(movieItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(parent.inflate(R.layout.row_movie, false))
    }

    class MovieViewHolder internal constructor(itemView: View) : ViewHolder(itemView) {

        private var movie: Movie? = null


        private val titleTextView: TextView? by lazy {
            itemView.findViewById(R.id.movie_title) as TextView?
        }

        private val overviewTextView: TextView? by lazy {
            itemView.findViewById(R.id.movie_overview) as TextView?
        }

        private val genreTextView: TextView? by lazy {
            itemView.findViewById(R.id.movie_genre) as TextView?
        }

        private val posterImageView: ImageView? by lazy {
            itemView.findViewById(R.id.movie_image) as ImageView?
        }

        fun bind(movie: Movie) {
            this.movie = movie

            titleTextView!!.text = movie.title
            overviewTextView!!.text = movie.overview
            genreTextView!!.text = getGenreStrings(movie)
            Glide.with(itemView.context)
                .load(Constants.IMAGE_BASE_URL + movie.poster_path)
                .apply(RequestOptions.placeholderOf(R.drawable.monster_mask))
                .into(posterImageView!!)
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
