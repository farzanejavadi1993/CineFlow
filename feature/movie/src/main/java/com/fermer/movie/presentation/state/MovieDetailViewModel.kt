package com.fermer.movie.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.domain.usecase.watchlist.AddToWatchlistUseCase
import com.fermer.domain.usecase.watchlist.IsInWatchlistUseCase
import com.fermer.domain.usecase.watchlist.RemoveFromWatchlistUseCase
import com.fermer.model.Movie
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
    private val isInWatchlist: IsInWatchlistUseCase
) : ViewModel() {

    private val _isSaved = MutableStateFlow<Boolean?>(null)
    val isSaved: StateFlow<Boolean?> = _isSaved.asStateFlow()

    fun checkWatchlist(id: Int) {
        viewModelScope.launch {
            _isSaved.value = isInWatchlist(id)
        }
    }

    fun toggleWatchlist(movie: Movie) {
        viewModelScope.launch {
            if (_isSaved.value == true) {
                removeFromWatchlist(movie)
                _isSaved.value = false
            } else {
                addToWatchlist(movie)
                _isSaved.value = true
            }
        }
    }
}
