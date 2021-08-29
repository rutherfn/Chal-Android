package com.nicholasrutherford.chal.navigationimpl.splash

import android.content.Intent
import com.nicholasrutherford.chal.account.redesignlogin.RedesignLoginFragment
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.splashredesign.SplashRedesignNavigation
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import javax.inject.Inject

class SplashRedesignNavigationImpl @Inject constructor(
    private val main: MainActivity,
    private val typeface: TypefacesImpl
    ): SplashRedesignNavigation {

    override fun showHome() {
        val intent = Intent(main.applicationContext, MainActivity::class.java)

        main.startActivity(intent)
        main.finish()
    }

    override fun showlogin() {
        println("i see fire")
        main.supportFragmentManager.beginTransaction()
            .replace(
                container,
                RedesignLoginFragment(typeface = typeface),
                RedesignLoginFragment(typeface = typeface)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }
}