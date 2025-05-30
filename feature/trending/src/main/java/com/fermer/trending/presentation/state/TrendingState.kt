package com.fermer.trending.presentation.state

import com.fermer.model.Movie

data class TrendingState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)