package com.app.weatherapp.base.repository

import com.app.weatherapp.network.RetrofitFactory

abstract class ApiRepository : BaseRepository() {
    protected val apiService: ApiService by lazy {
        RetrofitFactory.instance.createRetrofit(ApiService::class.java)
    }
}