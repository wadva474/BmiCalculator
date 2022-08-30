package com.wadud.bmi.di

import com.wadud.bmi.data.BmiRepository
import com.wadud.bmi.domain.repository.BmiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBMIRepository(bmiRepositoryImpl: BmiRepositoryImpl) : BmiRepository
}