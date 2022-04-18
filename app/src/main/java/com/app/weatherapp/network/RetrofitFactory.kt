package com.app.weatherapp.network

import com.app.weatherapp.common.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory private constructor(){
    private val retrofit : Retrofit

    fun <T> createRetrofit(clazz: Class<T>) : T{
        return retrofit.create(clazz)
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory((GsonConverterFactory.create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        val instance by lazy {
            RetrofitFactory()
        }
    }
}