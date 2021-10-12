package com.nicholarutherford.chal.more.debug

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class DebugViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigator: Navigator,
    private val createSharedPreference: CreateSharedPreference,
    private val fetchSharedPreference: FetchSharedPreference,
    private val removeSharedPreference: RemoveSharedPreference
) : BaseViewModel() {

    val viewState = DebugViewStateImpl()

    init {
        setViewState()
    }

    private fun setViewState() {
        viewState.isEnableChallengeModeChecked =
            fetchSharedPreference.fetchChallengeModeSharedPreference()
        viewState.isTurnOnAllFeaturesChecked =
            fetchSharedPreference.fetchTurnOnAllFeaturesSharedPreference()
        viewState.isShowDeviceNotificationsIsChecked =
            fetchSharedPreference.fetchShowDeviceNotificationPreference()
        viewState.isUnActivatedAccountIsChecked =
            fetchSharedPreference.fetchShowUnActiviatedAccountPreference()
        viewState.isOnBoardingChcked =
            fetchSharedPreference.fetchShowOnBoarding()

        setViewStateAsUpdated()
    }

    fun onCheckedEnableChallengeMode(isChecked: Boolean) {
        createSharedPreference.createEnableChallengeModePreference(isChecked)
    }

    fun onCheckedTurnOnAllFeatures(isChecked: Boolean) {
        createSharedPreference.createTurnOnAllFeaturesPreference(isChecked)
    }

    fun onCheckedShowDeviceNotification(isChecked: Boolean) {
        createSharedPreference.createShowDeviceNotificationsPreference(isChecked)
    }

    fun onCheckedUnActivatedAccount(isChecked: Boolean) {
        createSharedPreference.createUnActivatedAccountPreference(isChecked)
    }

    fun onCheckedOnBoarding(isChecked: Boolean) {
        createSharedPreference.createShowOnboardPreference(isChecked)
    }

    fun onBackClicked() = navigator.navigateBack()


    inner class DebugViewStateImpl : DebugViewState {
        override var isEnableChallengeModeChecked: Boolean = false
        override var isTurnOnAllFeaturesChecked: Boolean = false
        override var isShowDeviceNotificationsIsChecked: Boolean = false
        override var isUnActivatedAccountIsChecked: Boolean = false
        override var isOnBoardingChcked: Boolean = false
    }
}