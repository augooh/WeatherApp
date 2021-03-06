package com.app.weatherapp.module.main.repository

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.base.repository.ApiRepository
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.state.State
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.Place
import com.app.weatherapp.module.searchplace.model.SearchPlaceResponse


class MacRepository(var loadState: MutableLiveData<State>) : ApiRepository {
    suspend fun searchPlaces(query: String): SearchPlaceResponse {
        return apiService.searchPlaces(query)
    }

    suspend fun loadRealtimeWeather(lng: String?, lat: String?) =
        apiService.loadRealtimeWeather(lng, lat)

    suspend fun insertPlaces(place: Place): Long? = RoomHelper.insertPlace(place)

    suspend fun insertChoosePlaces(choosPlaceData: ChoosePlaceData): Long? =
        RoomHelper.insertChoosePlace(choosePlaceData)
}