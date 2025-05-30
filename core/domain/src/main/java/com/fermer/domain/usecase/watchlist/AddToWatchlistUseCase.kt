package com.fermer.domain.usecase.watchlist

import com.fermer.model.Movie

class AddToWatchlistUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(movie: Movie) = repository.addToWatchlist(movie)
}