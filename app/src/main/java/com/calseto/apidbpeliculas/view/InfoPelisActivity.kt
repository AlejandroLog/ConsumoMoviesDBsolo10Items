package com.calseto.apidbpeliculas.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.calseto.apidbpeliculas.R


class InfoPelisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_pelis)

        // Obtén los datos pasados desde el Intent
        val title = intent.getStringExtra("MOVIE_TITLE")
        val overview = intent.getStringExtra("MOVIE_OVERVIEW")
        val imagePath = intent.getStringExtra("MOVIE_IMAGE")


        // Configura las vistas con los datos
        findViewById<TextView>(R.id.titleTextView).text = title
        findViewById<TextView>(R.id.overviewTextView).text = overview


        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$imagePath")
            .into(findViewById(R.id.movieImageView))

    }
}
