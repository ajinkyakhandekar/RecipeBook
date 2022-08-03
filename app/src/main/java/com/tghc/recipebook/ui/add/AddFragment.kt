package com.tghc.recipebook.ui.add

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.tghc.recipebook.common.MSG_ADD_TITLE
import com.tghc.recipebook.ui.viewmodel.RecipeListViewModel
import com.tghc.recipebook.databinding.FragmentAddBinding
import com.tghc.recipebook.domain.model.Recipe
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.AddPagerAdapter
import com.tghc.recipebook.ui.base.BaseFragment

class AddFragment : BaseFragment<FragmentAddBinding>(
    FragmentAddBinding::inflate
) {
    lateinit var recipe: Recipe
    var imageUri = ArrayList<Uri>()
    private lateinit var dialog: Dialog
    private val recipeListViewModel: RecipeListViewModel by viewModels()
    private lateinit var addDet: AddDet
    private lateinit var addIng: AddIng
    private lateinit var addPro: AddPro
    private lateinit var addPic: AddPic
    private lateinit var addSave: AddSave
    var flagEdit = false
    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipe = arguments?.getSerializable("recipeData") as Recipe
//        flagEdit = (!recipe.recipeId.equals("0"))  //.isNotEmpty()

        addDet = AddDet(this)
        addIng = AddIng(this)
        addPro = AddPro(this)
        addPic = AddPic(this)
        addSave = AddSave(this)

        val fragmentList = arrayListOf<Fragment>(addDet, addIng, addPro, addPic, addSave)
        val fragmentTitle = arrayListOf("Details", "Ingredients", "Procedure", "Images", "Save")
        val addPagerAdapter = AddPagerAdapter(requireActivity(), fragmentList)
        binding.viewpagerAdd.adapter = addPagerAdapter
        binding.viewpagerAdd.offscreenPageLimit = 4

        TabLayoutMediator(binding.tabsAdd, binding.viewpagerAdd) { tab, position ->
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

        saveData()
    }


    // save images
    // save recipe
    // save item
    private fun saveData() {
        count = 0

        if (imageUri.size > 0) uploadImage(imageUri[0])
        else uploadRecipe()

    }

    private fun uploadImage(uri: Uri) {
        /* recipeViewModel.postImage(uri).observe(requireActivity()) {
             it.response({ imagePath ->
                 recipe.imagePath.add(imagePath)

                 count++
                 if (count < imageUri.size) {
                     uploadImage(imageUri[count])
                 } else {
                     uploadRecipe()
                 }
             }, {
                 toast(MSG_FIREBASE_ERROR)
             })
        }*/
    }

    private fun uploadRecipe() {
       /* recipeViewModel.postRecipe(FirebaseFirestore.getInstance(), recipe)
            .observe(requireActivity()) { baseResponse ->
                *//*baseResponse.response({ recipeId ->
                    toast(MSG_RECIPE_SAVED)
                    navigateBack()
                }, {
                    toast(MSG_FIREBASE_ERROR)
                })*//*
            }*/
    }


    fun dialogExit() {
       /* dialog = showAlertDialog(MSG_EXIT,
            isCancelable = true, isCancelableTouchOutside = true, builderFunction = {
                yesButton {
                    dialog.dismiss()
                    navigateBack()
                }
                noButton {
                    dialog.dismiss()
                }
            })*/
    }

    private fun navigateBack() {
       // findNavController().popBackStack()
    }
}
