package com.fermer.domain.usecase

import com.fermer.domain.model.Movie
import com.fermer.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow

class GetWatchlistUseCase(
    private val repository: WatchlistRepository
) {
    operator fun invoke(): Flow<List<Movie>> = repository.getWatchlist()
}