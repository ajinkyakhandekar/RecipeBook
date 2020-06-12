package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.SIZE_PRO
import com.tghc.recipebook.constant.Utils
import com.tghc.recipebook.extention.create
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.add_pro.*
import java.util.*

class AddPro(private val addFragment: AddFragment) : Fragment() {
    private lateinit var addProAdapter: RecyclerAdapter<String>
    val recipe = addFragment.recipe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_pro, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (TextUtils.isEmpty(recipe.postId)) {
            for (i in 0 until SIZE_PRO) {
                recipe.procedure.add("")
            }
        }

       /* addProAdapter = AddProAdapter(recipe.procedure) { position ->
            recipe.procedure.removeAt(position)
            addProAdapter.notifyDataSetChanged()
        }

        binding.proRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.proRecyclerView.adapter = addProAdapter*/

        fab_pro.setOnClickListener {
            recipe.procedure.add("")
            addProAdapter.notifyDataSetChanged()
        }
    }

    fun getProcedure(): ArrayList<String> {
        val procedureArrayList = ArrayList<String>()
        for (pro in recipe.procedure) {
            if (!Utils().isEmpty(pro)) procedureArrayList.add(pro)
        }
        return procedureArrayList
    }
}