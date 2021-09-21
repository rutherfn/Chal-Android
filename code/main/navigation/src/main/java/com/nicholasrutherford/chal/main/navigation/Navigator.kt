package com.nicholasrutherford.chal.main.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    lateinit var navController: NavController

    fun navigate(@IdRes navigationId: Int) {
        navController.navigate(navigationId)
    }

    fun navigateWithBundle(bundle: Bundle, @IdRes navigationId: Int) {
        navController.navigate(navigationId, bundle)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}