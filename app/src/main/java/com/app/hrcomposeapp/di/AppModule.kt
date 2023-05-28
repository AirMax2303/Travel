package com.app.hrcomposeapp.di

import com.app.hrcomposeapp.database.TravelDao
import com.app.hrcomposeapp.repository.TravelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTravelRepository(travelDao: TravelDao): TravelRepository {
        return TravelRepository(travelDao)
    }
}
