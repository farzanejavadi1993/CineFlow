package com.fermer.watchlist.state

import com.fermer.domain.model.Movie

data class WatchlistState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)

sealed interface WatchlistIntent {
    data object LoadWatchlist : WatchlistIntent
    data class RemoveMovie(val movieId: Int) : WatchlistIntent
}