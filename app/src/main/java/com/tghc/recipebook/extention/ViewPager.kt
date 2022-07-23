package com.tghc.recipebook.extention

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.onPageChanged(
    pageScrolled: (position: Int, positionOffsetPixels: Int) -> Unit,
    pageSelected: (position: Int) -> Unit
) {
    val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            pageScrolled(position, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            pageSelected(position)
        }

    }

    registerOnPageChangeCallback(pageChangeCallback)
}



