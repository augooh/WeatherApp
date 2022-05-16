package com.app.weatherapp.module.app

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.base.repository.ApiRepository
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.state.State


class AppRepository(var loadState: MutableLiveData<State>) : ApiRepository() {
    suspend fun queryAllChoosePlace() = RoomHelper.queryAllChoosePlace(loadState)
}