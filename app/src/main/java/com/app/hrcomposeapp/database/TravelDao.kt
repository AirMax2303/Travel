package com.app.hrcomposeapp.database

import androidx.room.*

@Dao
interface TravelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTravel(travel: Travel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListTravel(list: List<Travel>)

    @Query("SELECT * FROM travels")
    fun getAllTravels(): List<Travel>

    @Update
    suspend fun updateTravelDetails(Travel: Travel)

    @Delete
    suspend fun deleteTravel(Travel: Travel)

    @Delete
    suspend fun deleteAllTravel(list: List<Travel>)

    @Query("SELECT * FROM travels WHERE id = :Id")
    fun findTravelById(Id: String): Travel
}