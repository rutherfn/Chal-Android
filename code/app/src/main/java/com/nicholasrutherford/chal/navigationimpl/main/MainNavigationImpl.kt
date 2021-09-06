package com.nicholasrutherford.chal.navigationimpl.main

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import com.nicholasrutherford.chal.main.MainNavigation
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(
    ) : MainNavigation {

    override fun finish() {

    }

    override fun pop() {

    }

    override fun showLogin() {

    }

    override fun showChallenges() {
        // mainActivity.supportFragmentManager.beginTransaction()
        //     .replace(
        //         container,
        //         ChallengesRedesignFragment(application),
        //         ChallengesRedesignFragment(application)::javaClass.name
        //     )
        //     .addToBackStack(null)
        //     .commit()
    }

    override fun showMewsFeed() {
        // mainActivity.supportFragmentManager.beginTransaction()
        //     .replace(
        //         container,
        //         NewsFeedFragment(application),
        //         NewsFeedFragment(application)::javaClass.name
        //     )
        //     .addToBackStack(null)
        //     .commit()
    }

    override fun showMore() {
        // mainActivity.supportFragmentManager.beginTransaction()
        //     .replace(
        //         container,
        //         MoreFragment(application),
        //         MoreFragment(application)::javaClass.name
        //     )
        //     .addToBackStack(null)
        //     .commit()
    }

    override fun showProgressUpload(
        isUpdate: Boolean,
        title: String?,
        caption: String?,
        photoUri: Uri?,
        bitmapDrawable: BitmapDrawable?) {

        // val params = ProgressUploadParams(
        //     isUpdate = isUpdate,
        //     title = title,
        //     caption = caption,
        //     photoUri = photoUri,
        //     bitmapDrawable = bitmapDrawable
        // )
        // mainActivity.supportFragmentManager.beginTransaction()
        //     .replace(
        //         container,
        //         ProgressUploadFragment(application, mainActivityHelper, params),
        //         ProgressUploadFragment(application, mainActivityHelper, params)::javaClass.name
        //     )
        //     .addToBackStack(null)
        //     .commit()
    }
}