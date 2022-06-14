package com.example.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fAuth = FirebaseAuth.getInstance()
        val currentUser = fAuth.currentUser
        if (currentUser != null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, HomeFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        }
    }

    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (addToStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun navigateFragWithTransition(
        fragment: Fragment,
        addToStack: Boolean,
        transitionName: String,
        iv_image: ImageView
    ) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .addSharedElement(iv_image, ViewCompat.getTransitionName(iv_image)!!)
                .replace(com.example.ecommerce.R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()    }
//    override fun navigateFragWithTransition(fragment: Fragment, addToStack: Boolean,transitionName : String,ivImage : ImageView) {
//
//    }

}