package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.SIZE_ING
import com.tghc.recipebook.constant.Utils
import com.tghc.recipebook.data.modelRequest.Ingredient
import com.tghc.recipebook.extention.create
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.add_ing.*
import java.util.*

class AddIng(private val addFragment: AddFragment) : Fragment() {

    lateinit var addIngAdapter: RecyclerAdapter<Ingredient>
val recipe = addFragment.recipe
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_ing, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (TextUtils.isEmpty(recipe.postId)) {
            for (i in 0 until SIZE_ING) {
                recipe.ingredients?.add(Ingredient("", "", ""))
            }
        }

       /* addIngAdapter = AddIngAdapter(requireContext(), recipe.ingredients!!) { position ->
            recipe.ingredients?.removeAt(position)
            addIngAdapter.notifyDataSetChanged()
        }

        inRecyclerView.layoutManager = LinearLayoutManager(activity)
        inRecyclerView.adapter = addIngAdapter*/

        fab_ing.setOnClickListener {
            recipe.ingredients?.add(Ingredient("", "", ""))
            addIngAdapter.notifyDataSetChanged()
        }
    }

    fun getIngredients(): ArrayList<Ingredient> {
        val ingredientsArrayList = ArrayList<Ingredient>()
        for (ing in recipe.ingredients!!) {
            if (!Utils().isEmpty(ing.ingredient)) ingredientsArrayList.add(ing)
        }
        return ingredientsArrayList
    }
}