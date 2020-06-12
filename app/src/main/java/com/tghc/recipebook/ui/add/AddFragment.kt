package com.tghc.recipebook.ui.add

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tghc.recipebook.R
import com.tghc.recipebook.data.modelRequest.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.*

class AddFragment : Fragment() {

    private lateinit var dialog: Dialog
    lateinit var recipe: Recipe
    private var flagEdit=false
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

        if(arguments?.isEmpty!!){
            flagEdit = false
            recipe = Recipe()
        }else{
            flagEdit = true
            recipe = arguments?.getSerializable("recipeData") as Recipe
        }

         addDet = AddDet(this)
         addIng = AddIng(this)
         addPro = AddPro(this)
        addPic = AddPic(this)
        addSave = AddSave(this)


        firebaseViewModel.postRecipe(recipe).observe(requireActivity(), Observer { baseResponse ->
            response(baseResponse, {

            }, {

            })
        })

        fragmentBackPressed {
            dialogSave()
        }
    }

    private fun dialogSave() {
        dialog = showAlertDialog("Exit without saving?",
            isCancelable = true, isCancelableTouchOutside = true, builderFunction = {
                yesButton {
                    dialog.dismiss()
                    findNavController().popBackStack()
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

}
