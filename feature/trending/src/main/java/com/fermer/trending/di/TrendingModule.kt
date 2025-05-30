package com.fermer.trending.presentation.di

import com.fermer.domain.usecase.trending.TrendingRepository
import com.fermer.network.api.TmdbApiService
import com.fermer.trending.presentation.data.TrendingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrendingModule {

    @Provides
    @ViewModelScoped
    fun provideTrendingRepository(api: TmdbApiService): TrendingRepository {
        return TrendingRepositoryImpl(api)
    }

}