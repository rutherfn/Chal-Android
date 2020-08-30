package com.nicholasrutherford.chal.fragments.myprofile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.data.*
import com.nicholasrutherford.chal.databinding.ActivityMainBinding
import com.nicholasrutherford.chal.databinding.FragmentProfileBinding
import com.nicholasrutherford.chal.fragments.*
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.navigation.myprofile.MyProfileNavigationImpl
import com.nicholasrutherford.chal.recycler.adapters.MyProfileAdapter
import com.squareup.picasso.Picasso

class MyProfileFragment : Fragment(),
    FragmentExt {

    private var myProfileAdapter: MyProfileAdapter? = null
    private val typeface = Typeface()
    private val helper = Helper()
    private var binding: FragmentProfileBinding? = null
    private var myProfileNavigationImpl = MyProfileNavigationImpl()
    private var btNavigation: BottomNavigationView? = null
    private var screenContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation!!
        bind()
        clickListeners()
        return binding?.root
    }

    override fun onAttach(context: Context) {
        screenContext = context
        super.onAttach(context)
    }

    fun testSomethingHere() {
        binding?.tbProfile?.ibBack?.setOnClickListener {
            isExpired = true
            isWarning = true
        }
    }

    fun showBanner(text: String) {
        binding?.bannerMexsage?.clBannerMessage?.visibleOrGone = true
        binding?.bannerMexsage?.tvBannerMessage?.text = text
    }

    fun showBannerTwo(text: String) {
        binding?.bannerMessageChallengeAdded?.clBannerMessage?.visibleOrGone = true
        binding?.bannerMessageChallengeAdded?.tvBannerMessage?.text = text
    }

    fun showBannerThree(text: String) {
        Picasso.get().load(R.drawable.ic_plus).into(binding?.bannerMessageDayOneChallenge?.ivClose)
        binding?.bannerMessageDayOneChallenge?.clBannerMessage?.visibleOrGone = true
        binding?.bannerMessageDayOneChallenge?.tvBannerMessage?.text = text
    }

    fun showBannerFour(text: String) {
        binding?.bannerMessagePostCompleted?.clBannerMessage?.visibleOrGone = true
        binding?.bannerMessagePostCompleted?.tvBannerMessage?.text = text
    }

    fun showBannerFive(text: String) {
        binding?.bannerMessageWarningLayout?.clBannerMessage?.visibleOrGone = true
        binding?.bannerMessageWarningLayout?.tvBannerMessage?.text = text
    }

    fun showBannerSix(text: String) {
        binding?.bannerMessageExpiredLayout?.clBannerMessage?.visibleOrGone = true
        binding?.bannerMessageExpiredLayout?.tvBannerMessage?.text = text
    }

    override fun bind() {
        if(isUserProfileUpdated) {
            showBanner("Profile changes have been made")
            binding?.layoutProfile?.tvBodyMessage?.text = "Having fun on the app today!"
            isUserProfileUpdated =  false
        } else {
            binding?.layoutProfile?.tvBodyMessage?.text = "Here to have fun on the app by accomplishing fitness challenges, add me to learn more! "
        }

        if(isUserJoinedAChallenge) {
            showBannerTwo("Welcome to the #10DayPushUpChallenge!")
            isUserJoinedAChallenge = false
        }

        if(isDayOneChallenge) {
            showBannerThree("Log how your feeling after Day 1 of challenge")
            isDayOneChallenge = false
        }

        if(isGrateful) {
            showBannerFour("Sucessful! Click here to view post")
            isGrateful = false
        }

        if(isWarning) {
            showBannerFive("Please post towards your challenge")
            isWarning = false
        }
        if(isExpired) {
            showBannerSix("Sorry challenge post duration has expired")
            isExpired = false
        }

        binding?.tbProfile?.tvTbMyProfileUsername?.let { it -> typeface.setTypefaceForHeaderBold(it, it.context) }
        binding?.bannerMexsage?.tvBannerMessage?.let { it -> typeface.setTypefaceForBodyBold(it, it.context) }
        binding?.layoutProfile?.tvProfileFullName?.let { it -> typeface.setTypefaceForHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvProfileSubInfo?.let { it -> typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvBodyMessage?.let { typeface.setTypefaceForBodyBold(it, it.context)}

        Picasso.get().load(R.drawable.willplaceholder).into(binding?.layoutProfile?.cvProfilePicture)

        binding?.rvProfile?.isNestedScrollingEnabled = false

        binding?.rvProfile?.layoutManager = GridLayoutManager(context,3)

        val viewModel =
            MyProfileViewModel()
        myProfileAdapter = context?.let { MyProfileAdapter(viewModel, it, this) }
        binding?.rvProfile?.adapter = myProfileAdapter
    }

    override fun updateFragment() {

    }

    override fun clickListeners() {
        binding?.tbProfile?.ibBack?.setOnClickListener {
            isExpired = true
            isWarning = true
            fragmentManager?.let { it -> myProfileNavigationImpl.showHomeProfileFragment(true, it, containerId(), btNavigation!!) }
        }

        binding?.tbProfile?.ivEditProfile?.setOnClickListener {
            showEditProfileFragment()
        }
        binding?.bannerMexsage?.ivClose?.setOnClickListener {
            binding?.bannerMexsage?.clBannerMessage?.visibleOrGone = false
        }

        binding?.bannerMessageChallengeAdded?.ivClose?.setOnClickListener {
            binding?.bannerMessageChallengeAdded?.clBannerMessage?.visibleOrGone = false
        }

        binding?.bannerMessagePostCompleted?.ivClose?.setOnClickListener {
            binding?.bannerMessagePostCompleted?.clBannerMessage?.visibleOrGone = false

        }

        binding?.bannerMessageDayOneChallenge?.ivClose?.setOnClickListener {
            showChallengePostFragment()
        }

        binding?.layoutProfile?.btnViewFriends?.setOnClickListener {
            showMyFriendsFragment()
        }
        binding?.bannerMessageWarningLayout?.ivClose?.setOnClickListener {
            binding?.bannerMessageWarningLayout?.clBannerMessage?.visibleOrGone = false
        }
        binding?.bannerMessageExpiredLayout?.ivClose?.setOnClickListener {
            binding?.bannerMessageExpiredLayout?.clBannerMessage?.visibleOrGone = false
        }
    }

    private fun setupBottomNavigation(binding: ActivityMainBinding) {
        binding.bvNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.navigation_active_challenges -> {
                        return true
                    }
                    R.id.navigation_completed_challenges -> {

                        return true
                    }
                }
                return false
            }
        }

    private fun showChallengePostFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, challengePostFragment, challengePostFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    private fun showEditProfileFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, editProfileFragment, editProfileFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    private fun showMyFriendsFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, myFriendsFragment, myFriendsFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}