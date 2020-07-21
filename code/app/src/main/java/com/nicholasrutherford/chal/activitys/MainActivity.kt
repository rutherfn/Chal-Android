package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.fragments.ChallengesFragment
import com.nicholasrutherford.chal.fragments.HomeFragment
import com.nicholasrutherford.chal.fragments.SuggestedFriendsFragment
import com.nicholasrutherford.chal.fragments.dialogs.LoadingDialog


class MainActivity : AppCompatActivity() {

    private val fm = supportFragmentManager

    private val homeFragment = HomeFragment()
    private val challengesFragment = ChallengesFragment()
    private val suggestedFriendsFragment = SuggestedFriendsFragment()

    private val loadingDialog = LoadingDialog()

    private lateinit var clContainer: ConstraintLayout
    private lateinit var tbMain: Toolbar
    private lateinit var btNavigation: BottomNavigationView
    private lateinit var flMainContainer: FrameLayout
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main()
    }

    private fun main() {
        setupView()

        setupHomeForFirstToLoad()

        setupBottomNavigation()
    }

    private fun setupView() {
        setupIds()
        setupToolbar()
    }

    private fun setupIds() {
        mAuth = FirebaseAuth.getInstance()
  //      clContainer = findViewById(R.id.clContainer)
        tbMain = findViewById(R.id.tbMain)
        btNavigation = findViewById(R.id.bvNavigation)
//        flMainContainer = findViewById(R.id.flMainContainer)
    }

    private fun setupToolbar() {
        setSupportActionBar(tbMain)
        tbMain.title = ""
    }

    private fun setupHomeForFirstToLoad() {
        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, homeFragment.javaClass.simpleName)
            .commit()
    }

    private fun setupBottomNavigation() {
        btNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.navigation_home -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, homeFragment.javaClass.simpleName)
                            .commit()
                        return true
                    }
                    R.id.navigation_challenges -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container, challengesFragment, challengesFragment.javaClass.simpleName)
                            .commit()
                        return true
                    }
                    R.id.navigation_find_friends -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container, suggestedFriendsFragment, suggestedFriendsFragment.javaClass.simpleName)
                            .commit()
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

    private fun startSettingsActivity() {
        val intent = Intent(applicationContext, SettingsActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == R.id.navigation_debug) {

        } else if(id == R.id.navigation_log_out) {
            attemptToLogoutUser()
        } else if (id == R.id.navigation_settings) {
            startSettingsActivity()
        }
        return super.onOptionsItemSelected(item)
    }

}
