package com.fermer.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.domain.model.Movie
import com.fermer.domain.usecase.AddToWatchlistUseCase
import com.fermer.domain.usecase.GetMovieUseCase
import com.fermer.domain.usecase.IsInWatchlistUseCase
import com.fermer.domain.usecase.RemoveFromWatchlistUseCase
import com.fermer.movie.MovieDetailIntent
import com.fermer.movie.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val addToWatchlist: AddToWatchlistUseCase,
    private val removeFromWatchlist: RemoveFromWatchlistUseCase,
    private val isInWatchlist: IsInWatchlistUseCase,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState(movie = Movie(0)))
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    fun onIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.LoadMovie -> loadMovie(movieId = intent.movieId)
        }
    }

    fun checkWatchlist(id: Int) {
        viewModelScope.launch {
          _state.value=_state.value.copy(isSaved = isInWatchlist(id))
        }
    }

    fun toggleWatchlist(movie: Movie) {
        viewModelScope.launch {
            if (_state.value.isSaved) {
                removeFromWatchlist(movie)
                _state.value=_state.value.copy(isSaved = false)
            } else {
                addToWatchlist(movie)
                _state.value=_state.value.copy(isSaved = true)
            }
        }
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getMovieUseCase(movieId = movieId)
                .onSuccess { movie ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        movie = movie,
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
