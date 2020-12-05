package com.nicholasrutherford.chal.profile.editprofile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentEditProfileBinding
import com.nicholasrutherford.chal.ext.editprofile.EditProfileExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso

class EditMyProfileFragment(private val mainActivity: MainActivity, private val appContext: Context): Fragment(), EditProfileExtension {

    private var btNavigation: BottomNavigationView? = null
    private val typeface = Typeface()
    private var viewModel: EditMyProfileViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentEditProfileBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        btNavigation?.let { bottomNavigationView ->
            viewModel = fragmentManager?.let { fragmentManager ->
                EditMyProfileViewModel(mainActivity, appContext, fragmentManager, containerId(), bottomNavigationView)
            }
        }
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }
    override fun updateTypefaces(bind: FragmentEditProfileBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbEditMyProfile.tvTitle, appContext)
        typeface.setTypefaceForHeaderBold(bind.tbEditMyProfile.tvClose, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.clEditProfile.tvEditMyProfile, appContext)
        typeface.setTypefaceForBodyItalic(bind.clEditProfile.tvEditMyProfileDescription, appContext)

        typeface.setTypefaceForBodyBold(bind.clEditProfile.tvEditProfileUsername, appContext)
        typeface.setTypefaceForBodyItalic(bind.clEditProfile.etEditProfileUsername, appContext)

        typeface.setTypefaceForBodyBold(bind.clEditProfile.tvEditFirstName, appContext)
        typeface.setTypefaceForBodyItalic(bind.clEditProfile.etFirstName, appContext)

        typeface.setTypefaceForBodyBold(bind.clEditProfile.tvLastName, appContext)
        typeface.setTypefaceForBodyItalic(bind.clEditProfile.etLastName, appContext)

        typeface.setTypefaceForBodyBold(bind.clEditProfile.tvEditBio, appContext)
        typeface.setTypefaceForBodyItalic(bind.clEditProfile.etBio, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.clEditProfile.btnEditProfile, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clEditProfile.tvCancelEditProfile, appContext)
    }

    override fun updateView(bind: FragmentEditProfileBinding) {
        // placeholder
        Glide.with(this).load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
            .into(bind.clEditProfile.ivEditMyProfile)

        bind.clEditProfile.etEditProfileUsername.setText(viewModel?.viewState?.editUsername)
        bind.clEditProfile.etFirstName.setText(viewModel?.viewState?.editFirstName)
        bind.clEditProfile.etLastName.setText(viewModel?.viewState?.editLastName)
        bind.clEditProfile.etBio.setText(viewModel?.viewState?.editBio)
    }

    override fun clickListeners(bind: FragmentEditProfileBinding) {
        bind.clEditProfile.btnEditProfile.setOnClickListener {
            val username = bind.clEditProfile.etEditProfileUsername.text.toString()
            val firstName = bind.clEditProfile.etFirstName.text.toString()
            val lastName = bind.clEditProfile.etLastName.text.toString()
            val bio = bind.clEditProfile.etBio.text.toString()

            viewModel?.onEditProfileClicked(username, firstName, lastName, bio)
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}