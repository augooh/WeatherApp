package com.app.weatherapp.common.startup

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WeatherStartUp : Initializer<WorkManager{
    override fun create(context: Context): WorkManager {
        val request =
            PeriodicWorkRequest.Builder(WeatherWorkManager::class.java, 15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(context).enqueue(request)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}