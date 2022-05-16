package com.app.weatherapp.common.startup

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.WorkManager

class LibaryB : Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        AppInitializer.getInstance(context).initializeComponent(LibaryA::class.java)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return mutableListOf(LibaryA::class.java)
    }
}