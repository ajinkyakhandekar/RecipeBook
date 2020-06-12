package com.tghc.recipebook.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AddPagerAdapter(fragmentActivity: FragmentActivity, private val fragment: ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragment.size
    override fun createFragment(position: Int): Fragment = fragment[position]
}