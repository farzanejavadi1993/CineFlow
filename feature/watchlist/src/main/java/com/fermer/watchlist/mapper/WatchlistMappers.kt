package com.fermer.watchlist.mapper

import com.fermer.database.MovieEntity
import com.fermer.model.Movie

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
