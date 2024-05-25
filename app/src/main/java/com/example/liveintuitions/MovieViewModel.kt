package com.example.liveintuitions

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Movie(val title: String, val overview: String)

interface MovieApi {
    @GET("popular?api_key=YOUR_API_KEY")
    suspend fun getPopularMovies(): MovieResponse
}

data class MovieResponse(val results: List<Movie>)

class MovieViewModel : ViewModel() {
    private val movieApi: MovieApi
    val movies = mutableStateOf<List<Movie>>(emptyList())

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieApi = retrofit.create(MovieApi::class.java)
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = movieApi.getPopularMovies()
                movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}