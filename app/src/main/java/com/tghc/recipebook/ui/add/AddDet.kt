package com.tghc.recipebook.ui.add

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tghc.recipebook.R
import com.tghc.recipebook.common.SERVE_TYPE
import com.tghc.recipebook.databinding.AddDtBinding
import com.tghc.recipebook.databinding.RowTagBinding
import com.tghc.recipebook.extention.getString
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment
import java.util.*

class AddDet(addFragment: AddFragment) : BaseFragment<AddDtBinding>(
    AddDtBinding::inflate
) {

    private lateinit var tagAdapter: RecyclerAdapter<String, RowTagBinding>
    var tagsArray = mutableListOf<String>()
    private val recipe = addFragment.recipe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tagsArray = ArrayList()

        binding.edtTitle.setText(recipe.title)
        binding.edtDesc.setText(recipe.description)
        binding.edtCuisine.setText(recipe.cuisine)
        binding.edtServings.setText(recipe.servings)
        binding.edtServeType.text = recipe.type
        binding.edtPrepTime.text = recipe.pTime
        binding.edtCookTime.text = recipe.cTime
        tagsArray = recipe.tags as MutableList<String>

        //tags
        val flexBoxLayoutManager = FlexboxLayoutManager(requireActivity())
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START
        binding.recyclerTag.layoutManager = flexBoxLayoutManager

        tagAdapter = binding.recyclerTag.withAdapter(RowTagBinding::inflate) { tag, _ ->
            binding.textTag.text = tag
        }

        tagAdapter.setClickListeners = {
            binding.imageTagDelete.setOnClickListener {
                tagsArray.removeAt(adapterPosition)
                tagAdapter.updateData(tagsArray)
            }
        }


        /*(tagsArray, R.layout.row_tag, { tag, position ->
            text_tag.text = tag
        }, {
            image_tag_delete.setOnClickListener {
                tagsArray.removeAt(pos())
                tagAdapter.notifyDataSetChanged()
            }
        })*/

        binding.imageTagAdd.setOnClickListener {
            if (!TextUtils.isEmpty(binding.edtTags.getString())) {
                tagsArray.add(binding.edtTags.getString())
                tagAdapter.updateData(tagsArray)
                binding.edtTags.setText("")
            }
        }


        //pTime
        val pTimeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
            binding.edtPrepTime.text = getTimer(hourOfDay, minute)
        }

        binding.edtPrepTime.setOnClickListener {
            val mTimePickerDialog = TimePickerDialog(
                activity,
                R.style.DialogTheme,
                pTimeSetListener,
                0, 0, true
            )
            mTimePickerDialog.show()
        }

        //cTime
        val cTimeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
            binding.edtCookTime.text = getTimer(hourOfDay, minute)
        }

        binding.edtCookTime.setOnClickListener {
            val mTimePickerDialog = TimePickerDialog(
                requireActivity(),
                R.style.DialogTheme,
                cTimeSetListener,
                0, 0, true
            )
            mTimePickerDialog.show()
        }

        //Serving
        binding.edtServeType.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setItems(SERVE_TYPE) { dialog, item ->
                binding.edtServeType.text = SERVE_TYPE[item]
                dialog.dismiss()
            }
            builder.show()
        }
    }

    private fun getTimer(hourOfDay: Int, minute: Int): String {
        return when {
            hourOfDay == 0 -> {
                String.format(Locale.US, "%02d", minute) + " min"
            }
            minute == 0 -> {
                String.format(Locale.US, "%02d", hourOfDay) + " hr"
            }
            else -> {
                String.format(Locale.US, "%02d", hourOfDay) + " hr  " + String.format(
                    Locale.US,
                    "%02d",
                    minute
                ) + " min"
            }
        }
    }

    fun getTitle(): String {
        return binding.edtTitle.text.toString()
    }

    fun getDesc(): String {
        return binding.edtDesc.text.toString()
    }

    fun getCuisine(): String {
        return binding.edtCuisine.text.toString()
    }

    fun getPTime(): String {
        return binding.edtPrepTime.text.toString()
    }

    fun getCTime(): String {
        return binding.edtCookTime.text.toString()
    }

    fun getServing(): String {
        return binding.edtServings.text.toString()
    }

    fun getServingType(): String {
        return binding.edtServeType.text.toString()
    }
}