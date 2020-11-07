package com.nicholasrutherford.chal.fragments

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
var challengesFragment = ChallengesFragment()
var debugFragment = DebugFragment()
var homeFragment = HomeFragment()
var changeFontsAndColorsFragment = ChangeFontsAndColorsFragment()
var otherUserProfileFragment = OtherUserProfileFragment()
var myProfileFragment = MyProfileFragment()
var singleChallengeFragment = SingleChallengeFragment()
var searchPeopleFragment = SearchPeopleFragment()
var editProfileFragment = EditProfileFragment()
var myFriendsFragment = MyFriendsFragment()
var settingFragment = SettingsFragment()
var challengePostFragment = ChallengePostFragment()

// alerts fragments
var debugPasswordDialog = DebugPasswordDialogFragment()
var errorCreateAccountDialog = ErrorCreateAccountDialogFragment()
var errorLoginAccountDialog = ErrorLoginToAccountDialogFragment()
var loadingDialog = LoadingDialogFragment()
var loadingAccountDialog = SuccessCreateAccountDialogFragment()