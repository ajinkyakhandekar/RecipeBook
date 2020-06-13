package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tghc.recipebook.R
import com.tghc.recipebook.extention.create
import kotlinx.android.synthetic.main.add_save.*

class AddSave(private val addFragment: AddFragment) : Fragment() {

    val recipe = addFragment.recipe
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_pro, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_notes.setText(recipe.notes)


        text_save.setOnClickListener { addFragment.saveRecipe() }
        text_cancel.setOnClickListener { addFragment.dialogExit() }
    }

    fun getNotes(): String {
        return edit_notes.text.toString()
    }
}