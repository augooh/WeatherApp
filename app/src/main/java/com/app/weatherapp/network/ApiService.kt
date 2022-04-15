package com.app.weatherapp.network


import com.app.weatherapp.common.Constant
import com.app.weatherapp.model.Daily
import com.app.weatherapp.model.HourlyData
import com.app.weatherapp.model.RealTime
import com.app.weatherapp.module.searchplace.model.SearchPlaceResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v2/place?token=${Constant.CAIYUN_TOKEN}&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") query: String): SearchPlaceResponse

    @GET("v2.5/${Constant.CAIYUN_TOKEN}/{lng},{lat}/realtime.json")
    suspend fun loadRealtimeWeatehr(
        @Path("lng") lng: String?,
        @Path("lat") lat: String?
    ): RealTime

    @GET("v2.5/${Constant.CAIYUN_TOKEN}/{lng},{lat}/daily.json")
    suspend fun loadDailyWeather(
        @Path("lng") lng: String?,
        @Path("lat") lat: String?
    ): Daily

    @GET("v2.5/${Constant.CAIYUN_TOKEN}/{lng},{lat}/hourly.json?hourlysteps=12")
    suspend fun loadHourlyWeather(
        @Path("lng") lng: String?,
        @Path("lat") lat: String?
    ): Daily
}