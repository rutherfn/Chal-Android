package com.nicholasrutherford.chal.navigationimpl.main

import android.app.Application
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignFragment
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.main.MainNavigation
import com.nicholasrutherford.chal.main.helper.MainActivityHelperImpl
import com.nicholasrutherford.chal.more.MoreFragment
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadParams
import com.nicholasrutherford.chal.splashredesign.SplashRedesignFragment
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(
    private val application: Application,
    private val mainActivityHelper: MainActivityHelperImpl,
    private val mainActivity: MainActivity
    ) : MainNavigation {

    override fun finish() = mainActivity.finish()

    override fun pop()  = mainActivity.supportFragmentManager.popBackStack()

    override fun showChallenges() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                ChallengesRedesignFragment(application),
                ChallengesRedesignFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }

    override fun showMewsFeed() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                NewsFeedFragment(application),
                NewsFeedFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }

    override fun showMore() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                MoreFragment(application),
                MoreFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }

    override fun showProgressUpload(
        isUpdate: Boolean,
        title: String?,
        caption: String?,
        photoUri: Uri?,
        bitmapDrawable: BitmapDrawable?) {

        val params = ProgressUploadParams(
            isUpdate = isUpdate,
            title = title,
            caption = caption,
            photoUri = photoUri,
            bitmapDrawable = bitmapDrawable
        )
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                ProgressUploadFragment(application, mainActivityHelper, params),
                ProgressUploadFragment(application, mainActivityHelper, params)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }
}