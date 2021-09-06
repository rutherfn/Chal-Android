package com.nicholasrutherford.chal.main.activity

import android.graphics.drawable.BitmapDrawable
import android.net.Uri

interface MainNavigation {
    fun finish()
    fun pop()
    fun showLogin()
    fun showChallenges()
    fun showMewsFeed()
    fun showMore()
    fun showProgressUpload(isUpdate: Boolean, title: String?, caption: String?, photoUri: Uri?, bitmapDrawable: BitmapDrawable?)
}