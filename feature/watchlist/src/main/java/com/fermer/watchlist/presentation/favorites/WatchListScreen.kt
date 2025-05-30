package com.fermer.watchlist.presentation.favorites

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fermer.watchlist.presentation.state.WatchlistIntent
import com.fermer.watchlist.presentation.state.WatchlistViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.fermer.watchlist.R

@Composable
fun WatchlistScreen(
    navController: NavController,
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val json = Json { ignoreUnknownKeys = true }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(WatchlistIntent.LoadWatchlist)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.movies.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.watchlist_error_message), textAlign = TextAlign.Center)
        }
    } else {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(state.movies) { movie ->
                WatchlistItem(
                    movie = movie,
                    onClick = {
                        val jsonMovieArg = Uri.encode(json.encodeToString(movie))
                        navController.navigate("movie_detail/$jsonMovieArg")

                    },
                    onRemove = {
                        viewModel.onIntent(WatchlistIntent.RemoveMovie(movie.id))
                    }
                )
            }
        }
    }
}


