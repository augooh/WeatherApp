package com.app.weatherapp.base.view
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.kingja.loadsir.callback.SuccessCallback
import com.app.weatherapp.common.callback.*
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.getAppViewModel
import com.app.weatherapp.common.state.State
import com.app.weatherapp.common.state.StateType
import com.app.weatherapp.module.app.AppViewModel

abstract class BaseLifeCycleActivity<VM : BaseViewModel<*>, DB : ViewDataBinding> :
    BaseActivity<VM, DB>(){
    val appViewModel: AppViewModel by lazy { getAppViewModel() }

    override fun iniView() {
        showSuccess()
        mViewModel.loadState.observe(this, observer)
        initDataObserver()
    }

    open fun initDataObserver() {}

    open fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    open fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showError(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeTake(this, msg, Toast.LENGTH_SHORT).show()
        }
        loadService.showCallback(ErrorCallBack::class.java)
    }

    open fun showEmpty() {
        loadService.showCallback(EmptyCallBack::class.java)
    }

    private val observer by lazy {
        Observer<State> {
            it?.let{
                when (it.code) {
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showError("网络出现问题了")
                    StateType.NETWORK_ERROR -> showError("网络出现问题了")
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }
}