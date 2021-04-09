package com.nicholasrutherford.chal

import android.content.Context
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.account.createaccount.CreateAccountFragment
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordActivity
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordFragment
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.login.LoginFragment
import com.nicholasrutherford.chal.account.signup.SignUpActivity
import com.nicholasrutherford.chal.account.signup.SignUpFragment
import com.nicholasrutherford.chal.bugreport.BugReportFragment
import com.nicholasrutherford.chal.challengesredesign.challengedetails.ChallengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignFragment
import com.nicholasrutherford.chal.data.realdata.Challenges
import com.nicholasrutherford.chal.more.MoreFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import com.nicholasrutherford.chal.profile.editprofile.EditMyProfileFragment
import com.nicholasrutherford.chal.profile.profiles.MyProfileFragment

fun bugReportFragment(mainActivity: MainActivity, appContext: Context) = BugReportFragment(
    appContext
)
fun challengeDetailsFragment(appContext: Context, challenge: Challenges) = ChallengeDetailsFragment(appContext, challenge)
fun challengesRedesignFragment(appContext: Context) = ChallengesRedesignFragment(appContext)
fun createAccountFragment(activity: CreateAccountActivity, context: Context) = CreateAccountFragment(activity, context)
fun editMyProfileFragment(activity: MainActivity, context: Context) = EditMyProfileFragment(activity, context)
fun forgotPasswordFragment(activity: ForgotPasswordActivity, context: Context) = ForgotPasswordFragment(activity, context)
fun loginFragment(activity: LoginActivity, appContext: Context) = LoginFragment(activity, appContext)
fun newsFeedFragment(mainActivity: MainActivity, appContext: Context) = NewsFeedFragment(mainActivity, appContext)
fun moreFragment(mainActivity: MainActivity, context: Context) = MoreFragment(mainActivity, context)
fun profileFragment(mainActivity: MainActivity, context: Context) = MyProfileFragment(mainActivity, context)
fun signUpFragment(activity: SignUpActivity, appContext: Context) = SignUpFragment(activity, appContext)
