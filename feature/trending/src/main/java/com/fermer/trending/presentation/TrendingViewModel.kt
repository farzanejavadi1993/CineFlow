package com.fermer.trending.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fermer.dataa.remote.model.MoviePagingSource
import com.fermer.domain.model.Movie
import com.fermer.domain.usecase.GetTrendingMoviesUseCase
import com.fermer.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject




@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    val moviesPagingFlow: Flow<PagingData<Movie>> = _searchQuery
        .debounce(500L)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    prefetchDistance = 2,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    MoviePagingSource(getTrendingMoviesUseCase, searchMoviesUseCase, query)
                }
            ).flow
        }
        .cachedIn(viewModelScope)

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }



}
