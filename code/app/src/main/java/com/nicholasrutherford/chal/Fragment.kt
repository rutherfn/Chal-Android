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
import com.nicholasrutherford.chal.challengesredesign.challengedetails.ChallengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignFragment
import com.nicholasrutherford.chal.data.realdata.Challenges

fun challengeDetailsFragment(appContext: Context, challenge: Challenges) = ChallengeDetailsFragment(appContext, challenge)
fun challengesRedesignFragment(appContext: Context) = ChallengesRedesignFragment(appContext)
fun createAccountFragment(activity: CreateAccountActivity, context: Context) = CreateAccountFragment(activity, context)
fun forgotPasswordFragment(activity: ForgotPasswordActivity, context: Context) = ForgotPasswordFragment(activity, context)
fun loginFragment(activity: LoginActivity, appContext: Context) = LoginFragment(activity, appContext)
fun signUpFragment(activity: SignUpActivity, appContext: Context) = SignUpFragment(activity, appContext)
