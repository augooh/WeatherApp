package com.app.weatherapp.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.base.BaseApplication
import com.app.weatherapp.common.state.State
import com.app.weatherapp.common.state.StateType
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.model.Place
import com.app.weatherapp.model.Temperature
import com.app.weatherapp.module.chooseplace.model.database.ChoosePlaceDataBase
import com.app.weatherapp.module.searchplace.model.database.PlaceDataBase

object RoomHelper {
    private val placeDataBase by lazy {
        PlaceDataBase.getInstance(BaseApplication.instance)
    }

    private val placeDao by lazy {
        placeDataBase?.placeDao()
    }

    suspend fun queryAllPlace(loadState: MutableLiveData<State>): MutableList<Place> {
        val response = placeDao?.queryAllPlace()?.toMutableList()
        if(response!!.isEmpty()) {
            loadState.postValue(State(StateType.SUCCESS))
        }
        return response
    }

    suspend fun queryPlaceByName(name: String):Place? {
        val response = placeDao?.queryPlacByName(name)
        return response
    }

    suspend fun insertPlace(place:Place): Long? =
        placeDao?.let {
            it.queryPlacByName(place.name)?.let{
                var i = placeDao!!.deletePlace(it)
                Log.d("insert", i.toString())
            }
            it.insertPlace(place)
        }

    suspend fun deletePlace(place: Place?) {
        placeDao?.deletePlace(place!!)
    }

    suspend fun deleteAll() {
        placeDao?.deleteAll()
    }

    private val choosePlaceDataBase by lazy {
        ChoosePlaceDataBase.getInstance(BaseApplication.instance)
    }

    private val choosePlaceDao by lazy {
        choosePlaceDataBase?.choosePlaceDao()
    }

    suspend fun queryAllChoosePlace(loadState: MutableLiveData<State>): MutableList<ChoosePlaceData> {
        val response = choosePlaceDao?.queryAllPlace()?.toMutableList()
        if (response!!.isEmpty()) {
            loadState.postValue(State(StateType.SUCCESS))
        }
        return response
    }

    suspend fun queryFirstChoosePlace(): ChoosePlaceData {
        return choosePlaceDao!!.queryAllPlace().toList().get(0)
    }

    suspend fun insertChoosePlace(choosePlaceData: ChoosePlaceData): Long? =
        choosePlaceDao?.let {
            it.queryChoosePlaceByName(choosePlaceData.name)?.let {
                var i = choosePlaceDao!!.deleteChoosePlace(it)
                Log.d("insert", i.toString())
            }
            it.insertPlace(choosePlaceData)
        }


    suspend fun updateChoosePlace(temperature: Int, skycon: String, name: String) {
        choosePlaceDao?.let {
            it.updateChoosePlace(temperature, skycon, name)
        }
    }

    suspend fun deleteChoosePlace(choosePlaceData: ChoosePlaceData) {
        choosePlaceDao?.deleteChoosePlace(choosePlaceData)
    }
}