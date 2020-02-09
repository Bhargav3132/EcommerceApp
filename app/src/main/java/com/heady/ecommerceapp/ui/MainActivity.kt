package com.heady.ecommerceapp.ui

import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.ui.base.BaseActivity
import com.heady.ecommerceapp.ui.fragment.CategoryFragment

class MainActivity : BaseActivity() {

    override fun defineLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initializeComponents() {

        replaceFragment(R.id.flContainer, supportFragmentManager, CategoryFragment.getInstance())

    }


}
