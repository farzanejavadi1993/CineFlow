package com.fermer.trending.presentation.trendinglist


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.fermer.trending.component.TrendingItem
import com.fermer.trending.state.TrendingState

@Composable
fun TrendingScreenFake(state: TrendingState) {
    when {
        state.isLoading -> {
            CircularProgressIndicator(modifier = Modifier.testTag("loading"))
        }
        state.movies.isEmpty() -> {
            Text("فیلمی برای نمایش وجود ندارد.")
        }
        else -> {
            LazyColumn {
                items(state.movies.size) { item ->
                    TrendingItem(
                        movie = state.movies[item],
                        onClick = {}
                    )
                }
            }
        }
    }
}
