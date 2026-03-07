package com.fermer.dataa.remote.model

import android.graphics.pdf.LoadParams
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fermer.domain.model.Movie
import com.fermer.domain.usecase.GetTrendingMoviesUseCase
import com.fermer.domain.usecase.SearchMoviesUseCase

class MoviePagingSource(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val result = if (query.isBlank()) {
                getTrendingMoviesUseCase(page)
            } else {
                searchMoviesUseCase(query, page)
            }

            var movies = emptyList<Movie>()
            result.onSuccess { movies = it }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}