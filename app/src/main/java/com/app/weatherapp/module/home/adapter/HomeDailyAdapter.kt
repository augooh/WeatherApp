package com.app.weatherapp.module.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.app.weatherapp.R
import com.app.weatherapp.common.util.DateUtil
import com.app.weatherapp.common.util.getSky
import com.app.weatherapp.model.Daily

class HomeDailyAdapter(layout: Int, listData: MutableList<Daily.DailyData>?) :
    BaseQuickAdapter<Daily.DailyData, BaseViewHolder>(
        layout, listData
    ){
        override fun convert(holder: BaseViewHolder, item: Daily.DailyData) {
            holder?.let { holder ->
                item?.let{
                    holder.setText(R.id.data, DataUtil.getTodayInWeek(item.data))
                        .setImageResource(R.id.weather, getSky(
                            item.value
                        ).icon)
                        .setText(R.id.temperature, "${item.min.toInt()}℃~ ${item.max.toInt()}℃")
                }

            }
        }
}