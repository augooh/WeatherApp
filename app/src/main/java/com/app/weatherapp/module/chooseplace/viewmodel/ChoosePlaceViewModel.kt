package com.app.weatherapp.module.chooseplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.RoomHelper
import com.app.weatherapp.common.initiateRequest
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.Place
import com.app.weatherapp.model.RealTime
import com.app.weatherapp.module.chooseplace.repository.ChoosePlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChoosePlaceViewModel :
    BaseViewModel<>
{
}