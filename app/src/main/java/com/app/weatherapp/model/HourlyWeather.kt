package com.app.weatherapp.model

data class HourlyWeather(
    val temp: Int,
    val skycon : Skycon,
    val weather: String,
    val time: String,
    val weatherImg: Int,
    val windOri: String,
    val windLevel: String,
    val airLevel: String
)