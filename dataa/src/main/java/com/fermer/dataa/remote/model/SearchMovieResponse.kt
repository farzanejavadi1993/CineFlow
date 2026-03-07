package com.fermer.dataa.remote.model

data class SearchMovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)