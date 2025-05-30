package com.fermer.movie.presentation.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fermer.model.Movie
import com.fermer.movie.R
import com.fermer.movie.presentation.state.MovieDetailViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    movie: Movie,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val isInWatchlist by viewModel.isSaved.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkWatchlist(movie.id)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.movie_detail_title)) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            MovieDetailContent(
                modifier = Modifier.padding(innerPadding),
                movie = movie,
                isInWatchlist = isInWatchlist,
                onToggleWatchlist = { viewModel.toggleWatchlist(movie) }
            )
        }
    }
}
