package com.app.weatherapp.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.startup.AppInitializer
import com.app.weatherapp.common.callback.EmptyCallBck
import com.app.weatherapp.common.callback.LoadingCallBack
import com.kingja.loadsir.core.LoadSir
import com.wjx.android.wanandroidmvvm.common.callback.*
import com.wjx.android.weather.common.startup.WeatherStartUp
import com.wjx.android.weather.common.util.SPreference
import java.lang.reflect.ParameterizedType

open class BaseApplication : Application(), ViewModelStoreOwner {
    lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    companion object {
        lateinit var instance : BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SPreference.setContext(this)
        initLoadSir()
        mAppViewModelStore = ViewModelStore()
        AppInitailizer.getInstance(instance).initaializeComponent(WeatherStartUp::class.java)
    }

    private fun initLoadSir(){
        LoadSir.beginBuilder()
            .addCallback(ErrorCallBack())
            .addCallback(LoadingCallBack())
            .addCallback(EmptyCallBack())
            .commit()
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

    @Suppress("UNCHECKED_CAST")
    fun <VM> getVmClazz(obj: Any) : VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }
}
