package com.app.weatherapp.common.widget

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WeatherService :Service() {
    override fun onBind(intent: Intent?): IBinder? = null
}