package com.tghc.recipebook.ui.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.firebase.firestore.FirebaseFirestore
import com.tghc.recipebook.constant.*
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.databinding.*
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {
    private lateinit var recipe: Recipe
    private lateinit var deleteDialog: Dialog
    private lateinit var tagAdapter: RecyclerAdapter<String, RowTagBinding>
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = arguments?.getString("recipeId")

        firebaseViewModel.getRecipe(FirebaseFirestore.getInstance(),recipeId!!)
            .observe(requireActivity()) { baseResponse ->
                baseResponse.response({
                    recipe = it
                    setData()
                }, {
                    toast(MSG_FIREBASE_ERROR)
                })
            }
    }

    private fun setData() {
        binding.textDisplayTitle.text = recipe.title
//        binding.text_display_desc.setDisplayText(recipe.description)
//        binding.text_display_notes.setDisplayText(recipe.description)

        //Display View
        addDisplayView(recipe.cuisine, "", cuisine)
        addDisplayView(recipe.pTime, "", pTime)
        addDisplayView(recipe.cTime, "", cTime)
        addDisplayView(recipe.servings, recipe.type, servings)

        //Ingredients
        for (ingredient in recipe.ingredient!!) {
            val ingBinding = RowDisplayIngBinding.inflate(LayoutInflater.from(context))

            ingBinding.textDisplayIng.text = "${ingredient.num} ${ingredient.type}"
            ingBinding.textDisplayAmt.text = ingredient.ingredient
            val checkBox: CheckBox = ingBinding.checkDisplayIng

            ingBinding.textDisplayIng.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
            ingBinding.textDisplayIng.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
            binding.includeDisplay.linDisplayIng.addView(ingBinding.root)
        }

        //Procedure
        for ((i, procedure) in recipe.procedure.withIndex()) {
            val proBinding = RowDisplayProBinding.inflate(LayoutInflater.from(context))

            val j = i + 1
            proBinding.textDisplayStep.text = "Step: $j"
            proBinding.textDisplayPro.text = procedure
            binding.includeDisplay.linDisplayPro.addView(proBinding.root)
        }

        //Tags
        val flexBoxLayoutManager = FlexboxLayoutManager(requireContext())
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START
        binding.includeDisplay.recyclerTag.layoutManager = flexBoxLayoutManager
        tagAdapter =
            binding.includeDisplay.recyclerTag.withAdapter(RowTagBinding::inflate) { tag, _ ->
                binding.textTag.text = tag
            }

        tagAdapter.setClickListeners = {
//            binding.imageTagDelete.hide()
        }

        binding.imageOverflow.setOnClickListener {
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
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }

        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun addDisplayView(text: String, type: String, detail: String) {
        if (text.isNotEmpty()) {
            val detailsBinding = RowDisplayDetailsBinding.inflate(LayoutInflater.from(context))

            detailsBinding.textDisplayDetails.text = detail
            detailsBinding.textDisplayDetailsName.text = ":      $text $type"

            binding.includeDisplay.linDisplayDetails.addView(detailsBinding.root)
        }
    }

    private fun dialogDelete() {
        deleteDialog = showAlertDialog(
            message = MSG_DELETE,
            isCancelableTouchOutside = true,
            isCancelable = true
        ) {
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
        firebaseViewModel.deleteRecipe(FirebaseFirestore.getInstance(), recipe.recipeId)
            .observe(requireActivity()) { baseResponse ->
                baseResponse.response({
                    findNavController().popBackStack()
                }, {
                    toast(MSG_FIREBASE_ERROR)
                })
            }
    }
}
