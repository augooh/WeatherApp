package com.app.weatherapp.network

import org.apache.http.conn.ConnectTimeoutException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonParseException
import com.app.weatherapp.common.state.State
import com.app.weatherapp.common.state.StateType

object NetExceptionHandle {
    fun handleException(e: Throwable?, loadState: MutableLiveData<State>){
        e?.let{
            when (it) {

            }
        }
    }
}