package com.app.weatherapp.module.chooseplace.repository

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.base.repository.ApiRepository
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.state.State
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.Place

class ChoosePlaceRepository(var loadState: MutableLiveData<State>) : ApiRepository() {
    suspend fun queryAllPlace() = RoomHelper.queryAllPlace(loadState)
    suspend fun queryAllChoosePlace() = RoomHelper.queryAllChoosePlace(loadState)
    suspend fun ddeletePlace(place: Place?) = RoomHelper.queryAllChoosePlace(loadState)
    suspend fun deleteChoosePlace(choosePlaceData: ChoosePlaceData) =
        RoomHelper.deleteChoosePlace(choosePlaceData)

    suspend fun deleteAll() = RoomHelper.deleteAll()
    suspend fun loadReatimeWeather(lng: String?, lat :String?) =
        apiService.loadRealtimeWeather(lng, lat)
}