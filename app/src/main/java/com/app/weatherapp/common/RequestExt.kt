package com.app.weatherapp.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.base.repository.BaseRepository
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.state.State
import com.app.weatherapp.network.NetExceptionHandle
import kotlinx.coroutines.launch

fun <T : BaseRepository> BaseViewModel<T>.initiateRequest(
    block: suspend () -> Unit,
    loadState: MutableLiveData<State>
) {
    viewModelScope.launch {
        runCatching {
            block()
            Log.d("app", "success")
        }.onSuccess {
            Log.d("app", "success1")
        }.onFailure {
            Log.d("app", "fail")
            NetExceptionHandle.handleException(it, loadState)
        }
    }
}