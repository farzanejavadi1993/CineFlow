package com.fermer.watchlist.presentation.state

sealed interface WatchlistIntent {
    data object LoadWatchlist : WatchlistIntent
    data class RemoveMovie(val movieId: Int) : WatchlistIntent
}