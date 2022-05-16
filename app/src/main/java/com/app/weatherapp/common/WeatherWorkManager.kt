package com.app.weatherapp.common

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.app.weatherapp.base.BaseApplication
import com.app.weatherapp.module.app.AppViewModel

class WeatherWorkManager(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    private val mAppViewModel =
        BaseApplication.instance.getAppViewModelProvider().get(AppViewModel::class.java)

    override fun dowork(): Result {
        Log.d(Companion.TAG, "doWork")
        mAppViewModel.queryAllChoosePlace()
        return Result.success()
    }

    companion object {
        private const val TAG = "WeatherWorkManager"
    }
}
