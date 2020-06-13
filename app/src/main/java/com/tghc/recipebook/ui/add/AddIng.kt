package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.SIZE_ING
import com.tghc.recipebook.constant.UNITS
import com.tghc.recipebook.data.modelRequest.Ingredient
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.add_ing.*
import kotlinx.android.synthetic.main.row_edit_ing.*
import java.util.*

class AddIng(private val addFragment: AddFragment) : Fragment() {

    lateinit var addIngAdapter: RecyclerAdapter<Ingredient>
    private val ingredient = addFragment.recipe.ingredient!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_ing, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*if (TextUtils.isEmpty(postId)) {
            for (i in 0 until SIZE_ING) {
                ingredient.add(Ingredient("", "", ""))
            }
        }*/
        
        addIngAdapter = in_recycler_view.withAdapter(ingredient, R.layout.row_edit_ing, { ing, position ->
            val pos = position + 1
            text_row_ing_count.text = "$pos  ."
            edit_row_ing.setText(ing.ingredient)
            edit_row_ing_num.setText(ing.num)
            text_row_ing_type.text = ing.type

        }, {
            image_row_ing_delete.setOnClickListener {
                ingredient.removeAt(pos())
                addIngAdapter.notifyItemChanged(pos())
            }

            text_row_ing_type.setOnClickListener {
                showAlertDialog(builderFunction = {
                    setItems(UNITS) { dialog, item ->
                        text_row_ing_type.text = UNITS[item]
                        val mIng = Ingredient(
                            ingredient[pos()].ingredient,
                            ingredient[pos()].num,
                            text_row_ing_type.text.toString()
                        )

                        ingredient[pos()] = mIng
                        dialog.dismiss()
                    }
                })
            }

            edit_row_ing.textWatcher {
                val mIng = Ingredient(
                    edit_row_ing.getString(),
                    ingredient[pos()].num,
                    ingredient[pos()].type
                )
                ingredient[pos()] = mIng
            }

            edit_row_ing_num.textWatcher {
                val mIng = Ingredient(
                    ingredient[pos()].ingredient,
                    edit_row_ing_num.getString(),
                    ingredient[pos()].type
                )
                ingredient[pos()] = mIng
            }
        })



        fab_ing.setOnClickListener {
            ingredient.add(Ingredient("", "", ""))
            addIngAdapter.notifyDataSetChanged()
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