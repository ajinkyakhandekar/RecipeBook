package com.tghc.recipebook.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tghc.recipebook.R
import com.tghc.recipebook.extention.*
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.add_pro.*
import kotlinx.android.synthetic.main.row_edit_ing.*
import kotlinx.android.synthetic.main.row_edit_pro.*
import java.util.*

class AddPro(private val addFragment: AddFragment) : Fragment() {

    private lateinit var addProAdapter: RecyclerAdapter<String>
    private val procedure = addFragment.recipe.procedure

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_pro, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* if (TextUtils.isEmpty(recipe.postId)) {
             for (i in 0 until SIZE_PRO) {
                 recipe.procedure.add("")
             }
         }*/

        addProAdapter = pro_recycler_view.withAdapter(procedure, R.layout.row_edit_pro, { pro, position ->
            val pos = position + 1
            text_row_pro_count.text = "Step  $pos  :"
            edit_row_pro.setText(pro)
        }, {
            image_row_ing_delete.setOnClickListener {
                procedure.removeAt(pos())
                addProAdapter.notifyItemChanged(pos())
            }

            edit_row_pro.textWatcher {
                procedure[pos()] = edit_row_pro.getString()
            }
        })


        fab_pro.setOnClickListener {
            procedure.add("")
            addProAdapter.notifyDataSetChanged()
        }
    }

    fun getProcedure(): ArrayList<String> {
        val procedureArrayList = ArrayList<String>()
        for (pro in procedure) {
            if (isEmpty(pro)) procedureArrayList.add(pro)
        }
        return procedureArrayList
    }
}