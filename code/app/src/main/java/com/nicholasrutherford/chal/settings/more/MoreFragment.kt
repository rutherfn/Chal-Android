package com.nicholasrutherford.chal.settings.more

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentMoreBinding
import com.nicholasrutherford.chal.ext.fragments.more.MoreFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface

class MoreFragment(private val mainActivity: MainActivity, private val appContext: Context): Fragment(),
    MoreFragmentExtension {

    private var viewModel: MoreViewModel? = null
    private var btNavigation: BottomNavigationView? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentMoreBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        btNavigation?.let { bottomNavigationView ->
            viewModel = fragmentManager?.let { fragmentManager ->
                MoreViewModel(mainActivity, appContext, fragmentManager, containerId(), bottomNavigationView)
            }
        }
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentMoreBinding) {
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

    override fun containerId(): Int {
        return R.id.container
    }

    override fun clickListeners(bind: FragmentMoreBinding) {
        bind.tbMore.ibMoreBack.setOnClickListener {
            // take the user to the my feed menu
        }
        bind.tbMore.ivBackgroundClose.setOnClickListener {
            // take user to the my feed menu
        }
        bind.clSvMore.svHome.setOnClickListener {
            // ideally we should hide the menu here, and show the search view layout once its implemented
        }
        bind.clMore.cvMyProfile.setOnClickListener {
            viewModel?.let { moreViewModel ->
                moreViewModel.onMyProfileClicked()
            }
            // we should load in the users profile fragment
        }
        bind.clMore.cvUploadPost.setOnClickListener {
            viewModel?.let { moreViewModel ->
                moreViewModel.onUploadProgressClicked()
            }
        }
        bind.clMore.cvSettings.setOnClickListener {
            //load in the settings layout
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
        bind.clMore.btnSignOutAccount.setOnClickListener {
            viewModel?.let { moreViewModel ->
                moreViewModel.onSignOutAccountClicked()
            }
        }
    }

    override fun updateView(bind: FragmentMoreBinding) {
        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        viewModel?.let { moreViewModel ->
            Glide.with(this).load(moreViewModel.viewState.profilePictureDirectory).apply(options)
                .into(bind.clMore.cvMyProfilePic)
        }
    }

}