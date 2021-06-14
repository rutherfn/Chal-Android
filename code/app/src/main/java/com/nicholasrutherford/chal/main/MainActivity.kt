package com.nicholasrutherford.chal.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding: ActivityMainBinding? = null

    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel.launchNewsFeed()
        binding?.let { binding ->
            setupBottomNavigation(binding = binding)
        }

        viewModel.testFairy.init()
    }

    private fun setupBottomNavigation(binding: ActivityMainBinding) {
        binding.bvNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item -> viewModel.navigationItemSelected(item) }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_news_feed, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onCameraResult(resultCode, requestCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onBackPressed(supportFragmentManager.backStackEntryCount)
    }
}
