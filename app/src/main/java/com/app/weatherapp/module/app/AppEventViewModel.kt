package com.app.weatherapp.module.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppEventViewModel : ViewModel() {
    var addChoosePlace = MutableLiveData<Boolean>()
    var addPlace = MutableLiveData<Boolean>()
    var changeCurrentPlace = MutableLiveData<Boolean>()
}
