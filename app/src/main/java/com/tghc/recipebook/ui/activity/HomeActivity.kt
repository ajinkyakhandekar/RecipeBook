package com.tghc.recipebook.ui.activity

import com.tghc.recipebook.databinding.ActivityHomeBinding
import com.tghc.recipebook.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(
    ActivityHomeBinding::inflate
)
