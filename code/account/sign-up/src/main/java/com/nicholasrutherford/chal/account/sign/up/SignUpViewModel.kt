package com.nicholasrutherford.chal.account.sign.up

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class SignUpViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigation: SignUpNavigation
) : BaseViewModel() {

    val viewState = SignUpViewStateImpl()

    private fun updateSocialMediaUrl(type: SocialMediaTypes) {
        when (type) {
            SocialMediaTypes.IS_FACEBOOK -> {
                viewState.socialMediaUrl = SocialMediaTypes.IS_FACEBOOK.url
            }
            SocialMediaTypes.IS_INSTAGRAM -> {
                viewState.socialMediaUrl = SocialMediaTypes.IS_INSTAGRAM.url
            }
            else -> {
                viewState.socialMediaUrl = SocialMediaTypes.IS_LINKEDIN.url
            }
        }
    }

    fun onFacebookClicked() {
        updateSocialMediaUrl(SocialMediaTypes.IS_FACEBOOK)
    }

    fun onInstagramClicked() {
        updateSocialMediaUrl(SocialMediaTypes.IS_INSTAGRAM)
    }

    fun onLinkedinClicked() {
        updateSocialMediaUrl(SocialMediaTypes.IS_LINKEDIN)
    }

    fun onLoginClicked() = navigation.pop()

    fun onBackClicked() = navigation.pop()

    fun onContinueWithEmailClicked() = navigation.showCreateAccount()

    inner class SignUpViewStateImpl: SignUpViewState {
        override var socialMediaUrl: String = application.getString(R.string.empty_string)
    }
}