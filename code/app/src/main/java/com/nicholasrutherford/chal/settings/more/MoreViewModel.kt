package com.nicholasrutherford.chal.settings.more

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.firebase.read.ReadProfileDetailsFirebase
import com.nicholasrutherford.chal.firebase.sharedpref.WriteFirebaseSharedPref

class MoreViewModel(private val appContext: Context) : ViewModel() {

    private val readProfileDetailsFirebase = ReadProfileDetailsFirebase(appContext)
    private val writeProfileDetailsFirebase = WriteFirebaseSharedPref(appContext)

    fun testData() {
        println(readProfileDetailsFirebase.getAge())
        println(readProfileDetailsFirebase.getBio())

        writeProfileDetailsFirebase.clearAllSharePrefs()

    }

    inner class MoreViewStateImpl: MoreViewState{
        override var profilePictureDirectory = ""
    }

}