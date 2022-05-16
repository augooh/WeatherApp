package com.app.weatherapp.common.state

import androidx.annotation.StringRes

class State(var code: StateType, var message: String = "", @StringRes var tip: Int = 0) {

}
