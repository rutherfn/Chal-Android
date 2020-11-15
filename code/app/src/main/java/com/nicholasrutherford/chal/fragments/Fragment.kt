package com.nicholasrutherford.chal.fragments

import android.content.Context
import com.nicholasrutherford.chal.account.createaccount.CreateAccountFragment
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.account.signup.SignUpFragment
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.login.LoginFragment
import com.nicholasrutherford.chal.account.signup.SignUpActivity
import com.nicholasrutherford.chal.dialogfragments.*
import com.nicholasrutherford.chal.screens.challenges.ChallengesFragment
import com.nicholasrutherford.chal.fragments.debug.changecfontscolors.ChangeFontsAndColorsFragment
import com.nicholasrutherford.chal.fragments.debug.debugmenu.DebugFragment
import com.nicholasrutherford.chal.fragments.myfriends.MyFriendsFragment
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileFragment
import com.nicholasrutherford.chal.fragments.postprogress.ChallengePostFragment
import com.nicholasrutherford.chal.main.SettingsFragment
import com.nicholasrutherford.chal.screens.home.HomeFragment

// regular fragments
val changeFontsAndColorsFragment = ChangeFontsAndColorsFragment()
val challengesFragment = ChallengesFragment()
val challengePostFragment = ChallengePostFragment()
fun createAccountFragment(activity: CreateAccountActivity, context: Context) = CreateAccountFragment(activity, context)
val debugFragment = DebugFragment()
val editProfileFragment = EditProfileFragment()
val homeFragment = HomeFragment()
fun loginFragment(activity: LoginActivity, appContext: Context) = LoginFragment(activity,appContext)
val myFriendsFragment = MyFriendsFragment()
val myProfileFragment = MyProfileFragment()
val otherUserProfileFragment = OtherUserProfileFragment()
val searchPeopleFragment = SearchPeopleFragment()
fun signUpFragment(activity: SignUpActivity, appContext: Context) = SignUpFragment(activity, appContext)
val singleChallengeFragment = SingleChallengeFragment()
val settingFragment = SettingsFragment()

// alerts fragments
val debugPasswordDialog = DebugPasswordDialogFragment()
val errorCreateAccountDialog = ErrorCreateAccountDialogFragment()
val errorLoginAccountDialog = ErrorLoginToAccountDialogFragment()
val loadingAccountDialog = SuccessCreateAccountDialogFragment()
val loadingDialog = LoadingDialogFragment()