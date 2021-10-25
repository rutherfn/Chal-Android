package com.nicholasrutherford.chal.main.activity

import android.app.AlertDialog
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
                R.id.fragmentSignUp -> {
                    hideBottomNavigation()
                }
                R.id.fragmentCreateAccount -> {
                    hideBottomNavigation()
                }
                R.id.fragmentUploadPhoto -> {
                    hideBottomNavigation()
                }
                R.id.fragmentNewsFeed -> {
                    showBottomNavigation()
                }
                R.id.fragmentChallengesList -> {
                    showBottomNavigation()
                }
                R.id.fragmentMore -> {
                    showBottomNavigation()
                }
                R.id.fragmentChallengeDetail -> {
                    hideBottomNavigation()
                }
                R.id.fragmentUploadProgress -> {
                    hideBottomNavigation()
                }
                R.id.fragmentDebug -> {
                    hideBottomNavigation()
                }
            }
        }

        binding?.let { binding ->
            setupBottomNavigation(binding = binding)
        }
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
                R.id.fragmentSignUp -> {
                    navigator.navigateBack()
                }
                R.id.fragmentCreateAccount -> {
                    navigator.navigateBack()
                }
                R.id.fragmentUploadPhoto -> {
                    showClosingOutAppProgressAlert(
                        title = this.getString(R.string.creating_your_account),
                        message = this.getString(R.string.creating_your_account_desc)
                    )
                }
                R.id.fragmentNewsFeed -> {
                    finish()
                }
                R.id.fragmentChallengesList -> {
                    finish()
                }
                R.id.fragmentMore -> {
                    finish()
                }
                R.id.fragmentChallengeDetail -> {
                    navigator.navigateBack()
                }
                R.id.fragmentUploadProgress -> {
                    navigator.navigateBack()
                }
                R.id.fragmentDebug -> {
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

    fun showClosingOutAppProgressAlert(title: String, message: String) {
        val closingOutAppProgressAlertDialogBuilder = AlertDialog.Builder(this)

        closingOutAppProgressAlertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(this.getString(R.string.yes)) { dialog, _ ->
                finish()
            }
            .setNegativeButton(this.getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }

        val appProgressAlert = closingOutAppProgressAlertDialogBuilder.create()
        appProgressAlert.setTitle(title)

        appProgressAlert.show()
    }
}
