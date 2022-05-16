package com.app.weatherapp.module.app

import com.app.weatherapp.base.callback.UnPeekLiveData
import com.app.weatherapp.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppViewModel : BaseViewModel<AppRepository>() {
    var mCurrentPlace = UnPeekLiveData<Int>()
    val mChoosePlaceData: UnPeekLiveData<MutableList<ChoosePlaceData>> = UnPeekLiveData()

    fun changeCurrentPlace(position: Int) {
        mCurrentPlace.value = position
    }

    fun queryAllChoosePlace() {
        viewModelScope.launch {
            mChoosePlaceData.value = withContext(Dispatchers.IO) {
                mRepository.queryAllChoosePlace()
            }
        }
    }
}
