package com.tghc.recipebook.ui.add

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.SERVE_TYPE
import com.tghc.recipebook.extention.create
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.add_dt.*
import java.util.*
import kotlin.collections.ArrayList

class AddDet(private val addFragment: AddFragment) : Fragment() {

    lateinit var tagAdapter: RecyclerAdapter<String>
    lateinit var tagsArray: MutableList<String>
    val recipe = addFragment.recipe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_dt, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tagsArray = ArrayList()

        edt_title.setText(recipe.title)
        edt_desc.setText(recipe.desc)
        edt_cuisine.setText(recipe.cuisine)
        edt_servings.setText(recipe.servings)
        edt_serve_type.text = recipe.type
        edt_prep_time.text = recipe.pTime
        edt_cook_time.text = recipe.cTime
        tagsArray = recipe.tags


        //Todo: tags

        /*    val flexBoxLayoutManager = FlexboxLayoutManager(activity)
            flexBoxLayoutManager.flexDirection = FlexDirection.ROW
            flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START
            recycler_tag.layoutManager = flexBoxLayoutManager
            tagAdapter = TagAdapter(recipe.tags, true) { position ->
                tagsArray.removeAt(position)
                tagAdapter.notifyDataSetChanged()
            }
            recycler_tag.adapter = tagAdapter

            image_tag_add.setOnClickListener {
                if (!TextUtils.isEmpty(edt_tags.getText().toString().trim())) {
                    tagsArray.add(edt_tags.getText().toString().trim())
                    tagAdapter.notifyDataSetChanged()
                    edt_tags.setText("")
                }
            }*/

        //pTime
        val pTimeSetListener = OnTimeSetListener { view, hourOfDay, minute ->
            edt_prep_time.text = getTimer(hourOfDay, minute)
        }

       /* edt_prep_time.setOnClickListener {
            val mTimePickerDialog = TimePickerDialog(
                activity,
                R.style.DialogTheme,
                pTimeSetListener,
                0, 0, true
            )
            mTimePickerDialog.show()
        }*/

        //cTime
        val cTimeSetListener = OnTimeSetListener { view, hourOfDay, minute ->
            edt_cook_time.text = getTimer(hourOfDay, minute)
        }

       /* edt_cook_time.setOnClickListener {
            val mTimePickerDialog = TimePickerDialog(
                requireActivity(),
                R.style.DialogTheme,
                cTimeSetListener,
                0, 0, true
            )
            mTimePickerDialog.show()
        }*/

        //Serving
        edt_serve_type.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setItems(SERVE_TYPE) { dialog, item ->
                edt_serve_type.text = SERVE_TYPE[item]
                dialog.dismiss()
            }
            builder.show()
        }
    }

    private fun getTimer(hourOfDay: Int, minute: Int): String? {
        return if (hourOfDay == 0) {
            String.format(Locale.US, "%02d", minute) + " min"
        } else if (minute == 0) {
            String.format(Locale.US, "%02d", hourOfDay) + " hr"
        } else {
            String.format(Locale.US, "%02d", hourOfDay) + " hr  " + String.format(Locale.US, "%02d", minute) + " min"
        }
    }

    fun getTitle(): String {
        return edt_title.text.toString()
    }

    fun getDesc(): String {
        return edt_desc.text.toString()
    }

    fun getCuisine(): String {
        return edt_cuisine.text.toString()
    }

    fun getPTime(): String {
        return edt_prep_time.text.toString()
    }

    fun getCTime(): String {
        return edt_cook_time.text.toString()
    }

    fun getServing(): String {
        return edt_servings.text.toString()
    }

    fun getServingType(): String {
        return edt_serve_type.text.toString()
    }
}