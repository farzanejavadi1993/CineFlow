package com.fermer.domain.repository

import com.fermer.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {
    fun getWatchlist(): Flow<List<Movie>>

    suspend fun addToWatchlist(movie: Movie)

    suspend fun removeFromWatchlist(movie: Movie)

    suspend fun isInWatchlist(id: Int): Boolean


}