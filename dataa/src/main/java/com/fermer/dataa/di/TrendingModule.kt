package com.fermer.dataa.di

import com.fermer.dataa.remote.api.MovieApiService
import com.fermer.dataa.repository.TrendingRepositoryImpl
import com.fermer.domain.repository.TrendingRepository
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
    fun provideTrendingRepository(api: MovieApiService): TrendingRepository {
        return TrendingRepositoryImpl(api)
    }

}