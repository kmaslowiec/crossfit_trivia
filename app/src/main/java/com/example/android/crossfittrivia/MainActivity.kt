package com.example.android.crossfittrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* That is required to work with FragmentContainerView. Outside on create we can use:
           val navController = this.findNavController(R.id.nav_host_fragment) */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        //this updated builder indicates which fragments, are top fragments that have no up button
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.startFragment,
          /*  R.id.resultsFragment,
            R.id.gameFragment,
            R.id.noRepFragment*/
        ).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }
}