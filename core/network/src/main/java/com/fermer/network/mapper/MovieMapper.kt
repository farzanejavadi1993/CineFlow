package com.fermer.network.mapper

import com.fermer.model.Movie
import com.fermer.network.model.MovieDto

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterPath ?: "",
        overview = overview,
        rating = voteAverage,
        releaseDate = releaseDate
    )
}
