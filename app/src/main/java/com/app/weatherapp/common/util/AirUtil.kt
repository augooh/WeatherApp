package com.app.weatherapp.common.util

class AirLevel(val airLevel: String)

fun getAirLevel(airLevel: Double): AirLevel {
    if (airLevel in 0.0..50.0) {
        return AirLevel("优")
    }
    if (airLevel in 50.0..100.0) {
        return AirLevel("良")
    }
    if (airLevel in 100.0..150.0) {
        return AirLevel("轻度污染")
    }
    if (airLevel in 150.0..200.0) {
        return AirLevel("中度污染")
    }
    return AirLevel("重度污染")
}
