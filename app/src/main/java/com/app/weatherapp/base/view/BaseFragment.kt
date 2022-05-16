package com.app.weatherapp.base.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.app.weatherapp.R
import com.app.weatherapp.base.viewmodel.BaseViewModel
import com.app.weatherapp.common.util.CommonUtil

abstract class BaseFragment<VM : BaseViewModel<*>, DB : ViewDataBinding> : Fragment() {
    protected lateinit var mViewModel: VM
    protected lateinit var mDataBinding: DB
    protected lateinit var loadService: LoadService<*>

    open fun initView() {}
    open fun initData() {}

    open fun reLoad() = initData()
    abstract fun getLayoutId(): Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container,false)
        mDataBinding.lifecycleOwner = this
        loadService = LoadSir.getDefault().register(mDataBinding.root) { reLoad() }
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this).get(CommonUtil.getClass(this))
        initView()
        initData()
        initStatusBarColor()
    }
    private fun initStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            requireActivity().window.statusBarColor = ContextCompat.getColor(
                requireActivity(),
                R.color.always_white_text
            )
        }
        if (ColorUtils.calculateLuminance(requireContext().getColor(R.color.always_white_text)) >= 0.5) {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

}