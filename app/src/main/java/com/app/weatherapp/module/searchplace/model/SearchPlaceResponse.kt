package com.app.weatherapp.module.searchplace.model

import com.app.weatherapp.model.Place

class SearchPlaceResponse {
    val status: String,
    val places : MutableList<Place>
}