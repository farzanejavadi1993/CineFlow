package com.fermer.dataa.local.dao

import androidx.room.*
import com.fermer.dataa.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM watchlist")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM watchlist WHERE id = :id)")
    suspend fun isInWatchlist(id: Int): Boolean
}
