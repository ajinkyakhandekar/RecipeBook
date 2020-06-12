package com.tghc.recipebook.extention

import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.tghc.recipebook.ui.adapter.RecyclerAdapter

fun ViewPager2.onPageChanged(pageScrolled: (position: Int, positionOffsetPixels: Int) -> Unit, pageSelected: (position: Int) -> Unit) {
    val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            pageScrolled(position, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            pageSelected(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }
    }

    registerOnPageChangeCallback(pageChangeCallback)
}


fun <T : Any> ViewPager2.withAdapter(itemList: MutableList<T>, @LayoutRes layoutID: Int, bind: RecyclerAdapter.ViewHolder<T>.(data: T, position: Int) -> Unit, click: RecyclerAdapter.ViewHolder<T>.() -> Unit): RecyclerAdapter<T> {

    val recyclerAdapter = RecyclerAdapter(itemList, layoutID, bind, click)
    adapter = recyclerAdapter
    return recyclerAdapter
}


