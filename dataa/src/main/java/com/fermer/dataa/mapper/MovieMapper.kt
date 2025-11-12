package com.fermer.dataa.mapper

import com.fermer.dataa.local.entity.MovieEntity
import com.fermer.dataa.remote.model.MovieDto
import com.fermer.domain.model.Movie

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

fun MovieEntity.toMovie(): Movie = Movie(
    id = id,
    title = title,
    posterUrl = posterUrl,
    overview = overview,
    rating = rating,
    releaseDate = releaseDate
)

fun Movie.toEntity(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    posterUrl = posterUrl,
    overview = overview,
    rating = rating,
    releaseDate = releaseDate
)