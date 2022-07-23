package com.tghc.recipebook.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tghc.recipebook.R
import com.tghc.recipebook.databinding.FragmentDetailBinding
import com.tghc.recipebook.databinding.FragmentMediaBinding
import com.tghc.recipebook.extention.create
import com.tghc.recipebook.ui.base.BaseFragment

class MediaFragment : BaseFragment<FragmentMediaBinding>(
    FragmentMediaBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
