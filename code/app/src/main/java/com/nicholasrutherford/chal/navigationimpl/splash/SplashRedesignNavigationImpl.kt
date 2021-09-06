package com.nicholasrutherford.chal.navigationimpl.splash

import android.app.Application
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigator.Navigator
import com.nicholasrutherford.chal.splashredesign.SplashRedesignNavigation
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import javax.inject.Inject

class SplashRedesignNavigationImpl @Inject constructor(
    private val application: Application,
    private val typeface: TypefacesImpl,
    private val keyboard: KeyboardImpl
    ): SplashRedesignNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showHome() {
        // show home
    }

    override fun showlogin() {
        navigator.navigate(R.id.navLogin)
    }
}