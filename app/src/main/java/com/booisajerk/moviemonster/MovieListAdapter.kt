package com.booisajerk.moviemonster

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MovieListAdapter(val movies: List<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500"


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
        var title: TextView = itemView.findViewById(R.id.movieTitle)
        var overview: TextView = itemView.findViewById(R.id.movieOverview)
        var genres: TextView = itemView.findViewById(R.id.movieGenre)
        var poster: ImageView

        init {
            poster = itemView.findViewById(R.id.movieImage)
        }

        fun bind(movie: Movie) {
            title.text = movie.title
            overview.text = movie.overview
            genres.text = ""
            Glide.with(itemView)
                .load(IMAGE_BASE_URL + movie.poster_path)
                .apply(RequestOptions.placeholderOf(R.drawable.monster_mask))
                .into(poster)
        }
    }
}
