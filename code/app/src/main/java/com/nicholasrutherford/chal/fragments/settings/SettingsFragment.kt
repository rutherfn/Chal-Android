package com.nicholasrutherford.chal.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.databinding.FragmentSettingsLayoutBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.fragments.debugPasswordDialog
import com.nicholasrutherford.chal.fragments.loadingDialog
import com.nicholasrutherford.chal.recycler.adapters.SettingsAdapter

class SettingsFragment : Fragment(), FragmentExt {

    private var binding: FragmentSettingsLayoutBinding? = null

    private val profileArrayList = ArrayList<String>()
    private val helpArrayList = ArrayList<String>()
    private val phoneSettingArrayList = ArrayList<String>()

    private var adapterSettingsAdapter: SettingsAdapter? = null
    private var adapterSettingsTwoAdapter: SettingsAdapter? = null
    private var adapterSettingsThreeAdapter: SettingsAdapter? = null

    private var mAuth: FirebaseAuth? = null
    private var isVerified: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsLayoutBinding.inflate(layoutInflater)
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun bind() {
        mAuth = FirebaseAuth.getInstance()
        isVerified = mAuth!!.currentUser!!.isEmailVerified

        setupRecyclerViewToAdapters()
    }

    private fun setupRecyclerViewToAdapters() {

        profileArrayList.add("• My Profile")

//        if(!isVerified) {
//            profileArrayList.add("• Activate Account")
//        }

        profileArrayList.add("• Notifications")
        profileArrayList.add("• Preferences")

        helpArrayList.add("• Email Us")
        helpArrayList.add("• Troubleshooting")
        helpArrayList.add("• End User Agreement")
        helpArrayList.add("• Rate our app")

        phoneSettingArrayList.add("• View Permissions")
        phoneSettingArrayList.add("• Dark Mode")

        context?.let {

            binding?.rvPhoneSettings?.layoutManager = LinearLayoutManager(it)
            binding?.rvHelp?.layoutManager = LinearLayoutManager(it)
            binding?.rvProfile?.layoutManager = LinearLayoutManager(it)

            adapterSettingsAdapter = SettingsAdapter(it, profileArrayList)
            adapterSettingsTwoAdapter = SettingsAdapter(it, helpArrayList)
            adapterSettingsThreeAdapter = SettingsAdapter(it, phoneSettingArrayList)
        }

        binding?.rvProfile?.adapter = adapterSettingsAdapter
        binding?.rvHelp?.adapter = adapterSettingsTwoAdapter
        binding?.rvPhoneSettings?.adapter = adapterSettingsThreeAdapter
    }

    override fun updateFragment() {

    }

    private fun attemptToLogoutUser() {
        fragmentManager?.let {
            loadingDialog.show(it, "LoadingDialog")

            val timer = object : CountDownTimer(3000, 100) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    mAuth?.signOut()

                    loadingDialog.dismiss()
                    startLoginActivity()
                }
            }
            timer.start()
        }
    }

    private fun startLoginActivity() {
        context?.let {
            val intent = Intent(it, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun clickListeners() {
        binding?.layoutButtonsSettings?.btnLogout?.setOnClickListener {
            attemptToLogoutUser()
        }

        binding?.layoutButtonsSettings?.btnDebug?.setOnClickListener {
            fragmentManager?.let {
                debugPasswordDialog.show(it, "debugPasswordDialogFragment")
            }
            // startDebugActivity()
        }
    }

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int {
        return R.id.container
    }

}