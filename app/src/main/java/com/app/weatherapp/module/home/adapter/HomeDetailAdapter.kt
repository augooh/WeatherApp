package com.app.weatherapp.module.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeDetailAdapter (
    fragmentManager: FragmentManager,
    val tabs: List<String>,
    val fragments: List<Fragment>
    ) : FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int) : Fragment = fragments[position]

    override fun getCount(): Int = tabs.size

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }
}