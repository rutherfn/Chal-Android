package com.nicholasrutherford.chal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.databinding.ActivityMainBinding
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.testfairy.TestFairy

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        main(binding!!)
        TestFairy.begin(this, "SDK-aPjHEtM8")
    }

    private fun main(binding: ActivityMainBinding) {
        setupView(binding)
        setupHomeForFirstToLoad()
        setupBottomNavigation(binding)
    }

    private fun setupView(binding: ActivityMainBinding) {
        setupToolbar(binding)
    }

    private fun setupToolbar(binding: ActivityMainBinding) {
        setSupportActionBar(binding.tbMain)
        binding.tbMain.title = ""
    }

    private fun setupHomeForFirstToLoad() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container,
            newsFeedRedesignFragment(
                this@MainActivity,
                applicationContext
            ),
            newsFeedRedesignFragment(
                this@MainActivity,
                applicationContext
            ).javaClass.simpleName
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
                            newsFeedRedesignFragment(
                                this@MainActivity,
                                applicationContext
                            ),
                            newsFeedRedesignFragment(
                                this@MainActivity,
                                applicationContext
                            ).javaClass.simpleName
                        )
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        binding?.tbMain?.visibleOrGone = false
                        return true
                    }
                    R.id.navigation_challenges -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.container,
                            challengesRedesignFragment(applicationContext), challengesRedesignFragment(applicationContext).javaClass.simpleName
                        )
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        binding?.tbMain?.visibleOrGone = false
                        return true
                    }
                    R.id.navigation_more -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.container,
                            moreFragment(
                                this@MainActivity,
                                applicationContext
                            ),
                            moreFragment(
                                this@MainActivity,
                                applicationContext
                            ).javaClass.simpleName
                        )
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        binding?.tbMain?.visibleOrGone = false
                        return true
                    }
                }
                return false
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
