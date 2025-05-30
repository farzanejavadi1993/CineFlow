package com.fermer.watchlist.presentation.state

import com.fermer.model.Movie

data class WatchlistState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)