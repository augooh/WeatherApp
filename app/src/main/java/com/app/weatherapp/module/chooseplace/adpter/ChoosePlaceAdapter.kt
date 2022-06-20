package com.app.weatherapp.module.chooseplace.adpter

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.app.weatherapp.R
import com.app.weatherapp.common.setAdapterAnimation
import com.app.weatherapp.common.util.CommonUtil
import com.app.weatherapp.common.util.getSky
import com.app.weatherapp.model.ChoosePlaceData
import kotlinx.android.synthetic.main.place_item.view.*

class ChoosePlaceAdapter(layout: Int, listData: MutableList<ChoosePlaceData>?) :
    BaseQuickAdapter<ChoosePlaceData, BaseViewHolder>(
        layout, listData
    ){
    init {
        setAdapterAnimation(2)
    }

    override fun convert(holder: BaseViewHolder, item: ChoosePlaceData) {
        holder?.let { holder ->
            item?.let {
                holder.itemView.location_card.setBackgroundColor(
                    if (CommonUtil.getNightString(item.skycon)) ContextCompat.getColor(
                        context,
                        R.color.colorPrimaryDarkNight
                    ) else ContextCompat.getColor(
                        context, R.color.bluebackground
                    )
                )
                holder.setVisible(R.id.location_tag, item.isLocation)
                holder.setGone(R.id.location_tag, !item.isLocation)
                holder.setText(R.id.location_name, item.name)
                holder.setText(
                    R.id.location_temperature,
                    "${item.temperature} ℃"
                )
                holder.setImageResource(
                    R.id.location_tag, getSky(item.skycon).icon
                )
            }
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        setAdapterAnimation(2)
    }
}