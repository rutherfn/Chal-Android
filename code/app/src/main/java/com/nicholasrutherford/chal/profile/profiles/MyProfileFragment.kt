package com.nicholasrutherford.chal.profile.profiles

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentProfilesBinding
import com.nicholasrutherford.chal.ext.fragments.profile.ProfileFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.my_profile_layout.*

class MyProfileFragment(private val mainActivity: MainActivity, private val appContext: Context) :
    Fragment(),
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

    private fun updateTabState(bind: FragmentProfilesBinding) {
        viewModel?.let { myProfileViewModel ->
            updateTabs(myProfileViewModel.viewState.myFriendsTabActive, bind)
        }
    }

    private fun updateTabs(myFriendsTab: Boolean, bind: FragmentProfilesBinding) {
        println(myFriendsTab)
        val colorWhite = "#FFFFFF"
        val colorBlack = "#000000"
        // if (myFriendsTab) {
        //     bind.clProfile.tvProfileMyChallenges.setTextColor(Color.parseColor(colorWhite))
        //     Picasso.get().load(R.drawable.ic_calendar_white).into(bind.clProfile.ivProfileChallenges)
        //     bind.clProfile.tvProfileMyFriends.setTextColor(Color.parseColor(colorBlack))
        //     bind.clProfile.ivProfilePeopel?.background = R.drawable.ic_people
        // } else {
        //     bind.clProfile.tvProfileMyChallenges.setTextColor(Color.parseColor(colorBlack))
        //     Picasso.get().load(R.drawable.ic_calendar).into(bind.clProfile.ivProfileChallenges)
        //     bind.clProfile.tvProfileMyFriends.setTextColor(Color.parseColor(colorWhite))
        //     bind.clProfile.ivProfilePeopel?.background = R.drawable.ic_people_white
        // }
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
        bind.clProfile.ivProfileChallenges.setOnClickListener {
            viewModel?.onMyChallengesTabClicked()
       //     updateTabState(bind)
        }
        bind.clProfile.tvProfileMyChallenges.setOnClickListener {
            viewModel?.onMyChallengesTabClicked()
         //   updateTabState(bind)
        }
        bind.clProfile.tvProfileMyFriends.setOnClickListener {
            viewModel?.onMyFriendsTabClicked()
          //  updateTabState(bind)
        }
        bind.clProfile.tvProfileMyFriends.setOnClickListener {
            viewModel?.onMyFriendsTabClicked()
          //  updateTabState(bind)
        }
    }
}
