package com.app.weatherapp.module.searchplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.initiateRequest
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.Place
import com.app.weatherapp.model.RealTime
import com.app.weatherapp.module.searchplace.model.SearchPlaceResponse
import com.app.weatherapp.module.searchplace.repository.SearchPlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPlaceViewModel : BaseViewModel<SearchPlaceRepository>() {
    val mSearchPlacesData: MutableLiveData<SearchPlaceResponse> = MutableLiveData()
    val mRealTimeData: MutableLiveData<RealTime> = MutableLiveData()
    val mPlaceInsertResult: MutableLiveData<Long?> = MutableLiveData()
    val mChoosePlaceInsertResult: MutableLiveData<Long?> = MutableLiveData()

    fun searchPlaces(query: String) {
        initiateRequest({
            mSearchPlacesData.value = mRepository.searchPlaces(query)
        }, loadState)
    }

    fun insertPlace(place: Place) {
        viewModelScope.launch {
            mPlaceInsertResult.value = withContext(Dispatchers.IO) {
                mRepository.insertPlaces(place)
            }
        }
    }

    fun loadRealtimeWeather(lng: String?, lat: String?) {
        initiateRequest(
            { mRealTimeData.value = mRepository.loadRealtimeWeather(lng, lat) },
            loadState
        )
    }


    fun insertChoosePlace(choosePlaceData: ChoosePlaceData) {
        viewModelScope.launch {
            mChoosePlaceInsertResult.value = withContext(Dispatchers.IO) {
                mRepository.insertChoosePlaces(choosePlaceData)
            }
        }
    }
}