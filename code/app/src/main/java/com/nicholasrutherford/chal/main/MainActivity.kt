package com.nicholasrutherford.chal.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.challengesRedesignFragment
import com.nicholasrutherford.chal.databinding.ActivityMainBinding
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.more.MoreFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.let { main(it) }
        viewModel.testFairy.init()
    }

    private fun main(binding: ActivityMainBinding) {
        setupHomeForFirstToLoad()
        setupBottomNavigation(binding)
    }

    private fun setupHomeForFirstToLoad() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container,
            NewsFeedFragment(application),
            NewsFeedFragment(application).javaClass.simpleName
        )
            .commit()
    }

    private fun setupBottomNavigation(binding: ActivityMainBinding) {
        binding.bvNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.navigation_my_feed -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.container,
                            NewsFeedFragment(application),
                            NewsFeedFragment(application).javaClass.simpleName
                        )
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        return true
                    }
                    R.id.navigation_challenges -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.container,
                            challengesRedesignFragment(applicationContext), challengesRedesignFragment(applicationContext).javaClass.simpleName
                        )
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        return true
                    }
                    R.id.navigation_more -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.container,
                            MoreFragment(application),
                            MoreFragment(application).javaClass.simpleName
                        )
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        return true
                    }
                }
                return false
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
}
