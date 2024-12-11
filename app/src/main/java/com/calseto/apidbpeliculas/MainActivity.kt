package com.calseto.apidbpeliculas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calseto.apidbpeliculas.model.Movie
import com.calseto.apidbpeliculas.model.MovieApiService
import com.calseto.apidbpeliculas.view.InfoPelisActivity
import com.calseto.apidbpeliculas.view.MovieAdapter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        movieAdapter = MovieAdapter(movieList) { movie ->
            openDetailActivity(movie)
        }
        recyclerView.adapter = movieAdapter

        fetchMovies()
    }

    private fun fetchMovies() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(MovieApiService::class.java)
        lifecycleScope.launch {
            val respuesta = api.getPopularMovies("1d3d9a40d262c7e72f286d168886de03")
            movieList.addAll(respuesta.results.take(10))
            movieAdapter.notifyDataSetChanged()

        }
    }



    private fun openDetailActivity(movie: Movie) {
        val intent = Intent(this, InfoPelisActivity::class.java).apply {
            putExtra("MOVIE_TITLE", movie.title)
            putExtra("MOVIE_OVERVIEW", movie.overview)
            putExtra("MOVIE_IMAGE", movie.backdropPath)
        }
        startActivity(intent)
    }
}
