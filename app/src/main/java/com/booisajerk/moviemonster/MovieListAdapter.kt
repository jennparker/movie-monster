package com.booisajerk.moviemonster

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieListAdapter(var movieList: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.itemView.movieTitle.text = movie.title
        holder.itemView.movieOverview.text = movie.overview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
