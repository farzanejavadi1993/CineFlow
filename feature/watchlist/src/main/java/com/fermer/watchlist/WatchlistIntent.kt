package com.fermer.watchlist

sealed interface WatchlistIntent {
    data object LoadWatchlist : WatchlistIntent
    data class RemoveMovie(val movieId: Int) : WatchlistIntent
}