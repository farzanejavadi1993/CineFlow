package com.fermer.domain.usecase

import com.fermer.domain.model.Movie
import com.fermer.domain.repository.WatchlistRepository

class AddToWatchlistUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(movie: Movie) = repository.addToWatchlist(movie)
}