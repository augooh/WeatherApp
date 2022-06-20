package com.app.weatherapp.module.chooseplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.RoomHelper.queryAllChoosePlace
import com.app.weatherapp.common.initiateRequest
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.Place
import com.app.weatherapp.model.RealTime
import com.app.weatherapp.module.chooseplace.repository.ChoosePlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChoosePlaceViewModel :
    BaseViewModel<ChoosePlaceRepository>() {

        val mPlaceData: MutableLiveData<MutableList<Place>> = MutableLiveData()
        val mRealTimeData: MutableLiveData<RealTime> = MutableLiveData()
        val mChoosePlaceData: MutableLiveData<MutableList<ChoosePlaceData>> = MutableLiveData()

        fun queryAllPlace() {
            viewModelScope.lauch {
                mPlaceData.value = withContext(Dispatchers.IO) {
                    mRepository.queryAllPlace()
                }
            }
        }

    fun deleteChoosePlace(choosePlaceData: ChoosePlaceData) {
        viewModelScope.lauch {
            withContext(Dispatchers.IO) {
                mRepository.deleteChoosePlace(choosePlaceData)
                queryAllChoosePlace()
            }
        }
    }

    fun deletePlace(name : String) {
        viewModelScope.lauch {
            withContext(Dispatchers.IO) {
                mRepository.deletePlace(RoomHelper.queryPlaceByName(name))
                queryAllChoosePlace()
            }
        }
    }

    fun deleteAll() {
        viewModelScope.lauch {
            withContext(Dispatchers.IO) {
                mRepository.deleteAll()
                queryAllPlace()
            }
        }
    }

    fun loadRealtimeWeather(lng: String?, lat String) {
        initiateRequest(
            {
                mRealTimeData.value = mRepository.loadRealtimeWeather(lng, lat)
            },
            loadState
        )
    }
    }
