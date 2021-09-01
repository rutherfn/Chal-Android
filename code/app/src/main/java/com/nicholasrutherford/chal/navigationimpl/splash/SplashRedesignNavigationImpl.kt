package com.nicholasrutherford.chal.navigationimpl.splash

import android.app.Application
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.account.redesignlogin.RedesignLoginFragment
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.splashredesign.SplashRedesignNavigation
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import javax.inject.Inject

class SplashRedesignNavigationImpl @Inject constructor(
    private val application: Application,
    private val main: MainActivity,
    private val typeface: TypefacesImpl,
    private val keyboard: KeyboardImpl
    ): SplashRedesignNavigation {

    override fun showHome() {
        // show home
    }

    override fun showlogin() {
        main.supportFragmentManager.beginTransaction()
            .replace(
                container,
                RedesignLoginFragment(
                    application = application,
                    typeface = typeface,
                    keyboard = keyboard
                ),
                RedesignLoginFragment(
                    application = application,
                    typeface = typeface,
                    keyboard = keyboard
                )::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }
}