package com.app.weatherapp.module.searchplace.model.dao

import androidx.room.*
import com.app.weatherapp.model.Place

@Dao
interface PlaceDao {
    @Transaction
    @Insert(entity = Place::class)
    suspend fun insertPlace(place: Place) : Long

    @Transaction
    @Query("SELECT * FROM place ORDER BY primarykey desc")
    suspend fun queryAllplace() : MutableList<Place>

    @Transaction
    @Query("SELECT * FROM place WHERE name = (:name)")
    suspend fun queryPlacByName(name : String) : Place?

    @Transaction
    @Query("SELECT * FROM place order by primaryKey desc")
    suspend fun queryFirstPlace() : Place?

    @Transaction
    @Delete(entity = Place::class)
    suspend fun deletePlace(place: Place) : Int

    @Transaction
    @Query("DELETE FROM place")
    suspend fun deleteAll()
}