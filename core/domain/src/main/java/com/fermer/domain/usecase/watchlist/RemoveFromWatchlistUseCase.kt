package com.fermer.domain.usecase.watchlist

import com.fermer.model.Movie

class RemoveFromWatchlistUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(movie: Movie) = repository.removeFromWatchlist(movie)
}