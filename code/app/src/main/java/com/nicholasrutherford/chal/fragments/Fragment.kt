package com.nicholasrutherford.chal.fragments

import android.content.Context
import com.nicholasrutherford.chal.account.createaccount.CreateAccountFragment
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.account.signup.SignUpFragment
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.login.LoginFragment
import com.nicholasrutherford.chal.account.signup.SignUpActivity
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.challengesredesign.challengedetails.ChallengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignFragment
import com.nicholasrutherford.chal.dialogfragments.*
import com.nicholasrutherford.chal.screens.challenges.ChallengesFragment
import com.nicholasrutherford.chal.fragments.myfriends.MyFriendsFragment
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileFragment
import com.nicholasrutherford.chal.fragments.postprogress.ChallengePostFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedRedesignFragment
import com.nicholasrutherford.chal.profile.editprofile.EditMyProfileFragment
import com.nicholasrutherford.chal.screens.home.HomeFragment
import com.nicholasrutherford.chal.settings.more.MoreFragment

// regular fragments
val challengesFragment = ChallengesFragment()
val challengePostFragment = ChallengePostFragment()
fun challengeDetailsFragment(mainActivity: MainActivity, appContext: Context) = ChallengeDetailsFragment(mainActivity, appContext)
fun challengesRedesignFragment(mainActivity: MainActivity, appContext: Context) =
    ChallengesRedesignFragment(
        mainActivity,
        appContext
    )
fun createAccountFragment(activity: CreateAccountActivity, context: Context) = CreateAccountFragment(activity, context)
fun editMyProfileFragment(activity: MainActivity, context: Context) = EditMyProfileFragment(activity, context)
val editProfileFragment = EditProfileFragment()
val homeFragment = HomeFragment()
fun loginFragment(activity: LoginActivity, appContext: Context) = LoginFragment(activity,appContext)
val myFriendsFragment = MyFriendsFragment()
val myProfileFragment = MyProfileFragment()
fun newsFeedRedesignFragment(mainActivity: MainActivity, appContext: Context) = NewsFeedRedesignFragment(mainActivity, appContext)
fun moreFragment(mainActivity: MainActivity, context: Context) = MoreFragment(mainActivity, context)
fun profileFragment(mainActivity: MainActivity, context: Context) =
    com.nicholasrutherford.chal.profile.profiles.ProfileFragment(
        mainActivity,
        context
    )
val otherUserProfileFragment = OtherUserProfileFragment()
val searchPeopleFragment = SearchPeopleFragment()
fun signUpFragment(activity: SignUpActivity, appContext: Context) = SignUpFragment(activity, appContext)
val singleChallengeFragment = SingleChallengeFragment()

// alerts fragments
val errorCreateAccountDialog = ErrorCreateAccountDialogFragment()
val loadingDialog = LoadingDialogFragment()