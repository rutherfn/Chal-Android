package com.nicholasrutherford.chal.profile.profiles

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentProfilesBinding
import com.nicholasrutherford.chal.ext.fragments.profile.ProfileFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyProfileFragment @Inject constructor(private val application: Application) : DaggerFragment(),
    ProfileFragmentExtension {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val myProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MyProfileViewModel::class.java)
    }

    private var btNavigation: BottomNavigationView? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentProfilesBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentProfilesBinding) {
        application.applicationContext?.let { context ->

            typeface.setTypefaceForHeaderBold(bind.tbProfilePost.tvTitle, context)

            typeface.setTypefaceForHeaderRegular(
                bind.clProfile.tvProfileEdit,
                context
            )
            typeface.setTypefaceForSubHeaderBold(
                bind.clProfile.tvCurrentProfileName,
                context
            )
            typeface.setTypefaceForSubHeaderRegular(
                bind.clProfile.tvCurrentProfileSubTitle,
                context
            )

            typeface.setTypefaceForBodyRegular(bind.clProfile.tvDescription, context)

            typeface.setTypefaceForSubHeaderRegular(
                bind.clProfile.tvProfileMyChallenges,
                context
            )
            typeface.setTypefaceForSubHeaderRegular(bind.clProfile.tvProfileMyFriends, context)
        }
    }

    override fun containerId(): Int {
        return R.id.container
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

    override fun clickListeners(bind: FragmentProfilesBinding) {
        bind.clProfile.tvProfileEdit.setOnClickListener {
            myProfileViewModel.onEditMyProfileClicked()
        }
        bind.clProfile.ivProfileChallenges.setOnClickListener {
            myProfileViewModel.onMyFriendsTabClicked()
       //     updateTabState(bind)
        }
        bind.clProfile.tvProfileMyChallenges.setOnClickListener {
            myProfileViewModel.onMyFriendsTabClicked()
         //   updateTabState(bind)
        }
        bind.clProfile.tvProfileMyFriends.setOnClickListener {
            myProfileViewModel.onMyFriendsTabClicked()
          //  updateTabState(bind)
        }
        bind.clProfile.tvProfileMyFriends.setOnClickListener {
            myProfileViewModel.onMyFriendsTabClicked()
          //  updateTabState(bind)
        }
    }

    override fun updateView(bind: FragmentProfilesBinding) {
        // placeholder for right now
        Picasso.get().load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
            .into(bind.clProfile.ivProfile)

        val options = RequestOptions()
            .placeholder(R.drawable.circle)
            .error(R.drawable.circle)

        Glide.with(this).load(myProfileViewModel.viewState.profileImage).apply(options)
            .into(bind.clProfile.cvCurrentProfilePic)

        bind.clProfile.tvCurrentProfileName.text = myProfileViewModel.viewState.username
        bind.clProfile.tvDescription.text = myProfileViewModel.viewState.description

        // functionality here for tab switches.
    }
}
