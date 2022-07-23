package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.view.View
import com.tghc.recipebook.databinding.AddSaveBinding
import com.tghc.recipebook.ui.base.BaseFragment

class AddSave(private val addFragment: AddFragment) : BaseFragment<AddSaveBinding>(
    AddSaveBinding::inflate
) {
    val recipe = addFragment.recipe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // if (!addFragment.flagEdit) {
        binding.editNotes.setText(recipe.notes)
        //  }


        binding.textSave.setOnClickListener { addFragment.saveRecipe() }
        binding.textCancel.setOnClickListener { addFragment.dialogExit() }
    }

    fun getNotes(): String {
        return binding.editNotes.text.toString()
    }
}