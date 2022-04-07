package com.app.weatherapp.common.callback

import com.kingja.loadsir.callback.Callback
import com.app.weatherapp.R

class LoadingCallBack : Callback() {
    override fun onCreateView(): Int = R.layout.layout_loading
}