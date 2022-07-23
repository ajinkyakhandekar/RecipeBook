package com.tghc.recipebook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class RecyclerAdapter<Data : Any, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    private val bind: ViewHolder<VB>.(Data, ItemDetails) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder<VB>>() {

    var setClickListeners: (ViewHolder<VB>.(MutableList<Data>) -> Unit)? = null
    private var itemList = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        setClickListeners?.invoke(holder, itemList)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        holder.bind(itemList[position], getItemDetails(position))
    }

    override fun getItemCount() = itemList.count()

    fun updateData(itemList: List<Data>) {
        val diffUtilCallback = DiffUtilCallback(this.itemList, itemList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.itemList.clear()
        this.itemList.addAll(itemList)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun getItemDetails(position: Int) = ItemDetails(
        size = itemList.size,
        position = position,
        isFirst = position == 0,
        isLast = position == itemList.size - 1
    )

    class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

}


fun <Data : Any, VB : ViewBinding> RecyclerView.withAdapter(
    bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    bind: RecyclerAdapter.ViewHolder<VB>.(Data, ItemDetails) -> Unit
): RecyclerAdapter<Data, VB> {
    val recyclerAdapter = RecyclerAdapter(bindingInflater, bind)
    itemAnimator = null
    adapter = recyclerAdapter
    return recyclerAdapter
}

data class ItemDetails(
    var size: Int,
    var position: Int,
    var isFirst: Boolean,
    var isLast: Boolean
)