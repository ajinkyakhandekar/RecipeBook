package com.tghc.recipebook.ui.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.*
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.include_display.*
import kotlinx.android.synthetic.main.include_display.view.*
import kotlinx.android.synthetic.main.row_display_details.view.*
import kotlinx.android.synthetic.main.row_display_ing.view.*
import kotlinx.android.synthetic.main.row_display_pro.view.*
import kotlinx.android.synthetic.main.row_tag.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var recipe: Recipe
    private lateinit var deleteDialog: Dialog
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.fragment_detail, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = arguments?.getString("recipeId")

        firebaseViewModel.getRecipe(recipeId!!).observe(requireActivity(), Observer { baseResponse ->
            baseResponse.response({
                recipe = it
                setData()
            }, {
                toast(MSG_FIREBASE_ERROR)
            })
        })
    }

    private fun setData() {
        text_display_title.text = recipe.title
        text_display_desc.setDisplayText(recipe.description)
        text_display_notes.setDisplayText(recipe.description)

        //Display View
        addDisplayView(recipe.cuisine, "", cusine)
        addDisplayView(recipe.pTime, "", pTime)
        addDisplayView(recipe.cTime, "", cTime)
        addDisplayView(recipe.servings, recipe.type, servings)

        //Ingredients
        for (ingredient in recipe.ingredient!!) {
            val ingDetails = inflate1(R.layout.row_display_ing)

            ingDetails.text_display_ing.text = "${ingredient.num} ${ingredient.type}"
            ingDetails.text_display_amt.text = ingredient.ingredient
            val checkBox: CheckBox = ingDetails.check_display_ing

            ingDetails.text_display_ing.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
            ingDetails.text_display_ing.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
            include_display.lin_display_ing.addView(ingDetails)
        }

        //Procedure
        for ((i, procedure) in recipe.procedure.withIndex()) {

            val proDetails = inflate1(R.layout.row_display_pro)

            val j = i + 1
            proDetails.text_display_step.text = "Step: $j"
            proDetails.text_display_pro.text = procedure
            include_display.lin_display_pro.addView(proDetails)
        }

        //Tags
        val flexBoxLayoutManager = FlexboxLayoutManager(requireContext())
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START
        include_display.recycler_tag.layoutManager = flexBoxLayoutManager
        include_display.recycler_tag.withAdapter(recipe.tags, R.layout.row_tag, { tag, position ->
            text_tag.text = tag
        }, {
            image_tag_delete.hide()
        })

        image_overflow.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setCancelable(true)
            builder.setItems(OVERFLOW_TYPE) { dialog, item ->
                when (item) {
                    0 -> {
                        navigate(DetailFragmentDirections.actionDetailFragmentToAddFragment(recipe))
                    }
                    1 -> dialogDelete()
                }
                dialog.dismiss()
            }
            builder.setNegativeButton("Cancel") { dialog, item ->
                dialog.dismiss()
            }
            builder.show()
        }

        image_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun addDisplayView(text: String, type: String, detail: String) {
        if (text.isNotEmpty()) {
            val displayDetails = inflate1(R.layout.row_display_details)
            displayDetails.text_display_details.text = detail
            displayDetails.text_display_details_name.text = ":      $text $type"

            include_display.lin_display_details.addView(displayDetails)
        }
    }

    private fun dialogDelete() {
        deleteDialog = showAlertDialog(message = MSG_DELETE, isCancelableTouchOutside = true, isCancelable = true) {
            yesButton {
                deleteDialog.dismiss()
                deleteItem()
            }
            noButton {
                deleteDialog.dismiss()
            }
        }
    }

    private fun deleteItem() {
        firebaseViewModel.deleteRecipe(recipe.itemId, recipe.recipeId)
            .observe(requireActivity(), Observer { baseResponse ->
                baseResponse.response({
                    findNavController().popBackStack()
                }, {
                    toast(MSG_FIREBASE_ERROR)
                })
            })
    }
}
