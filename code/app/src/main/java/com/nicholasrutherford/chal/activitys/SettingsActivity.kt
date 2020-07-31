package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.fragments.dialogs.LoadingDialog
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.recycler.adapters.ProfileSettings

class SettingsActivity : AppCompatActivity() {

    private lateinit var tbSettings: Toolbar
    private lateinit var tvProfile: TextView
    private lateinit var rvProfile: RecyclerView
    private lateinit var tvHelp: TextView
    private lateinit var rvHelp: RecyclerView
    private lateinit var tvPhoneSettings: TextView
    private lateinit var rvPhoneSettings: RecyclerView
    private lateinit var btnLogoutSettings: Button

    private val profileArrayList = ArrayList<String>()
    private val helpArrayList = ArrayList<String>()
    private val phoneSettingArrayList = ArrayList<String>()

    private var adapterSettingsProfile: ProfileSettings? = null
    private var adapterSettingsProfileTwo: ProfileSettings? = null
    private var adapterSettingsProfileThree: ProfileSettings? = null

    private val fm = supportFragmentManager
    private val loadingDialog = LoadingDialog()

    private var mAuth: FirebaseAuth? = null

    private var typeface = Typeface()
    private var helper = Helper()

    private var isVerified: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        main()
    }

    private fun main() {

        setupView()

        clickListeners()

        setupRecyclerViewToAdapters()
    }

    private fun setupView() {

        setupIds()

        setupTypefaceAndTextViewsColors()
    }

    private fun setupIds() {

        mAuth = FirebaseAuth.getInstance()

        isVerified = mAuth!!.currentUser!!.isEmailVerified

        tbSettings = findViewById(R.id.tbSettings)
        tvProfile = findViewById(R.id.tvProfile)
        rvProfile = findViewById(R.id.rvProfile)
        tvHelp = findViewById(R.id.tvHelp)
        rvHelp = findViewById(R.id.rvHelp)
        tvPhoneSettings = findViewById(R.id.tvPhoneSettings)
        rvPhoneSettings = findViewById(R.id.rvPhoneSettings)
        btnLogoutSettings = findViewById(R.id.btnLogOutSettings)

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(tbSettings)

        supportActionBar!!.title = "Settings"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTypefaceAndTextViewsColors() {

        typeface.setTypefaceForHeaderBold(tvProfile, baseContext)
        typeface.setTypefaceForHeaderBold(tvHelp, baseContext)
        typeface.setTypefaceForHeaderBold(tvPhoneSettings, baseContext)

        helper.setTextViewColor(baseContext, tvProfile, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvHelp, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvPhoneSettings, R.color.colorPrimary)
    }

    private fun clickListeners() {

        btnLogoutSettings.setOnClickListener {
            attemptToLogoutUser()
        }
    }

    private fun setupRecyclerViewToAdapters() {

        profileArrayList.add("• My Profile")

        if(!isVerified) {
            profileArrayList.add("• Activate Account")
        }

        profileArrayList.add("• Notifications")
        profileArrayList.add("• Preferences")

        helpArrayList.add("• Email Us")
        helpArrayList.add("• Troubleshooting")
        helpArrayList.add("• End Uer Agreement")
        helpArrayList.add("• Rate our app")

        phoneSettingArrayList.add("• View Permissions")
        phoneSettingArrayList.add("• Dark Mode")
        phoneSettingArrayList.add("• Other Stuff")

        rvPhoneSettings.layoutManager = LinearLayoutManager(baseContext)
        rvHelp.layoutManager = LinearLayoutManager(baseContext)
        rvProfile.layoutManager = LinearLayoutManager(baseContext)

        adapterSettingsProfile = ProfileSettings(applicationContext,profileArrayList)
        adapterSettingsProfileTwo = ProfileSettings(applicationContext,helpArrayList)
        adapterSettingsProfileThree = ProfileSettings(applicationContext,phoneSettingArrayList)

        rvProfile.adapter = adapterSettingsProfile
        rvHelp.adapter = adapterSettingsProfileTwo
        rvPhoneSettings.adapter = adapterSettingsProfileThree
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

    private fun startLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if(item.itemId == android.R.id.home) {
            startMainActivity()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() {
        startMainActivity()
        super.onBackPressed()
    }

}