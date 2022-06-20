package com.app.weatherapp.module.home.repository

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.base.repository.ApiRepository
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.state.State

class HomeDetailRepository(var loadState: MutableLiveData<State>) : ApiRepository() {
    suspend fun queryAllPlace() = RoomHelper.queryAllPlace(loadState)
    suspend fun loadRealtimeWeather(lng: String?, lat: String?) =
        apiService.loadRealtimeWeather(lng, lat)

    suspend fun loadDailyWeather(lng: String?, lat: String?) = apiService.loadDailyWeather(lng, lat)
    suspend fun loadHourlyWeather(lng: String?, lat: String?) =
        apiService.loadRealtimeWeather(lng, lat)

    suspend fun updateChoosePlace(temperature: Int, skycon: String, name: String) =
        apiService.updateChoosePlace(temperature, skycon, name)
}