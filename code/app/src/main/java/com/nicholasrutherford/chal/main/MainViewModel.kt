package com.nicholasrutherford.chal.main

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.helpers.sharedpreference.clearsharedpreference.ClearSharedPreferenceImpl
import com.nicholasrutherford.chal.helpers.sharedpreference.readsharedpreference.ReadSharedPreferenceImpl
import com.nicholasrutherford.chal.helpers.testfairy.ChalTestFairyImpl
import com.nicholasrutherford.chal.navigationimpl.main.MainNavigationImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    val viewState = MainViewStateImpl()

    private val navigation = MainNavigationImpl(application, mainActivity)
    val testFairy = ChalTestFairyImpl(application)

    var selectedPhotoUri: Uri? = null

    private val clearSharedPreference = ClearSharedPreferenceImpl(application)
    private val readSharedPreference = ReadSharedPreferenceImpl(application)

    fun launchSplash() = navigation.showSplash()

    fun launchNewsFeed() = navigation.showMewsFeed()

    private fun launchChallenges() = navigation.showChallenges()

    private fun launchMore() = navigation.showMore()

    fun navigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_my_feed -> {
                launchNewsFeed()
                return true
            }
            R.id.navigation_challenges -> {
                launchChallenges()
                return true
            }
            R.id.navigation_more -> {
                launchMore()
                return true
            }
        }
        return false
    }

    fun onCameraResult(resultCode: Int, requestCode: Int, data: Intent?) {
            if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
                selectedPhotoUri = data.data

                val bitmap = MediaStore.Images.Media.getBitmap(application.applicationContext.contentResolver, selectedPhotoUri)

                val bitmapDrawable = BitmapDrawable(bitmap)

                if (viewState.currentScreen == Screens.UPLOAD_PROGRESS) {
                    navigateToProgressUpload(bitmapDrawable)
                }
            } else if (resultCode == Activity.RESULT_OK) {
                val bitmap = MediaStore.Images.Media.getBitmap(application.applicationContext.contentResolver, selectedPhotoUri)

                val bitmapDrawable = BitmapDrawable(bitmap)

                if (viewState.currentScreen == Screens.UPLOAD_PROGRESS) {
                    navigateToProgressUpload(bitmapDrawable)
                  // bind?.clPostProgress?.ivUploadImage?.setBackgroundDrawable(bitmapDrawable)
                }
            }
        }

    private fun navigateToProgressUpload(bitmapDrawable: BitmapDrawable) {
        val title = readSharedPreference.readProgressTitle()
        val caption = readSharedPreference.readProgressCaption()

        navigation.pop()

        navigation.showProgressUpload(
            isUpdate = true,
            title = title,
            caption = caption,
            photoUri = selectedPhotoUri,
            bitmapDrawable = bitmapDrawable
        )

        clearSharedPreference.clearProgressTitle()
        clearSharedPreference.clearProgressCaption()
    }

    private fun captureTestFairyScreenshot() = testFairy.takeScreenshot()

    fun onBackPressed(entryCount: Int) {
        if (entryCount == 0) {
            navigation.finish()
        }
    }

    fun updateCurrentScreen(newScreen: Screens) {
            viewState.currentScreen = newScreen
        }

        inner class MainViewStateImpl : MainViewState {
            override var currentScreen: Screens? = null
        }
    }
