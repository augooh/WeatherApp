package com.app.weatherapp.module.home.repository

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.base.repository.ApiRepository
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.state.State

class HomeRepository(var loadState: MutableLiveData<State>) : ApiRepository() {
    suspend fun queryAllPlace() = RoomHelper.queryAllPlace(loadState)
}