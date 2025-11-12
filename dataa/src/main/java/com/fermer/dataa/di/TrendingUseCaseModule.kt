package  com.fermer.dataa.di

import com.fermer.domain.usecase.GetTrendingMoviesUseCase
import com.fermer.domain.repository.TrendingRepository
import com.fermer.domain.usecase.GetMovieUseCase
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

    @Provides
    @ViewModelScoped
    fun provideGetMovieUseCase(repository: TrendingRepository): GetMovieUseCase =
        GetMovieUseCase(repository)
}
