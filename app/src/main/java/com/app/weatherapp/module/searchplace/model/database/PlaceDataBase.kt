package com.app.weatherapp.module.searchplace.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.weatherapp.model.LocationTypeConverter
import com.app.weatherapp.model.Place
import com.app.weatherapp.module.searchplace.model.dao.PlaceDao


@Database(entities = [Place::class], version = 1, exportSchema = false)
@TypeConverters(LocationTypeConverter::class)
abstract class PlaceDataBase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
    companion object {
        private var INSTANCE: PlaceDataBase? = null
        fun getInstance(context: Context): PlaceDataBase? {
            if(INSTANCE == null){
                synchronized(PlaceDataBase::class.java){
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            PlaceDataBase::class.java,
                            "database_weather"
                        ).build()

                    }
                }
            }
            return INSTANCE
        }
    }
}