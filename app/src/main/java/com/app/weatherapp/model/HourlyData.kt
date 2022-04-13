package com.app.weatherapp.model


data class HourlyData(
    val api_status: String,
    val api_version: String,
    var lang: String,
    val location: List<Double>,
    val result: Result,
    val server_time: Int,
    val skycon: List<Skycon>,
    val status: String,
    val temperature: List<Temperature>,
    val visibility: List<Visibility>,
    val wind: List<Wind>
)

data class AirQuality(
    val aqi: List<Aqi>,
    val pm25: List<Pm25>
)

data class Cloudrate(
    val datetime: String,
    val value: Double
)

data class Dswrf(
    val datetime: String,
    val value: Double
)

data class Humidity(
    val datetime: String,
    val value: Double
)

data class Precipitation(
    val datetime: String,
    val value: Double
)

data class Pressure(
    val datetime: String,
    val value: Double
)

data class Skycon(
    val datetime: String,
    val value: String
)

data class Temperature(
    val datetime: String,
    val value: Double
)

data class Visibility(
    val datetime: String,
    val value: Double
)

data class Wind(
    val datetime: String,
    val direction: Double,
    val speed: Double
)

data class Aqi(
    val datetime: String,
    val value: Value
)

data class Pm25(
    val datetime: String,
    val value: Double
)

data class Value(
    val chn: Double,
    val usa: Double
)