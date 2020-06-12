package com.tghc.recipebook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tghc.recipebook.R
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.create
import com.tghc.recipebook.extention.response

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.fragment_main, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
