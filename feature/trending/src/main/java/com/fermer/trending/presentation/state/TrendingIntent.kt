package com.fermer.trending.presentation.state

sealed interface TrendingIntent {
    data object LoadTrendingMovies : TrendingIntent
}