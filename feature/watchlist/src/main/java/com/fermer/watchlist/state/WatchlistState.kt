package com.fermer.watchlist.state

import com.fermer.domain.model.Movie

data class WatchlistState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)