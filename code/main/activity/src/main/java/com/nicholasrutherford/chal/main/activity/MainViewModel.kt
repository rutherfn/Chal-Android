package com.nicholasrutherford.chal.main.activity

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.view.MenuItem
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import java.io.ByteArrayOutputStream

class MainViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigation: MainNavigation,
    private val createSharedPreference: CreateSharedPreference
) : BaseViewModel() {

    val viewState = MainViewStateImpl()

    fun navigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_my_feed -> {
                navigation.showNewsFeed()
                return true
            }
            R.id.navigation_challenges -> {
                navigation.showChallengesList()
                return true
            }
            R.id.navigation_more -> {
                navigation.showMore()
                return true
            }
        }
        return false
    }

    fun onCameraResult(resultCode: Int, requestCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            data.data?.let { uriData ->
                createSharedPreference.createProfilePictureDirectorySharedPreference( uriData.toString())
            }
            // }
        } else if (requestCode == 1888 && resultCode == Activity.RESULT_OK && data != null) {
            // hack needs to be updated down the road
            data.extras?.get("data").let {
                val uri = getImageUri(application.applicationContext, it as Bitmap)
                uri.let { uriData ->
                    createSharedPreference.createProfilePictureDirectorySharedPreference(uriData.toString())
                }
            }
        }
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.getContentResolver(),
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
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
