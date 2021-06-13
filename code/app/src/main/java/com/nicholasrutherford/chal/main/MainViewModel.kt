package com.nicholasrutherford.chal.main

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.helpers.testfairy.ChalTestFairyImpl
import com.nicholasrutherford.chal.navigationimpl.main.MainNavigationImpl
import com.testfairy.TestFairy
import javax.inject.Inject

class MainViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    val viewState = MainViewStateImpl()

    private val navigation = MainNavigationImpl(application, mainActivity)
    val testFairy = ChalTestFairyImpl(application)

    var selectedPhotoUri: Uri? = null

    fun onCameraResult(resultCode: Int, requestCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(application.applicationContext.contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)

            if (viewState.currentScreen == Screens.UPLOAD_PROGRESS) {
                // do this functionality for only the upload progress, once you have the bit map drawable
            }

            // test(application)._test.value = bitmapDrawable.toString()

            // test(application).setSomething(bitmapDrawable)
        } else if (resultCode == Activity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(application.applicationContext.contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)

            if (viewState.currentScreen == Screens.UPLOAD_PROGRESS) {
                // do this functionality for only the upload progress, once you have the bit map drawable
            }

            //test(application)._test.value = bitmapDrawable.toString()
        }
    }

    private fun captureTestFairyScreenshot() = testFairy.takeScreenshot()

    fun updateCurrentScreen(newScreen: Screens) {
        viewState.currentScreen = newScreen
    }

    inner class MainViewStateImpl : MainViewState {
        override var currentScreen: Screens? = null
    }
}
