package com.fermer.domain.model


data class Movie(
    val id: Int ,
    val title: String ="",
    val posterUrl: String = "",
    val overview: String ="",
    val rating: Double =0.0,
    val releaseDate: String =""
)
