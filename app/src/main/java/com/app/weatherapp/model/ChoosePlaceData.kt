package com.app.weatherapp.model

import androidx.room.PrimaryKey

data class ChoosePlaceData(
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int,
    var isLocation: Boolean,
    val name: String,
    val temperature: Int,
    val skycon: String
)