package com.fermer.common.di

import android.app.Application
import com.fermer.common.theme.ThemePreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    @Singleton
    fun provideThemePreferenceManager(application: Application): ThemePreferenceManager {
        return ThemePreferenceManager(application)
    }
}