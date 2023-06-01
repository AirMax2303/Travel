package com.app.hrcomposeapp.database

import androidx.room.*

@Dao
interface TravelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTravel(travel: Travel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListTravel(list: List<Travel>)

    @Query("SELECT * FROM travels ORDER BY id DESC")
    fun getAllTravels(): List<Travel>

    @Query("SELECT * FROM travels WHERE category = :category")
    fun getTravelsByCategory(category: String): List<Travel>

    @Query("SELECT category FROM travels GROUP BY category")
    fun getCategoryes(): List<Category>

    @Query("SELECT * from travels WHERE category = :category ORDER BY " +
            "CASE WHEN :columnIndex = 0 AND :sort = 0 THEN id END DESC," +
            "CASE WHEN :columnIndex = 0 AND :sort = 1 THEN id END ASC," +
            "CASE WHEN :columnIndex = 1 AND :sort = 0 THEN destination END DESC," +
            "CASE WHEN :columnIndex = 1 AND :sort = 1 THEN destination END ASC," +
            "CASE WHEN :columnIndex = 2 AND :sort = 0 THEN name END DESC," +
            "CASE WHEN :columnIndex = 2 AND :sort = 1 THEN name END ASC," +
            "CASE WHEN :columnIndex = 3 AND :sort = 0 THEN country END DESC," +
            "CASE WHEN :columnIndex = 3 AND :sort = 1 THEN country END ASC," +
            "CASE WHEN :columnIndex = 4 AND :sort = 0 THEN category END DESC," +
            "CASE WHEN :columnIndex = 4 AND :sort = 1 THEN category END ASC," +
            "CASE WHEN :columnIndex = 5 AND :sort = 0 THEN duration END DESC," +
            "CASE WHEN :columnIndex = 5 AND :sort = 1 THEN duration END ASC," +
            "CASE WHEN :columnIndex = 6 AND :sort = 0 THEN price END DESC," +
            "CASE WHEN :columnIndex = 6 AND :sort = 1 THEN price END ASC"
    )
    fun getSortTravelByIndex(category: String, columnIndex: Int, sort: Int): List<Travel>

    @Update
    suspend fun updateTravelDetails(Travel: Travel)

    @Delete
    suspend fun deleteTravel(Travel: Travel)

    @Delete
    suspend fun deleteAllTravel(list: List<Travel>)

    @Query("SELECT * FROM travels WHERE id = :Id")
    fun findTravelById(Id: String): Travel

}