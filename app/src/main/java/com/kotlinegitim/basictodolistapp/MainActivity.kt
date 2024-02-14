 package com.kotlinegitim.basictodolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kotlinegitim.basictodolistapp.ui.login.LoginFragment

 class MainActivity : AppCompatActivity() {
     lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    loadFragment(LoginFragment())
                    true
                }else -> false
            }
        }
    }
     private  fun loadFragment(fragment: Fragment){
         val transaction = supportFragmentManager.beginTransaction()
         transaction.replace(R.id.fragmentContainerView,fragment)
         transaction.commit()
}
 }