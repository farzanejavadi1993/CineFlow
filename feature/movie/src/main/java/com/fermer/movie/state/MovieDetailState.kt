package com.fermer.movie.state

import com.fermer.domain.model.Movie


data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: Movie ,
    val error: String? = null,
    val isSaved: Boolean = false
)