package com.tghc.recipebook.extention

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.listener.PaginationListener

fun <T : Any> RecyclerView.withAdapter(itemList: MutableList<T>, @LayoutRes layoutID: Int, bind: RecyclerAdapter.ViewHolder<T>.(data: T, position: Int) -> Unit, click: RecyclerAdapter.ViewHolder<T>.() -> Unit): RecyclerAdapter<T> {

    val recyclerAdapter = RecyclerAdapter(itemList, layoutID, bind, click)
    adapter = recyclerAdapter
    return recyclerAdapter
}

fun ViewGroup.inflater(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun <T : Any> RecyclerAdapter.ViewHolder<T>.pos(): Int {
    return adapterPosition
}

fun <T : Any> RecyclerAdapter<T>.notifyItem(position: Int) {
    notifyItemChanged(position, Unit)
}

fun RecyclerView.onScrolledY(vertical: (scroll: Int) -> Unit) {
    var scrollY = 0
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            scrollY += dy
            vertical(scrollY)
        }
    }
    addOnScrollListener(scrollListener)
}

fun RecyclerView.addPagination(isLastPage:Boolean, isLoading:Boolean, loadMoreItems:()->Unit){

    val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val linearLayoutManager = layoutManager as LinearLayoutManager
            val visibleItemCount = linearLayoutManager.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

            if (!isLoading && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }
            }
        }
    }

    addOnScrollListener(onScrollListener)
}