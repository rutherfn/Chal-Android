package com.nicholasrutherford.chal.create.account.createaccount

import androidx.core.os.bundleOf
import com.nicholasrutherford.chal.create.account.EMAIL
import com.nicholasrutherford.chal.create.account.PASSWORD
import com.nicholasrutherford.chal.create.account.R
import com.nicholasrutherford.chal.create.account.USERNAME
import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class CreateAccountNavigationImpl @Inject constructor(): CreateAccountNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun pop() {
        navigator.navController.popBackStack()
    }

    override fun showUploadPhoto(username: String, email: String, password: String) {
        val uploadPhotoBundle = bundleOf(USERNAME to username, EMAIL to email, PASSWORD to password)
        navigator.navigateWithBundle(uploadPhotoBundle, R.id.nav_graph_upload_photo)
    }
}