package com.tghc.recipebook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.MSG_FIREBASE_ERROR
import com.tghc.recipebook.data.model.Item
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.row_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var mainAdapter: RecyclerAdapter<Item>
    private var itemList: MutableList<Item> = ArrayList()
    private var page = 0
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.fragment_main, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter = recycler_main.withAdapter(itemList, R.layout.row_main, { data, position ->
            txt_title.text = data.title
            txt_main_cuisine.text = data.cuisine
            image_main.setGlideImage(source = data.imagePath[0], placeholder = R.drawable.p_recipe)
        }, {
            itemView.setOnClickListener {
                navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(itemList[pos()].recipeId))
            }
        })

        getItemsAPI(page)

        recycler_main.addPagination(isLastPage, isLoading) {
            isLoading = true
            getItemsAPI(page)
        }

        fab_main.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToAddFragment(Recipe()))
        }
    }

    private fun getItemsAPI(page:Int){
        firebaseViewModel.getItemList(page).observe(requireActivity(), Observer { baseResponse ->
            baseResponse.response({
                itemList.addAll(it)
                mainAdapter.notifyDataSetChanged()
                this.page++
            }, {
                toast(MSG_FIREBASE_ERROR)
            })
        })
    }
}
