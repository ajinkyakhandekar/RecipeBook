package com.tghc.recipebook.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.tghc.recipebook.extention.inflater
import kotlinx.android.extensions.LayoutContainer

class RecyclerAdapter<Data : Any>(var itemList: MutableList<Data>
                                  , @LayoutRes val layoutID: Int
                                  , val bind: ViewHolder<Data>.(data: Data, position: Int) -> Unit
                                  , val click: ViewHolder<Data>.() -> Unit)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Data> {
        val view = parent.inflater(layoutID)
        val holder = ViewHolder<Data>(view)
        holder.click()
        return holder
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder<Data>, position: Int) {
        holder.bind(itemList[position], position)}

    class ViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer{
        override val containerView: View?
            get() = itemView
    }

}