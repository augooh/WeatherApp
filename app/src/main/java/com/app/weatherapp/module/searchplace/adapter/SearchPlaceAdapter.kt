package com.app.weatherapp.module.searchplace.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.app.weatherapp.R
import com.app.weatherapp.model.Place

class SearchPlaceAdapter(layour: Int, listData: MutableList<Place>?)  :
    BaseQuickAdapter<Place, BaseViewHolder>(layout, listData) {

    override fun convert(holder: BaseViewHolder, item: Place) {
        holder?.let{ holder ->
            item?.let {
                holder.setText(R.id.placeName, item.name)
                holder.setText(R.id.placeAddress, item.address)
            }
        }
    }
}