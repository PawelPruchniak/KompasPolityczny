package com.example.kompaspolityczny

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.kompaspolityczny.databinding.ActivityMainBinding
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initializing JodaTimeAndroid before using it
        JodaTimeAndroid.init(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigation()

    }

    override fun onSupportNavigateUp()
            = navigateUp(findNavController(R.id.nav_host_fragment), binding.drawerLayout)

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)

        setSupportActionBar(binding.toolbar)

        binding.navigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            val toolBar = supportActionBar ?: return@addOnDestinationChangedListener
            when(destination.id) {
                R.id.home -> {
                    toolBar.setDisplayShowTitleEnabled(false)
                    binding.heroImage.visibility = View.VISIBLE
                }
                else -> {
                    toolBar.setDisplayShowTitleEnabled(true)
                    binding.heroImage.visibility = View.GONE
                }
            }
        }
    }

}