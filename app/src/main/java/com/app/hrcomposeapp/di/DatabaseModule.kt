package com.app.hrcomposeapp.di

import android.content.Context
import androidx.room.Room
import com.app.hrcomposeapp.database.EmployeeRoomDatabase
import com.app.hrcomposeapp.database.TravelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    fun provideTravelDao(appDatabase: EmployeeRoomDatabase): TravelDao {
        return appDatabase.travelDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): EmployeeRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EmployeeRoomDatabase::class.java,
            "appDB"
        ).build()
    }

}