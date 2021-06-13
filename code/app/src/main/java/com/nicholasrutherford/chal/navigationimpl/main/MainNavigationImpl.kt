package com.nicholasrutherford.chal.navigationimpl.main

import android.app.Application
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.main.MainNavigation
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : MainNavigation {

}