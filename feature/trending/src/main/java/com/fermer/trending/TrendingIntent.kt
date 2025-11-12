package com.fermer.trending

sealed interface TrendingIntent {
    data object LoadTrendingMovies : TrendingIntent
}