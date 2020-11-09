package com.nicholasrutherford.chal.fragments

import android.content.Context
import com.nicholasrutherford.chal.account.LoginFragment
import com.nicholasrutherford.chal.dialogfragments.*
import com.nicholasrutherford.chal.screens.challenges.ChallengesFragment
import com.nicholasrutherford.chal.fragments.debug.changecfontscolors.ChangeFontsAndColorsFragment
import com.nicholasrutherford.chal.fragments.debug.debugmenu.DebugFragment
import com.nicholasrutherford.chal.fragments.myfriends.MyFriendsFragment
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileFragment
import com.nicholasrutherford.chal.fragments.postprogress.ChallengePostFragment
import com.nicholasrutherford.chal.fragments.settings.SettingsFragment
import com.nicholasrutherford.chal.screens.home.HomeFragment

// regular fragments
val changeFontsAndColorsFragment = ChangeFontsAndColorsFragment()
val challengesFragment = ChallengesFragment()
val challengePostFragment = ChallengePostFragment()
val debugFragment = DebugFragment()
val editProfileFragment = EditProfileFragment()
val homeFragment = HomeFragment()
fun loginFragment(appContext: Context) = LoginFragment(appContext)
val myFriendsFragment = MyFriendsFragment()
val myProfileFragment = MyProfileFragment()
val otherUserProfileFragment = OtherUserProfileFragment()
val searchPeopleFragment = SearchPeopleFragment()
val singleChallengeFragment = SingleChallengeFragment()
val settingFragment = SettingsFragment()

// alerts fragments
val debugPasswordDialog = DebugPasswordDialogFragment()
val errorCreateAccountDialog = ErrorCreateAccountDialogFragment()
val errorLoginAccountDialog = ErrorLoginToAccountDialogFragment()
val loadingAccountDialog = SuccessCreateAccountDialogFragment()
val loadingDialog = LoadingDialogFragment()