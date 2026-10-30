package com.sang.rapcuasang.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sang.rapcuasang.data.api.ApiService
import com.sang.rapcuasang.data.api.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val apiService = ApiService.create()

    private val _recentMovies = MutableStateFlow<List<Movie>>(emptyList())
    val recentMovies: StateFlow<List<Movie>> = _recentMovies

    private val _singleMovies = MutableStateFlow<List<Movie>>(emptyList())
    val singleMovies: StateFlow<List<Movie>> = _singleMovies

    private val _seriesMovies = MutableStateFlow<List<Movie>>(emptyList())
    val seriesMovies: StateFlow<List<Movie>> = _seriesMovies

    private val _cinemaMovies = MutableStateFlow<List<Movie>>(emptyList())
    val cinemaMovies: StateFlow<List<Movie>> = _cinemaMovies

    private val _animationMovies = MutableStateFlow<List<Movie>>(emptyList())
    val animationMovies: StateFlow<List<Movie>> = _animationMovies

    init {
        fetchAllCategories()
    }

    private fun fetchAllCategories() {
        viewModelScope.launch {
            try {
                val recent = apiService.getMoviesByType("phim-moi-cap-nhat", 1)
                if (recent.status) _recentMovies.value = recent.items

                val single = apiService.getMoviesByType("phim-le", 1)
                if (single.status) _singleMovies.value = single.items

                val series = apiService.getMoviesByType("phim-bo", 1)
                if (series.status) _seriesMovies.value = series.items

                val cinema = apiService.getMoviesByType("phim-chieu-rap", 1)
                if (cinema.status) _cinemaMovies.value = cinema.items

                val animation = apiService.getMoviesByType("hoat-hinh", 1)
                if (animation.status) _animationMovies.value = animation.items
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
