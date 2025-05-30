package com.fermer.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String
)
