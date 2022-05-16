package com.app.weatherapp.module.web

import android.view.View
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.just.agentweb.AgentWeb
import com.app.weatherapp.R
import com.app.weatherapp.base.view.BaseLifeCycleFragment
import com.app.weatherapp.common.navigation.NavHostFragment
import com.app.weatherapp.databinding.WebFragmentBinding
import com.app.weatherapp.module.app.AppViewModel
import kotlinx.android.synthetic.main.custom_bar.view.*
import kotlinx.android.synthetic.main.web_fragment.*

class WebFragment : BaseLifeCycleFragment<AppViewModel, WebFragmentBinding>() {
    private var mUrl: String = ""
    private var mTitle: String = ""
    private var mAgentWeb: AgentWeb? = null
    override fun getLayoutId() = R.layout.web_fragment

    override fun initView() {
        super.initView()
        showSuccess()
        initArguments()
        initHeaderView()
        initAgentWeb()
    }

    private fun initArguments() {
        arguments?.let {
            mUrl = it.getString("url").toString()
            mTitle = it.getString("title").toString()
        }
    }

    private fun initHeaderView() {
        web_bar.detail_title.text = mTitle
        web_bar.detail_start.visibility = View.VISIBLE
        web_bar.detail_end.visibility = View.GONE
        web_bar.detail_start.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }

    private fun initAgentWeb() {
        //加载网页
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(web_view, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(mUrl)
        //添加返回键逻辑
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mAgentWeb?.let {
                        if (it.webCreator.webView.canGoBack()) {
                            it.webCreator.webView.goBack()
                        } else {
                            NavHostFragment.findNavController(this@WebFragment).navigateUp()
                        }
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}