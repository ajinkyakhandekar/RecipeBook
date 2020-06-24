package com.tghc.recipebook.ui.add

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.MSG_ADD_TITLE
import com.tghc.recipebook.constant.MSG_EXIT
import com.tghc.recipebook.constant.MSG_FIREBASE_ERROR
import com.tghc.recipebook.constant.MSG_RECIPE_SAVED
import com.tghc.recipebook.data.model.Item
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.AddPagerAdapter
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    lateinit var recipe: Recipe
    var imageUri = ArrayList<Uri>()
    private lateinit var dialog: Dialog
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var addDet: AddDet
    private lateinit var addIng: AddIng
    private lateinit var addPro: AddPro
    private lateinit var addPic: AddPic
    private lateinit var addSave: AddSave
    var flagEdit = false
    private var count = 0
    private var item = Item()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.fragment_add, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipe = arguments?.getSerializable("recipeData") as Recipe
        flagEdit = recipe.recipeId.isNotEmpty()

        addDet = AddDet(this)
        addIng = AddIng(this)
        addPro = AddPro(this)
        addPic = AddPic(this)
        addSave = AddSave(this)

        val fragmentList = arrayListOf(addDet, addIng, addPro, addPic, addSave)
        val fragmentTitle = arrayListOf("Details", "Ingredients", "Procedure", "Images", "Save")
        val addPagerAdapter = AddPagerAdapter(requireActivity(), fragmentList)
        viewpager_add.adapter = addPagerAdapter
        viewpager_add.offscreenPageLimit = 4

        TabLayoutMediator(tabs_add, viewpager_add) { tab, position ->
            tab.text = fragmentTitle[position]
        }.attach()

        fragmentBackPressed {
            dialogExit()
        }
    }

    fun saveRecipe() {
        if (TextUtils.isEmpty(addDet.getTitle())) {
            toast(MSG_ADD_TITLE)
            return
        }

        //recipe.userId = firebaseUser.uid
        recipe.title = addDet.getTitle()
        recipe.description = addDet.getDesc()
        recipe.cuisine = addDet.getCuisine()
        recipe.servings = addDet.getServing()
        recipe.type = addDet.getServingType()
        recipe.cTime = addDet.getCTime()
        recipe.pTime = addDet.getPTime()
        recipe.tags = addDet.tagsArray

        recipe.ingredient = addIng.getIngredients()
        recipe.procedure = addPro.getProcedure()
        //recipe.imagePath = addPic.getImages()
        recipe.notes = addSave.getNotes()

        item.chef = recipe.chef
        item.cuisine = recipe.cuisine
        item.title = recipe.title
        item.imagePath = recipe.imagePath

        saveData()
    }


    // save images
    // save recipe
    // save item
    private fun saveData() {
        count=0

        if (imageUri.size > 0) uploadImage(imageUri[0])
        else uploadRecipe()

    }

    private fun uploadImage(uri: Uri) {
        firebaseViewModel.postImage(uri).observe(requireActivity(), Observer {
            it.response({ imagePath ->
                 recipe.imagePath.add(imagePath)

                 count++
                 if (count < imageUri.size) {
                     uploadImage(imageUri[count])
                 }else{
                     uploadRecipe()
                 }
            }, {
                toast(MSG_FIREBASE_ERROR)
            })
        })
    }

    private fun uploadRecipe() {
        firebaseViewModel.postRecipe(recipe).observe(requireActivity(), Observer { baseResponse ->
            baseResponse.response({ recipeId ->
                uploadItem(recipeId)
            }, {
                toast(MSG_FIREBASE_ERROR)
            })
        })
    }

    private fun uploadItem(recipeId: String) {
        item.recipeId = recipeId
        firebaseViewModel.postItem(item).observe(requireActivity(), Observer { baseResponse ->
            baseResponse.response({
                toast(MSG_RECIPE_SAVED)
                navigateBack()
            }, {
                toast(MSG_FIREBASE_ERROR)
            })
        })
    }


    fun dialogExit() {
        dialog = showAlertDialog(MSG_EXIT,
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

    private fun navigateBack() {
        findNavController().popBackStack()
    }
}
