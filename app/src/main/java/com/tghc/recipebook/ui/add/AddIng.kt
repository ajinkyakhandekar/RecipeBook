package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.view.View
import com.tghc.recipebook.constant.SIZE_ING
import com.tghc.recipebook.constant.UNITS
import com.tghc.recipebook.data.model.Ingredient
import com.tghc.recipebook.databinding.AddIngBinding
import com.tghc.recipebook.databinding.RowEditIngBinding
import com.tghc.recipebook.extention.getString
import com.tghc.recipebook.extention.isEmpty
import com.tghc.recipebook.extention.showAlertDialog
import com.tghc.recipebook.extention.textWatcher
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment

class AddIng(private val addFragment: AddFragment) : BaseFragment<AddIngBinding>(
    AddIngBinding::inflate
) {

    private lateinit var addIngAdapter: RecyclerAdapter<Ingredient, RowEditIngBinding>
    private val ingredient = addFragment.recipe.ingredient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!addFragment.flagEdit) {
            for (i in 0 .. SIZE_ING) {
                ingredient.add(Ingredient("", "", ""))
            }
        }

        addIngAdapter =
            binding.inRecyclerView.withAdapter(RowEditIngBinding::inflate) { ing, itemDetails ->
                val pos = itemDetails.position + 1
                binding.textRowIngCount.text = "$pos  ."
                binding.editRowIng.setText(ing.ingredient)
                binding.editRowIngNum.setText(ing.num)
                binding.textRowIngType.text = ing.type

            }

        addIngAdapter.setClickListeners = {
            binding.imageRowIngDelete.setOnClickListener {
                ingredient.removeAt(adapterPosition)
                addIngAdapter.updateData(ingredient)
            }

            binding.textRowIngType.setOnClickListener {
                showAlertDialog(builderFunction = {
                    setItems(UNITS) { dialog, item ->
                        binding.textRowIngType.text = UNITS[item]
                        val mIng = Ingredient(
                            ingredient[adapterPosition].ingredient,
                            ingredient[adapterPosition].num,
                            binding.textRowIngType.text.toString()
                        )

                        ingredient[adapterPosition] = mIng
                        dialog.dismiss()
                    }
                })
            }

            binding.editRowIng.textWatcher {
                val mIng = Ingredient(
                    binding.editRowIng.getString(),
                    ingredient[adapterPosition].num,
                    ingredient[adapterPosition].type
                )
                ingredient[adapterPosition] = mIng
            }

            binding.editRowIngNum.textWatcher {
                val mIng = Ingredient(
                    ingredient[adapterPosition].ingredient,
                    binding.editRowIngNum.getString(),
                    ingredient[adapterPosition].type
                )
                ingredient[adapterPosition] = mIng
            }
        }

        binding.fabIng.setOnClickListener {
            ingredient.add(Ingredient("", "", ""))
            addIngAdapter.updateData(ingredient)
        }
    }

    fun getIngredients(): ArrayList<Ingredient> {
        val ingredientsArrayList = ArrayList<Ingredient>()
        for (ing in ingredient) {
            if (isEmpty(ing.ingredient)) ingredientsArrayList.add(ing)
        }
        return ingredientsArrayList
    }
}