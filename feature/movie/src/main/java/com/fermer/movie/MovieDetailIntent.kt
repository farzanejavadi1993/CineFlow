package com.fermer.movie

sealed interface MovieDetailIntent {
    data class LoadMovie(val movieId: Int) : MovieDetailIntent
}