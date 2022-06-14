package com.example.ecommerce

import android.widget.ImageView
import androidx.fragment.app.Fragment

interface FragmentNavigation {
    fun navigateFrag(fragment : Fragment, addToStack : Boolean)

    fun navigateFragWithTransition(fragment: Fragment,addToStack: Boolean,transitionName : String,iv_image : ImageView)
}