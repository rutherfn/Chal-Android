package com.nicholasrutherford.chal

import android.content.Context
import com.nicholasrutherford.chal.account.createaccount.CreateAccountFragment
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordActivity
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordFragment
import com.nicholasrutherford.chal.account.signup.SignUpFragment
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.login.LoginFragment
import com.nicholasrutherford.chal.account.signup.SignUpActivity
import com.nicholasrutherford.chal.challengesredesign.challengedetails.ChallengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedRedesignFragment
import com.nicholasrutherford.chal.profile.editprofile.EditMyProfileFragment
import com.nicholasrutherford.chal.profile.profiles.ProfileFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadFragment
import com.nicholasrutherford.chal.settings.more.MoreFragment

fun challengeDetailsFragment(mainActivity: MainActivity, appContext: Context) = ChallengeDetailsFragment(mainActivity, appContext)
fun challengesRedesignFragment(mainActivity: MainActivity, appContext: Context) = ChallengesRedesignFragment(mainActivity, appContext)
fun createAccountFragment(activity: CreateAccountActivity, context: Context) = CreateAccountFragment(activity, context)
fun editMyProfileFragment(activity: MainActivity, context: Context) = EditMyProfileFragment(activity, context)
fun forgotPasswordFragment(activity: ForgotPasswordActivity, context: Context) = ForgotPasswordFragment(activity, context)
fun loginFragment(activity: LoginActivity, appContext: Context) = LoginFragment(activity,appContext)
fun newsFeedRedesignFragment(mainActivity: MainActivity, appContext: Context) = NewsFeedRedesignFragment(mainActivity, appContext)
fun moreFragment(mainActivity: MainActivity, context: Context) = MoreFragment(mainActivity, context)
fun profileFragment(mainActivity: MainActivity, context: Context) = ProfileFragment(mainActivity, context)
fun progressUploadFragment(mainActivity: MainActivity, context: Context) = ProgressUploadFragment(mainActivity, context)
fun signUpFragment(activity: SignUpActivity, appContext: Context) = SignUpFragment(activity, appContext)