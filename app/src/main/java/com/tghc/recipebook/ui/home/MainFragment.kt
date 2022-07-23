package com.tghc.recipebook.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.MSG_FIREBASE_ERROR
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.databinding.FragmentMainBinding
import com.tghc.recipebook.databinding.RowMainBinding
import com.tghc.recipebook.extention.navigate
import com.tghc.recipebook.extention.response
import com.tghc.recipebook.extention.setGlideImage
import com.tghc.recipebook.extention.toast
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var mainAdapter: RecyclerAdapter<Recipe, RowMainBinding>
    private var recipeList: MutableList<Recipe> = ArrayList()
    private var page = 0
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter =
            binding.recyclerMain.withAdapter(RowMainBinding::inflate) { data, _ ->
                binding.txtTitle.text = data.title
                binding.txtMainCuisine.text = data.cuisine
                if (data.imagePath.isNotEmpty())
                    binding.imageMain.setGlideImage(
                        source = data.imagePath[0],
                        placeholder = R.drawable.p_recipe
                    )
            }
        mainAdapter.setClickListeners = {
            binding.relativeRowMain.setOnClickListener {
                navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(
                        recipeList[adapterPosition].recipeId
                    )
                )
            }
        }
        getItemsAPI(page)

        /* recycler_main.addPagination(isLastPage, isLoading) {
             isLoading = true
             getItemsAPI(page)
         }*/

        binding.fabMain.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToAddFragment(Recipe()))
        }
    }

    private fun getItemsAPI(page: Int) {
        firebaseViewModel.getRecipeList(FirebaseFirestore.getInstance(), page)
            .observe(requireActivity()) { baseResponse ->
                baseResponse.response({
                    recipeList.addAll(it)
                    mainAdapter.updateData(recipeList)
                    this.page++
                }, {
                    toast(MSG_FIREBASE_ERROR)
                })
            }
    }
}
