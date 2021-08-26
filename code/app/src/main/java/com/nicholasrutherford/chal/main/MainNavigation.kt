package com.nicholasrutherford.chal.main

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import com.nicholasrutherford.chal.Navigation

interface MainNavigation : Navigation {
    fun finish()
    fun pop()
    fun showChallenges()
    fun showMewsFeed()
    fun showMore()
    fun showProgressUpload(isUpdate: Boolean, title: String?, caption: String?, photoUri: Uri?, bitmapDrawable: BitmapDrawable?)
    fun showSplash()
}