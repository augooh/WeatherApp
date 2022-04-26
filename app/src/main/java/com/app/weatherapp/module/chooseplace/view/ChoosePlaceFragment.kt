package com.app.weatherapp.module.chooseplace.view

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.app.weatherapp.R
import com.app.weatherapp.base.view.BaseLifeCycleFragment
import com.app.weatherapp.common.getEventViewModel
import com.app.weatherapp.common.util.CommonUtil
import com.app.weatherapp.databinding.FragmentListBinding
import com.app.weatherapp.model.ChoosePlaceData
import com.app.weatherapp.module.chooseplace.adapter.ChoosePlaceAdapter
import com.app.weatherapp.module.chooseplace.viewmodel.ChoosePlaceViewModel
import kotlinx.android.synthetic.main.custom_bar.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.ic_weather_item.view.*


class ChoosePlaceFragment : BaseLifeCycleFragment<ChoosePlaceViewModel, FragmentListBinding>() {
    private lateinit var mAdapter: ChoosePlaceAdapter

    private lateinit var mHeaderView: View

    override fun getLayoutId() = R.layout.fragment_list

    override fun initView() {
        super.initView()
        initRefresh()
        initAdapter()
        initHeaderView()
    }

    private fun initRefresh() {
        mSrlRefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.bluebackground
            )
        )
        mSrlRefresh.setColorSchemeColors(Color.WHITE)
        mSrlRefresh.setOnRefreshListener { initFata() }
    }

    private fun initinitHaderView() {
        mHeaderView = View.inflate(requireActivity(), R.layout.custom_bar, null)
        mHeaderView.apply {
            detail_title.text = "添加的城市"
            detail_start.visibility = View.VISIBLE
            detail_end.visibility = View.VISIBLE
            detail_end.setOnclickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_choosePlaceFragment_to_searchPlaceFragment)
            }
            detail_start.setOnClickListener {
                Navigation.findNavController(it).navigateUp()
            }
        }
        mAdapter.addHeaderView(mHeaderView)
    }

    override fun initData() {
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
        }
        mViewModel.queryAllChoosePlace()
    }

    override fun initDataObserver() {
        mViewModel.mChoosePlaceData.observe(this, Observer { response ->
            response?.let {
                if (response.size == 0) {
                    CommonUtil.showToast(requireContext(), "请添加城市")
                    mHeaderView.detail_start.setOnClickListener {
                        CommonUtil.showToast(requireContext(), "请添加城市")
                    }
                } else {
                    mHeaderView.detail_start.setOnClickListener {
                        Navigation.findNavController(it).navigateUp()
                    }
                }
                setPlaceList(response)
            }
        })
        requireActivity().getEventViewModel().addChoosePlace.observe(this, Observer {
            it?.let {
                mViewModel.queryAllChoosePlace()
//                mAdapter.notifyDataSetChanged()
            }
        })
        appViewModel.mCurrentPlace.observe(this, Observer {
            it?.let {
                requireActivity().getEventViewModel().changeCurrentPlace.postValue(true)
            }
        })
        showSuccess()
    }

    private fun initAdapter() {
        mAdapter = ChoosePlaceAdapter(R.layout.place_item, null)
        mRvArticle?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRvArticle.adapter = mAdapter
        mAdapter.setOnItemLongClickListener { adapter, view, position ->
            mAdapter.getViewByPosition(position + 1, R.id.location_delete)?.visibility =
                View.VISIBLE
            mAdapter.getViewByPosition(position + 1, R.id.location_card)
                ?.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey_80
                    )
                )
            mAdapter.getViewByPosition(position + 1, R.id.location_delete)?.setOnClickListener {
                val place = mAdapter.getItem(position)
                place?.let {
                    MaterialDialog(requireContext()).show {
                        title(R.string.title)
                        message(R.string.delete_city)
                        cornerRadius(8.0f)
                        negativeButton(R.string.cancel) {
                            mAdapter.getViewByPosition(
                                position + 1,
                                R.id.location_delete
                            )?.visibility =
                                View.GONE
                            mAdapter.getViewByPosition(position + 1, R.id.location_card)
                                ?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.bluebackground
                                    )
                                )
                        }
                        positiveButton(R.string.delete) {
                            mViewModel.deletePlace(place.name)
                            mViewModel.deleteChoosePlace(place)
                            requireActivity().getEventViewModel().addPlace.postValue(true)
                            mAdapter.getViewByPosition(position + 1, R.id.location_delete)?.visibility =
                                View.GONE
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                }
                true
            }
            true
        }
        mAdapter.setOnItemClickListener { adapter, view, position ->
            appViewModel.changeCurrentPlace(position)
            Navigation.findNavController(view).navigateUp()
        }
    }

    private fun setPlaceList(addedPlaceList: MutableList<ChoosePlaceData>) {
        mAdapter.setNewInstance(addedPlaceList)
    }
}