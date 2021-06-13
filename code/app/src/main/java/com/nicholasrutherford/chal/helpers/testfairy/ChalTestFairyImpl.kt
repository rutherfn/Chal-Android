package com.nicholasrutherford.chal.helpers.testfairy

import android.app.Application
import com.testfairy.TestFairy
import javax.inject.Inject

// put this in the gradle in a future PR??
const val TEST_FAIRY_BUILD_CODE = "SDK-aPjHEtM8"

class ChalTestFairyImpl @Inject constructor(private val application: Application) : ChalTestFairy {

    override fun init() = TestFairy.begin(application.applicationContext, TEST_FAIRY_BUILD_CODE)

    override fun takeScreenshot() = TestFairy.takeScreenshot()
}