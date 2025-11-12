package com.fermer.dataa.di

import com.fermer.dataa.local.dao.MovieDao
import com.fermer.dataa.repository.WatchlistRepositoryImpl
import com.fermer.domain.repository.WatchlistRepository
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
