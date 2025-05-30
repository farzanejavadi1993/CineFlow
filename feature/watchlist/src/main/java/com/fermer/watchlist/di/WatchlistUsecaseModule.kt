package com.fermer.watchlist.di

import com.fermer.domain.usecase.watchlist.AddToWatchlistUseCase
import com.fermer.domain.usecase.watchlist.GetWatchlistUseCase
import com.fermer.domain.usecase.watchlist.IsInWatchlistUseCase
import com.fermer.domain.usecase.watchlist.RemoveFromWatchlistUseCase
import com.fermer.domain.usecase.watchlist.WatchlistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WatchlistUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetWatchlistUseCase(repository: WatchlistRepository): GetWatchlistUseCase =
        GetWatchlistUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideAddToWatchlistUseCase(repository: WatchlistRepository): AddToWatchlistUseCase =
        AddToWatchlistUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideRemoveFromWatchlistUseCase(repository: WatchlistRepository):
            RemoveFromWatchlistUseCase = RemoveFromWatchlistUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideIsInWatchlistUseCase(repository: WatchlistRepository): IsInWatchlistUseCase =
        IsInWatchlistUseCase(repository)
}
