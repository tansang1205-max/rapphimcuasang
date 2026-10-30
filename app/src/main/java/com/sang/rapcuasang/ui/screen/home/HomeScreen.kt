package com.sang.rapcuasang.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sang.rapcuasang.data.api.Movie
import com.sang.rapcuasang.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onMovieClick: (String) -> Unit
) {
    val recentMovies by viewModel.recentMovies.collectAsState()
    val singleMovies by viewModel.singleMovies.collectAsState()
    val seriesMovies by viewModel.seriesMovies.collectAsState()
    val cinemaMovies by viewModel.cinemaMovies.collectAsState()
    val animationMovies by viewModel.animationMovies.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF141414)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            if (recentMovies.isNotEmpty()) {
                item {
                    HeroSection(movie = recentMovies[0], onMovieClick = onMovieClick)
                }
            }

            item { MovieRow(title = "Phim Mới Cập Nhật", movies = recentMovies, onMovieClick = onMovieClick) }
            item { MovieRow(title = "Phim Lẻ Đặc Sắc", movies = singleMovies, onMovieClick = onMovieClick) }
            item { MovieRow(title = "Phim Bộ Hấp Dẫn", movies = seriesMovies, onMovieClick = onMovieClick) }
            item { MovieRow(title = "Phim Chiếu Rạp", movies = cinemaMovies, onMovieClick = onMovieClick) }
            item { MovieRow(title = "Hoạt Hình / Anime", movies = animationMovies, onMovieClick = onMovieClick) }
        }
    }
}

@Composable
fun HeroSection(movie: Movie, onMovieClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .clickable { onMovieClick(movie.slug) }
    ) {
        AsyncImage(
            model = movie.poster_url,
            contentDescription = movie.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MovieRow(title: String, movies: List<Movie>, onMovieClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieCard(movie = movie, onClick = { onMovieClick(movie.slug) })
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            AsyncImage(
                model = movie.thumb_url,
                contentDescription = movie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.name,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
