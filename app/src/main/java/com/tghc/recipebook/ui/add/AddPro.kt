package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.view.View
import com.tghc.recipebook.common.SIZE_PRO
import com.tghc.recipebook.databinding.AddProBinding
import com.tghc.recipebook.databinding.RowEditProBinding
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment

class AddPro(private val addFragment: AddFragment) : BaseFragment<AddProBinding>(
    AddProBinding::inflate
) {

    private lateinit var addProAdapter: RecyclerAdapter<String, RowEditProBinding>
    private val procedure = addFragment.recipe.procedure as MutableList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!addFragment.flagEdit) {
            for (i in 0 until SIZE_PRO) {
                procedure.add("")
            }
        }

        addProAdapter =
            binding.proRecyclerView.withAdapter(RowEditProBinding::inflate) { pro, itemDetails ->
                val pos = itemDetails.position + 1
                binding.textRowProCount.text = "Step  $pos  :"
                binding.editRowPro.setText(pro)
            }

        addProAdapter.setClickListeners = {
            binding.imageRowProDelete.setOnClickListener {
                procedure.removeAt(adapterPosition)
                addProAdapter.updateData(procedure)
            }

            /*binding.editRowPro.textWatcher {
                procedure[adapterPosition] = binding.editRowPro.getString()
            }*/
        }


        binding.fabPro.setOnClickListener {
            procedure.add("")
            addProAdapter.updateData(procedure)
        }
    }

    fun getProcedure(): ArrayList<String> {
        val procedureArrayList = ArrayList<String>()
        /*for (pro in procedure) {
            if (isEmpty(pro)) procedureArrayList.add(pro)
        }*/
        return procedureArrayList
    }
}