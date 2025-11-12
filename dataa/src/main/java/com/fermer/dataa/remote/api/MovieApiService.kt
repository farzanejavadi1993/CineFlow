package com.fermer.dataa.remote.api

import com.fermer.dataa.remote.model.MovieDto
import com.fermer.dataa.remote.model.MovieResponseDto
import com.fermer.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "59cd6896d8432f9c69aed9b86b9c2931",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponseDto

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int ,
        @Query("api_key") api_key: String = "59cd6896d8432f9c69aed9b86b9c2931"
    ): MovieDto


}