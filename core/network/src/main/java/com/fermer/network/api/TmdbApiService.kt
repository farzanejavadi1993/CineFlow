package com.fermer.network.api

import com.fermer.network.model.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String ="59cd6896d8432f9c69aed9b86b9c2931", // کلید واقعی رو بعداً می‌ذاریم
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponseDto
}