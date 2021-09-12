package com.nicholasrutherford.chal.main.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.main.activity.databinding.ActivityMainBinding
import com.nicholasrutherford.chal.main.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    var binding: ActivityMainBinding? = null

    private lateinit var navHostFragment: Fragment

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        navHostFragment =
            requireNotNull(supportFragmentManager.findFragmentById(R.id.container))

        val navController = navHostFragment.findNavController()
        navigator.navController = navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navSplash -> {
                    hideBottomNavigation()
                }
                R.id.fragmentLogin -> {
                    hideBottomNavigation()
                }
                R.id.fragmentForgotPassword -> {
                    hideBottomNavigation()
                }
            }
        }

        binding?.let { binding ->
            setupBottomNavigation(binding = binding)
        }

        // binding?.tbBack?.ibBack?.setOnClickListener {
        //     navigator.navigateBack()
        // }
    }

    private fun setupBottomNavigation(binding: ActivityMainBinding) {
        binding.bvNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item -> viewModel.navigationItemSelected(item) }

    private fun hideBottomNavigation() {
        binding?.let { bind ->
            bind.bvNavigation.visibleOrGone = false
        }
    }

    private fun showBottomNavigation() {
        binding?.let { bind ->
            bind.bvNavigation.visibleOrGone = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_news_feed, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onCameraResult(resultCode, requestCode, data)
    }

    override fun onBackPressed() {
        val navController = navHostFragment.findNavController()

        navController.currentDestination?.let { navDestination ->
            when (navDestination.id) {
                R.id.fragmentLogin -> {
                    finish()
                }
                R.id.fragmentForgotPassword -> {
                    navigator.navigateBack()
                }
                else -> {
                    finish()
                }
            }
        }?: run {
            finish()
        }
    }
}
