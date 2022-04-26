package com.app.weatherapp.module.chooseplace.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.LocationTypeConverter
import com.app.weatherapp.module.chooseplace.model.dao.ChoosePlaceDao

@Database(entities = [ChoosePlaceData::class], version = 1, exportSchema = false)
@TypeConverters(LocationTypeConverter::class)
abstract class ChoosePlaceDataBase : RoomDatabase() {
    abstract fun choosePlaceDao(): ChoosePlaceDao
    companion object {
        private var INSTANCE: ChoosePlaceDataBase? = null
        fun getInstance(context: Context): ChoosePlaceDataBase? {
            if (INSTANCE == null) {
                synchronized(ChoosePlaceDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            ChoosePlaceDataBase::class.java,
                            "database_choose_place"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}