package com.nicholasrutherford.chal.ui.base_vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

open class BaseViewModel : ViewModel() {

    val currentTime = Calendar.getInstance()
    val timeToMatch = Calendar.getInstance()

    val toolbarBackImage = R.drawable.ic_toolbar_arrow
    val toolbarBlackBackImage = R.drawable.ic_toolbar_arrow_black

    val _viewStateUpdated = MutableStateFlow(false)
    val viewStateUpdated: StateFlow<Boolean> = _viewStateUpdated

    val _shouldShowProgress = MutableStateFlow(false)
    val shouldShowProgress: StateFlow<Boolean> = _shouldShowProgress

    val _shouldDismissProgress = MutableStateFlow(false)
    val shouldDismissProgress: StateFlow<Boolean> = _shouldDismissProgress

    val _shouldShowAlert = MutableStateFlow(false)
    val shouldShowAlert: StateFlow<Boolean> = _shouldShowAlert

    init {
        setViewStateAsNotUpdated()
        setShouldShowProgressAsNotUpdated()
        setShouldDismissProgressAsNotUpdated()
        setShouldShowAlertAsNotUpdated()
    }

    fun setViewStateAsNotUpdated() {
        _viewStateUpdated.value = false
    }

    fun setShouldShowProgressAsNotUpdated() {
        _shouldShowProgress.value = false
    }

    fun setShouldDismissProgressAsNotUpdated() {
        _shouldDismissProgress.value = false
    }

    fun setShouldShowAlertAsNotUpdated() {
        _shouldShowAlert.value = false
    }

    fun setViewStateAsUpdated() {
        _viewStateUpdated.value = true
    }

    fun setShouldShowProgressAsUpdated() {
        _shouldShowProgress.value = true
    }

    fun setShouldShowDismissProgressAsUpdated() {
        _shouldDismissProgress.value = true
    }

    fun setShouldShowAlertAsUpdated() {
        _shouldShowAlert.value = true
    }

    fun dayInChallenge(): Int {
        val calendar = Calendar.getInstance()

        return when (calendar[Calendar.DAY_OF_WEEK]) {
            Calendar.MONDAY -> { 1 }
            Calendar.TUESDAY -> { 2 }
            Calendar.WEDNESDAY -> { 3 }
            Calendar.THURSDAY -> { 4 }
            Calendar.FRIDAY -> { 5 }
            Calendar.SATURDAY -> { 6 }
            else -> { 7 }
        }
    }

    fun getRealCurrentDayOnChallenge(currentDay: Int, challengeExpired: Int): Int {
        when (currentDay) {
            1 -> { return 0 }
            2 -> { return 1 }
            3 -> { return 2 }
            4 -> { return 3 }
            5 -> { return 4 }
            6 -> { return 5 }
            else -> { return 7 }
        }
    }
}