package com.fermer.watchlist.presentaion.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.fermer.watchlist.state.WatchlistState
import com.fermer.watchlist.component.WatchlistItem

@Composable
fun WatchlistScreenFake(state: WatchlistState) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.movies.isEmpty()) {
        Text("هیچ فیلمی در علاقه‌مندی‌ها نیست.")
    } else {
        LazyColumn {
            items(state.movies.size) { movie ->
                WatchlistItem(
                    movie = state.movies[movie],
                    onClick = {},
                    onRemove = {}
                )
            }
        }
    }
}
