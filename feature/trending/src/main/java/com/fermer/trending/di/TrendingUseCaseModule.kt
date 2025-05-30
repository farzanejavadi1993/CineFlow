package com.fermer.trending.presentation.di

import com.fermer.domain.usecase.trending.GetTrendingMoviesUseCase
import com.fermer.domain.usecase.trending.TrendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrendingUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetTrendingMoviesUseCase(repository: TrendingRepository): GetTrendingMoviesUseCase =
        GetTrendingMoviesUseCase(repository)
}
