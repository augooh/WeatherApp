package com.app.weatherapp.module.main.view


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol
import com.amap.api.location.AMapLocationListener
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.wjx.android.weather.R
import com.wjx.android.weather.base.BaseApplication
import com.wjx.android.weather.base.view.BaseLifeCycleActivity
import com.wjx.android.weather.common.checkUpdate
import com.wjx.android.weather.common.getEventViewModel
import com.wjx.android.weather.common.permission.PermissionResult
import com.wjx.android.weather.common.permission.Permissions
import com.wjx.android.weather.common.util.CommonUtil
import com.wjx.android.weather.databinding.ActivityMainBinding
import com.wjx.android.weather.model.ChoosePlaceData
import com.wjx.android.weather.model.Place
import com.wjx.android.weather.module.main.viewModel.MainViewModel


class MainActivity {
}