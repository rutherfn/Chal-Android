package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.databinding.ActivityMainBinding
import com.nicholasrutherford.chal.fragments.*
import com.nicholasrutherford.chal.helpers.visibleOrGone

class MainActivity : AppCompatActivity() {

    private val fm = supportFragmentManager

    var binding : ActivityMainBinding? = null

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        main(binding!!)
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
        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, homeFragment.javaClass.simpleName)
            .commit()
    }

    private fun setupBottomNavigation(binding: ActivityMainBinding) {
        binding.bvNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.navigation_my_feed-> {
                        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, homeFragment.javaClass.simpleName)
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        binding?.tbMain?.visibleOrGone = false
                        return true
                    }
                    R.id.navigation_challenges -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container, challengesFragment, challengesFragment.javaClass.simpleName)
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        binding?.tbMain?.visibleOrGone = false
                        return true
                    }
//                    R.id.navigation_search -> {
//                        supportFragmentManager.beginTransaction().replace(R.id.container, searchPeopleFragment, searchPeopleFragment.javaClass.simpleName)
//                            .commit()
//                        binding?.bvNavigation?.visibleOrGone = true
//                        binding?.tbMain?.visibleOrGone = false
//                        return true
//                    }
                    R.id.navigation_more -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container, settingFragment, settingFragment.javaClass.simpleName)
                            .commit()
                        binding?.bvNavigation?.visibleOrGone = true
                        binding?.tbMain?.visibleOrGone = false
                        return true
                    }

                }
                return false
            }
        }

    private fun attemptToLogoutUser() {

        loadingDialog.show(fm, "LoadingDialog")

        val timer = object: CountDownTimer(3000, 100) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                mAuth?.signOut()

                loadingDialog.dismiss()
                startLoginActivity()
            }
        }
        timer.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun startLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_debug -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, debugFragment, debugFragment.javaClass.simpleName)
                    .commit()
                binding?.tbMain?.visibleOrGone = true
                binding?.bvNavigation?.visibleOrGone = false
            }
            R.id.navigation_log_out -> {
                attemptToLogoutUser()
            }
            R.id.navigation_profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, myProfileFragment, myProfileFragment.javaClass.simpleName)
                    .commit()
                binding?.bvNavigation?.visibleOrGone = true
                binding?.tbMain?.visibleOrGone = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
