package com.fermer.domain.usecase.watchlist

import com.fermer.model.Movie
import kotlinx.coroutines.flow.Flow

class GetWatchlistUseCase(
    private val repository: WatchlistRepository
) {
    operator fun invoke(): Flow<List<Movie>> = repository.getWatchlist()
}