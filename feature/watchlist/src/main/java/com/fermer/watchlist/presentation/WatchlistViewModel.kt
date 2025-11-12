package com.fermer.watchlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.domain.usecase.GetWatchlistUseCase
import com.fermer.domain.usecase.RemoveFromWatchlistUseCase
import com.fermer.watchlist.WatchlistIntent
import com.fermer.watchlist.state.WatchlistState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.find

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val removeFromWatchlistUseCase: RemoveFromWatchlistUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WatchlistState())
    val state: StateFlow<WatchlistState> = _state.asStateFlow()

    fun onIntent(intent: WatchlistIntent) {
        when (intent) {
            is WatchlistIntent.LoadWatchlist -> loadWatchlist()
            is WatchlistIntent.RemoveMovie -> removeMovieById(intent.movieId)
        }
    }

    private fun loadWatchlist() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getWatchlistUseCase()
                .catch { e ->
                    _state.value = _state.value.copy(isLoading = false, error = e.message)
                }
                .collect { movies ->
                    _state.value = _state.value.copy(isLoading = false, movies = movies)
                }
        }
    }

    private fun removeMovieById(movieId: Int) {
        viewModelScope.launch {
            val movie = _state.value.movies.find { it.id == movieId }
            movie?.let {
                removeFromWatchlistUseCase(it)
            }
        }
    }
}