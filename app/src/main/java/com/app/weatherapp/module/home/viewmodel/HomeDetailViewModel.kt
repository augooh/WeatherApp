package com.app.weatherapp.module.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.initiateRequest
import com.app.weatherapp.model.*
import com.app.weatherapp.module.home.repository.HomeDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeDetailViewModel :
    BaseViewModel<HomeDetailRepository>() {
    val mRealTimeData: MutableLiveData<RealTime> = MutableLiveData()
    val mDailyData: MutableLiveData<Daily> = MutableLiveData()
    val mHourlyData: MutableLiveData<HourlyData> = MutableLiveData()

    fun loadRealtimeWeather(lng: String?, lat: String?) {
        initiateRequest(
            { mRealTimeData.value = mRepository.loadRealtimeWeather(lng, lat) },
            loadState
        )
    }

    fun loadDailyWeather(lng: String?, lat:String?) {
        initiateRequest({
            mDailyData.value = mRepository.loadDailyWeather(lng, lat)
        }, loadState)
    }

    fun loadHourlyWeather(lng: String?, lat: String?) {
        initiateRequest({
            mHourlyData.value = mRepository.loadHourlyWeather(lng, lat)
        }, loadState)
    }

    fun updateChoosePlace(temperature: Int, skycon: String, name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mRepository.updateChoosePlace(temperature, skycon, name)
            }
        }
    }
}