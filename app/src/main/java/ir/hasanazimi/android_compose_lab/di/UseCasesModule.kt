package ir.hasanazimi.android_compose_lab.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hasanazimi.android_compose_lab.data.repository.sources.XRepository
import ir.hasanazimi.android_compose_lab.domain.XUseCase
import ir.hasanazimi.android_compose_lab.domain.XUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideXUseCase(xRepository: XRepository): XUseCase {
        return XUseCaseImpl(xRepository)
    }


}