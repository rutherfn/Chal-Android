package com.nicholasrutherford.chal.profile.profiles

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
import com.nicholasrutherford.chal.databinding.FragmentProfilesBinding
import com.nicholasrutherford.chal.ext.fragments.profile.ProfileFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso

class ProfileFragment(private val mainActivity: MainActivity, private val appContext: Context) : Fragment(),
    ProfileFragmentExtension {

    private var btNavigation: BottomNavigationView? = null
    private var viewModel: MyProfileViewModel? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentProfilesBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        btNavigation?.let { bottomNavigationView ->
            viewModel = fragmentManager?.let { fragmentManager ->
                MyProfileViewModel(mainActivity, appContext, fragmentManager, containerId(), bottomNavigationView)
            }
        }
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentProfilesBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbProfilePost.tvTitle, appContext)
        typeface.setTypefaceForHeaderRegular(bind.tbProfilePost.tvUpload, appContext)

        typeface.setTypefaceForHeaderRegular(bind.clProfile.tvProfileEdit, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clProfile.tvCurrentProfileName, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clProfile.tvCurrentProfileSubTitle, appContext)

        typeface.setTypefaceForBodyRegular(bind.clProfile.tvDescription, appContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clProfile.tvProfileMyChallenges, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clProfile.tvProfileMyFriends, appContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateView(bind: FragmentProfilesBinding) {
        // placeholder for right now
        Picasso.get().load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
            .into(bind.clProfile.ivProfile)

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        viewModel?.let { profileViewModel ->
            Glide.with(this).load(profileViewModel.viewState.profileImage).apply(options)
                .into(bind.clProfile.cvCurrentProfilePic)

            bind.clProfile.tvCurrentProfileName.text = profileViewModel.viewState.username
            bind.clProfile.tvDescription.text = profileViewModel.viewState.description
        }
    }

    override fun clickListeners(bind: FragmentProfilesBinding) {
        bind.clProfile.tvProfileEdit.setOnClickListener {
            viewModel?.onEditMyProfileClicked()
        }
    }
}