package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.databinding.ActivitySettingsBinding
import com.nicholasrutherford.chal.dialogfragments.DebugPasswordDialogFragment
import com.nicholasrutherford.chal.dialogfragments.LoadingDialogFragement
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.recycler.adapters.SettingsAdapter

class SettingsActivity : AppCompatActivity() {

    private var binding: ActivitySettingsBinding? = null

    private val profileArrayList = ArrayList<String>()
    private val helpArrayList = ArrayList<String>()
    private val phoneSettingArrayList = ArrayList<String>()

    private var adapterSettingsAdapter: SettingsAdapter? = null
    private var adapterSettingsTwoAdapter: SettingsAdapter? = null
    private var adapterSettingsThreeAdapter: SettingsAdapter? = null

    private val fm = supportFragmentManager
    private val debugPasswordDialog =
        DebugPasswordDialogFragment()
    private val loadingDialog =
        LoadingDialogFragement()

    private var mAuth: FirebaseAuth? = null

    private var typeface = Typeface()
    private var helper = Helper()

    private var isVerified: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        main()
    }

    private fun main() {
        setupView()

        setupToolbar()

        mAuth = FirebaseAuth.getInstance()
        isVerified = mAuth!!.currentUser!!.isEmailVerified

        clickListeners()

        setupRecyclerViewToAdapters()
    }

    private fun setupView() { setupTypefaceAndTextViewsColors() }

    private fun setupToolbar() {
        binding?.tbSettings?.ibBack?.visibleOrGone = true
        binding?.tbSettings?.tvSubTitle?.visibleOrGone = false

        applicationContext.let { binding?.tbSettings?.tvTitle?.let { it1 -> typeface.setTypefaceForHeaderBold(it1, it) } }

        toolbarBackCloseListener()
    }

    private fun toolbarBackCloseListener() { binding?.tbSettings?.ibBack?.setOnClickListener { startMainActivity() } }

    private fun setupTypefaceAndTextViewsColors() {

        typeface.setTypefaceForHeaderBold(binding!!.tvProfile, baseContext)
        typeface.setTypefaceForHeaderBold(binding!!.tvHelp, baseContext)
        typeface.setTypefaceForHeaderBold(binding!!.tvPhoneSettings, baseContext)

        helper.setTextViewColor(baseContext, binding!!.tvProfile, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding!!.tvHelp, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding!!.tvPhoneSettings, R.color.colorPrimary)
    }

    private fun clickListeners() {

        binding?.layoutButtonsSettings?.btnLogout?.setOnClickListener {
            attemptToLogoutUser()
        }

        binding?.layoutButtonsSettings?.btnDebug?.setOnClickListener {
            debugPasswordDialog.show(fm, "debugPasswordDialogFragment")
           // startDebugActivity()
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
        helpArrayList.add("• End User Agreement")
        helpArrayList.add("• Rate our app")

        phoneSettingArrayList.add("• View Permissions")
        phoneSettingArrayList.add("• Dark Mode")

        binding?.rvPhoneSettings?.layoutManager = LinearLayoutManager(baseContext)
        binding?.rvHelp?.layoutManager = LinearLayoutManager(baseContext)
        binding?.rvProfile?.layoutManager = LinearLayoutManager(baseContext)

        adapterSettingsAdapter = SettingsAdapter(applicationContext,profileArrayList)
        adapterSettingsTwoAdapter = SettingsAdapter(applicationContext,helpArrayList)
        adapterSettingsThreeAdapter = SettingsAdapter(applicationContext,phoneSettingArrayList)

        binding?.rvProfile?.adapter = adapterSettingsAdapter
        binding?.rvHelp?.adapter = adapterSettingsTwoAdapter
        binding?.rvPhoneSettings?.adapter = adapterSettingsThreeAdapter
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

    override fun onBackPressed() {
        startMainActivity()
        super.onBackPressed()
    }

}