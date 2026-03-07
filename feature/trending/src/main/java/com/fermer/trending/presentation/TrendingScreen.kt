package com.fermer.trending.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.fermer.trending.component.TrendingItem


@Composable
fun TrendingScreen(
    navController: NavController,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    val movies =  viewModel.moviesPagingFlow.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search movies...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search"
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )


        when (val refreshState = movies.loadState.refresh) {
            is LoadState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is LoadState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error accrued: ${refreshState.error.localizedMessage}")
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        count = movies.itemCount,
                        key = { index -> movies[index]?.id ?: index }
                    ) { index ->
                        val movie = movies[index]
                        if (movie != null) {
                            TrendingItem(
                                movie = movie,
                                onClick = { navController.navigate("movie_detail/${movie.id}") }
                            )
                        }
                    }


                    when (movies.loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )
                            }
                        }
                        is LoadState.Error -> {
                            item {
                                Text("Error in loading more items...")
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

