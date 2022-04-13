package com.app.weatherapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Place(
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int,
    var name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String = "")
{
    constructor() : this(0,"",Location("",""),"")
}