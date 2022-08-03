package com.tghc.recipebook.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tghc.recipebook.R
import com.tghc.recipebook.databinding.FragmentMainBinding
import com.tghc.recipebook.databinding.RowMainBinding
import com.tghc.recipebook.domain.model.Recipe
import com.tghc.recipebook.extention.navigate
import com.tghc.recipebook.extention.setGlideImage
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment
import com.tghc.recipebook.ui.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    private lateinit var mainAdapter: RecyclerAdapter<Recipe, RowMainBinding>
    private val recipeListViewModel: RecipeListViewModel by viewModels()
    private val recipeList = mutableListOf<Recipe>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter =
            binding.recyclerMain.withAdapter(RowMainBinding::inflate) { data, _ ->
                binding.txtTitle.text = data.title
                binding.txtMainCuisine.text = data.cuisine
                binding.imageMain.setGlideImage(R.drawable.p_recipe)
                /*if (data.imagePath.isNotEmpty())
                    binding.imageMain.setGlideImage(
                        source = data.imagePath[0],
                        placeholder = R.drawable.p_recipe
                    )*/
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

        setRecipeListObserver()
    }

    private fun setRecipeListObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                recipeListViewModel.uiState.collect { uiState ->
                    // binding.progressBar.isVisible = uiState.isLoading

                    recipeList.clear()
                    recipeList.addAll(uiState.recipeList)
                    mainAdapter.updateData(recipeList)
                }
            }
        }
    }
}
