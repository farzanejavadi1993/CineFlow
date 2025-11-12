package com.fermer.trending.state

import com.fermer.domain.model.Movie


data class TrendingState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)