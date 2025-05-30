package com.fermer.domain.usecase.watchlist

class IsInWatchlistUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(id: Int): Boolean = repository.isInWatchlist(id)
}