package com.nicholasrutherford.chal.profile.editprofile

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentEditProfileBinding
import com.nicholasrutherford.chal.ext.fragments.editprofile.EditProfileExtension
import com.nicholasrutherford.chal.helpers.Typeface
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EditProfileFragment(private val application: Application) : DaggerFragment(), EditProfileExtension {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val editProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(EditProfileViewModel::class.java)
    }

    private var btNavigation: BottomNavigationView? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentEditProfileBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation

        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }
    override fun updateTypefaces(bind: FragmentEditProfileBinding) {
        application.applicationContext?.let { appContext ->
            typeface.setTypefaceForHeaderBold(bind.tbEditMyProfile.tvTitle, appContext)
            typeface.setTypefaceForHeaderBold(bind.tbEditMyProfile.tvClose, appContext)

            typeface.setTypefaceForSubHeaderBold(bind.clEditProfile.tvEditMyProfile, appContext)
            typeface.setTypefaceForBodyItalic(
                bind.clEditProfile.tvEditMyProfileDescription,
                appContext
            )

            typeface.setTypefaceForBodyBold(bind.clEditProfile.tvEditProfileUsername, appContext)
            typeface.setTypefaceForBodyItalic(bind.clEditProfile.etEditProfileUsername, appContext)

            typeface.setTypefaceForBodyBold(bind.clEditProfile.tvEditFirstName, appContext)
            typeface.setTypefaceForBodyItalic(bind.clEditProfile.etFirstName, appContext)

            typeface.setTypefaceForBodyBold(bind.clEditProfile.tvLastName, appContext)
            typeface.setTypefaceForBodyItalic(bind.clEditProfile.etLastName, appContext)

            typeface.setTypefaceForBodyBold(bind.clEditProfile.tvEditBio, appContext)
            typeface.setTypefaceForBodyItalic(bind.clEditProfile.etBio, appContext)

            typeface.setTypefaceForSubHeaderBold(bind.clEditProfile.btnEditProfile, appContext)
            typeface.setTypefaceForSubHeaderRegular(
                bind.clEditProfile.tvCancelEditProfile,
                appContext
            )
        }
    }

    override fun updateView(bind: FragmentEditProfileBinding) {
        // placeholder
        Glide.with(this).load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
            .into(bind.clEditProfile.ivEditMyProfile)

        bind.clEditProfile.etEditProfileUsername.setText(editProfileViewModel.viewState.editUsername)
        bind.clEditProfile.etFirstName.setText(editProfileViewModel.viewState.editFirstName)
        bind.clEditProfile.etLastName.setText(editProfileViewModel.viewState.editLastName)
        bind.clEditProfile.etBio.setText(editProfileViewModel.viewState.editBio)
    }

    override fun clickListeners(bind: FragmentEditProfileBinding) {
        bind.clEditProfile.btnEditProfile.setOnClickListener {
            val username = bind.clEditProfile.etEditProfileUsername.text.toString()
            val firstName = bind.clEditProfile.etFirstName.text.toString()
            val lastName = bind.clEditProfile.etLastName.text.toString()
            val bio = bind.clEditProfile.etBio.text.toString()

            editProfileViewModel.onEditProfileClicked(username, firstName, lastName, bio)
        }
        bind.tbEditMyProfile.ibMoreBack.setOnClickListener {
            editProfileViewModel.onBackButtonClicked()
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }
}