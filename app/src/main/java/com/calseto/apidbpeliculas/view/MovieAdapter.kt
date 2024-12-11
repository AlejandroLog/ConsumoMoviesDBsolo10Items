package com.calseto.apidbpeliculas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.calseto.apidbpeliculas.R
import com.calseto.apidbpeliculas.model.Movie

class MovieAdapter(
    private val movies: List<Movie>, private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onClick)
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
        private val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)

        fun bind(movie: Movie, onClick: (Movie) -> Unit) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .into(movieImageView)

            movieTitleTextView.text = movie.title
            itemView.setOnClickListener { onClick(movie) }
        }
    }
}

