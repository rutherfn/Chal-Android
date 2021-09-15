package com.nicholasrutherford.chal.main.activity

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.view.MenuItem
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference

class MainViewModel @ViewModelInject constructor(
    private val application: Application,
    private val createSharedPreference: CreateSharedPreference
) : ViewModel() {

    val viewState = MainViewStateImpl()

   // val testFairy = ChalTestFairyImpl(application)

    var selectedPhotoUri: Uri? = null

    // private val clearSharedPreference = ClearSharedPreferenceImpl(application)
    // private val readSharedPreference = ReadSharedPreferenceImpl(application)

    fun navigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_my_feed -> {
                //   launchNewsFeed()
                return true
            }
            R.id.navigation_challenges -> {
                //   launchChallenges()
                return true
            }
            R.id.navigation_more -> {
                //  launchMore()
                return true
            }
        }
        return false
    }

    fun onCameraResult(resultCode: Int, requestCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            data.data?.let { uriData ->
                createSharedPreference.createProfilePictureDirectorySharedPreference("profile-picture", uriData.toString())
            }
            // }
        } else if (resultCode == Activity.RESULT_OK && data != null && requestCode == 1001) {
            data.data?.let { uriData ->
                createSharedPreference.createProfilePictureDirectorySharedPreference("profile-picture", uriData.toString())
            }
        }
    }

    private fun navigateToProgressUpload(bitmapDrawable: BitmapDrawable) {
       // val title = readSharedPreference.readProgressTitle()
       // val caption = readSharedPreference.readProgressCaption()

        //   navigation.pop()

        // navigation.showProgressUpload(
        //     isUpdate = true,
        //     title = title,
        //     caption = caption,
        //     photoUri = selectedPhotoUri,
        //     bitmapDrawable = bitmapDrawable
        // )

      //  clearSharedPreference.clearProgressTitle()
       // clearSharedPreference.clearProgressCaption()
    }

   // private fun captureTestFairyScreenshot() = testFairy.takeScreenshot()

    fun onBackPressed(entryCount: Int) {
        if (entryCount == 0) {
            // navigation.finish()
        }
    }

    // fun updateCurrentScreen(newScreen: Screens) {
    //     viewState.currentScreen = newScreen
    // }

    inner class MainViewStateImpl : MainViewState {
     //   override var currentScreen: Screens? = null
    }
}
