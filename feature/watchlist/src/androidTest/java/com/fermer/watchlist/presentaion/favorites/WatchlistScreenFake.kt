package com.fermer.watchlist.presentaion.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.fermer.watchlist.presentation.state.WatchlistState
import com.fermer.watchlist.presentation.favorites.WatchlistItem

@Composable
fun WatchlistScreenFake(state: WatchlistState) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.movies.isEmpty()) {
        Text("هیچ فیلمی در علاقه‌مندی‌ها نیست.")
    } else {
        LazyColumn {
            items(state.movies) { movie ->
                WatchlistItem(
                    movie = movie,
                    onClick = {},
                    onRemove = {}
                )
            }
        }
    }
}
