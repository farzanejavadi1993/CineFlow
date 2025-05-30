package com.fermer.trending.presentation.trendinglist

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fermer.trending.presentation.state.TrendingIntent
import com.fermer.trending.presentation.state.TrendingViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.fermer.trending.R

@Composable
fun TrendingScreen(
    navController: NavController,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    val json = Json { ignoreUnknownKeys = true }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(TrendingIntent.LoadTrendingMovies)
    }
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.movies.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.trending_movie_error_message),textAlign = TextAlign.Center)
            }
        }

        else -> {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(state.movies) { movie ->
                    TrendingItem(
                        movie = movie,
                        onClick = {
                            val jsonMovieArg = Uri.encode(json.encodeToString(movie))
                            navController.navigate("movie_detail/$jsonMovieArg")
                        }
                    )
                }
            }
        }
    }
}
