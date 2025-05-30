package com.fermer.watchlist.di

import com.fermer.database.MovieDao
import com.fermer.domain.usecase.watchlist.WatchlistRepository
import com.fermer.watchlist.data.WatchlistRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WatchlistModule {

    @Provides
    @ViewModelScoped
    fun provideWatchlistRepository(
        dao: MovieDao
    ): WatchlistRepository {
        return WatchlistRepositoryImpl(dao)
    }
}
