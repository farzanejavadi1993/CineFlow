package com.fermer.trending.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.domain.usecase.GetTrendingMoviesUseCase
import com.fermer.trending.TrendingIntent
import com.fermer.trending.state.TrendingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TrendingState())
    val state: StateFlow<TrendingState> = _state.asStateFlow()

    fun onIntent(intent: TrendingIntent) {
        when (intent) {
            is TrendingIntent.LoadTrendingMovies -> loadTrendingMovies()
        }
    }

    private fun loadTrendingMovies() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getTrendingMoviesUseCase()
                .onSuccess { movies ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        movies = movies,
                        error = null
                    )
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message ?: "An error occurred"
                    )
                }
        }
    }
}