package com.app.hrcomposeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Travel::class], version = 1, exportSchema = false)
abstract class EmployeeRoomDatabase : RoomDatabase() {

    abstract fun travelDao(): TravelDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeRoomDatabase? = null

        fun getInstance(context: Context): EmployeeRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmployeeRoomDatabase::class.java,
                        "employee_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
