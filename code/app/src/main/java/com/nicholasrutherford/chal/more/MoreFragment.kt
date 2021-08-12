package com.nicholasrutherford.chal.more

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentMoreBinding
import com.nicholasrutherford.chal.ext.fragments.more.MoreFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MoreFragment @Inject constructor(private val application: Application) : DaggerFragment(), MoreFragmentExtension {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val moreViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MoreViewModel::class.java)
    }

    var btNavigation: BottomNavigationView? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentMoreBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentMoreBinding) {
        application.applicationContext?.let { appContext ->
            typeface.setTypefaceForHeaderBold(bind.clMore.tvChalMenu, appContext)

            typeface.setTypefaceForBodyBold(bind.clMore.tvMyProfile, appContext)
            typeface.setTypefaceForBodyBold(bind.clMore.tvUploadPost, appContext)
            typeface.setTypefaceForBodyBold(bind.clMore.tvSettings, appContext)
            typeface.setTypefaceForBodyBold(bind.clMore.tvChat, appContext)
            typeface.setTypefaceForBodyBold(bind.clMore.tvMyFeed, appContext)
            typeface.setTypefaceForBodyBold(bind.clMore.tvChallenges, appContext)

            typeface.setTypefaceForSubHeaderBold(bind.clMore.btnSignOutAccount, appContext)
            typeface.setTypefaceForSubHeaderRegular(bind.clMore.tvAllRightsReserved, appContext)
        }
    }

    override fun clickListeners(bind: FragmentMoreBinding) {
        bind.tbMore.ibMoreBack.setOnClickListener {
            // take the user to the my feed menu
        }
        bind.clMore.cvMyProfile.setOnClickListener {
            moreViewModel.onMyProfileClicked()
        }
        bind.clMore.cvUploadPost.setOnClickListener {
            moreViewModel.onUploadProgressClicked()
        }
        bind.clMore.cvSettings.setOnClickListener {
            // load in the settings layout
        }
        bind.clMore.cvChat.setOnClickListener {
            // we should should in the chat layout
        }
        bind.clMore.cvMyFeed.setOnClickListener {
            // we should load in the my feed layout
        }
        bind.clMore.cvChallenges.setOnClickListener {
            // we should load in the challenges ;ayout
        }
        bind.clMore.cvReportBug.setOnClickListener {
            moreViewModel.onReportBugClicked()
        }
        bind.clMore.btnSignOutAccount.setOnClickListener {
            moreViewModel.onSignOutAccountClicked()
        }
    }

    override fun updateView(bind: FragmentMoreBinding) {
        val options = RequestOptions()
            .placeholder(R.drawable.circle)
            .error(R.drawable.circle)

        Glide.with(this).load(moreViewModel.viewState.profilePictureDirectory).apply(options)
                .into(bind.clMore.cvMyProfilePic)
    }
}
