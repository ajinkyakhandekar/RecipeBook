package com.tghc.recipebook.ui.add

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.MSG_FIREBASE_ERROR
import com.tghc.recipebook.constant.MSG_RECIPE_SAVED
import com.tghc.recipebook.data.modelRequest.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.AddPagerAdapter
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var dialog: Dialog
    lateinit var recipe: Recipe
    private var flagEdit = false
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var addDet: AddDet
    private lateinit var addIng: AddIng
    private lateinit var addPro: AddPro
    private lateinit var addPic: AddPic
    private lateinit var addSave: AddSave

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.fragment_add, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.isEmpty!!) {
            flagEdit = false
            recipe = Recipe()
        } else {
            flagEdit = true
            recipe = arguments?.getSerializable("recipeData") as Recipe
        }

        addDet = AddDet(this)
        addIng = AddIng(this)
        addPro = AddPro(this)
        addPic = AddPic(this)
        addSave = AddSave(this)

        val fragmentList = arrayListOf(addDet, addIng, addPro, addPic, addSave)
        val addPagerAdapter = AddPagerAdapter(requireActivity(), fragmentList)
        viewpager_add.adapter = addPagerAdapter
        viewpager_add.offscreenPageLimit = 4

        fragmentBackPressed {
            dialogExit()
        }
    }

    fun saveRecipe() {
        if (TextUtils.isEmpty(addDet.getTitle())) return

        //recipe.userId = firebaseUser.uid
        recipe.title = addDet.getTitle()
        recipe.desc = addDet.getDesc()
        recipe.cuisine = addDet.getCuisine()
        recipe.servings = addDet.getServing()
        recipe.type = addDet.getServingType()
        recipe.cTime = addDet.getCTime()
        recipe.pTime = addDet.getPTime()
        recipe.tags = addDet.tagsArray

        recipe.ingredient = addIng.getIngredients()
        recipe.procedure = addPro.getProcedure()
        recipe.images = addPic.getImages()
        recipe.notes = addSave.getNotes()

        firebaseViewModel.postRecipe(recipe).observe(requireActivity(), Observer { baseResponse ->
            response(baseResponse, {
                toast(MSG_RECIPE_SAVED)
            }, {
                toast(MSG_FIREBASE_ERROR)
            })
        })
    }

    private fun dialogExit() {
        dialog = showAlertDialog("Exit without saving?",
            isCancelable = true, isCancelableTouchOutside = true, builderFunction = {
                yesButton {
                    dialog.dismiss()
                    navigateBack()
                }
                noButton {
                    dialog.dismiss()
                }
            })
    }

    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         addPic.onActivityResult(requestCode, resultCode, data)
     }*/

    fun navigateBack() {
        findNavController().popBackStack()
    }
}
