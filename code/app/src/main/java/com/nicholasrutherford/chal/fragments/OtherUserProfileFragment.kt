package com.nicholasrutherford.chal.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentOtherUserProfileBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.recycler.adapters.MyProfileAdapter
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileViewModel
import com.nicholasrutherford.chal.viewmodels.OtherUserProfileViewModel
import com.squareup.picasso.Picasso

class OtherUserProfileFragment : Fragment(), FragmentExt {

    private var challengesAdapter: MyProfileAdapter? = null
    private var binding: FragmentOtherUserProfileBinding? = null
    private var viewModel: OtherUserProfileViewModel? = null
    private var btNavigation: BottomNavigationView? = null
    private var typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOtherUserProfileBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation!!
        viewModel = fragmentManager?.let { OtherUserProfileViewModel(it, containerId(), btNavigation!!) }
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun bind() {
        binding?.layoutProfile?.tvBodyMessage?.text = "Here to have fun on the app by accomplishing fitness challenges, add me to learn more! "

        Picasso.get().load(R.drawable.willplaceholder).into(binding?.layoutProfile?.cvProfilePicture)

        binding?.rvProfile?.isNestedScrollingEnabled = false

        binding?.rvProfile?.layoutManager = GridLayoutManager(context,3)

        val viewModel =
            MyProfileViewModel()
        challengesAdapter = context?.let { MyProfileAdapter(viewModel, it, myProfileFragment) }
        binding?.rvProfile?.adapter = challengesAdapter
    }

    fun setupTypeface() {
        binding?.tbOtherUserProfile?.tvTbProfileUsername?.let { typeface.setTypefaceForHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvProfileFullName?.let { typeface.setTypefaceForHeaderBold(it,it.context) }
        binding?.layoutProfile?.tvProfileSubInfo?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvBodyMessage?.let { typeface.setTypefaceForBodyRegular(it, it.context) }
        binding?.layoutProfile?.btnSendMessage?.let { typeface.setTypefaceForHeaderRegular(it, it.context) }
        binding?.layoutProfile?.btnAddFriend?.let { typeface.setTypefaceForHeaderRegular(it, it.context) }
    }

    override fun updateFragment() {
        setupTypeface()

        Picasso.get().load(viewModel?.viewState?.userProfilePreview?.get(0)?.url).into(binding?.layoutProfile?.cvProfilePicture)

        binding?.layoutProfile?.tvProfileFullName?.text = viewModel?.viewState?.userProfilePreview?.get(0)?.firstName.plus(" ").plus(
            viewModel?.viewState?.userProfilePreview?.get(0)?.lastName
        )

        binding?.layoutProfile?.tvProfileSubInfo?.text = viewModel?.viewState?.userProfilePreview?.get(0)?.age.plus(" ")
            .plus(viewModel?.viewState?.userProfilePreview?.get(0)?.city).plus(" ")
            .plus(viewModel?.viewState?.userProfilePreview?.get(0)?.stateAbv)

        binding?.layoutProfile?.tvBodyMessage?.text = viewModel?.viewState?.userProfilePreview?.get(0)?.sloganMessage
    }

    private fun showAlertDialog(title: String, body: String) {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle("Add as friend")

        builder.setMessage("Are you sure you want to send a request to add Nick as a friend?")

        builder.setPositiveButton("YES"){dialog, which ->
            dialog.dismiss()
        }

        builder.setNegativeButton("No"){dialog,which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }

    override fun clickListeners() {
        binding?.tbOtherUserProfile?.ibBack?.setOnClickListener {
            viewModel?.onBackClicked()
        }


        binding?.layoutProfile?.btnAddFriend?.setOnClickListener {
            showAlertDialog("Add Nick as a friend", "Are you sure you want to send a request to add Nick as a friend?")

        }
        binding?.layoutProfile?.btnSendMessage?.setOnClickListener {
            showAlertDialog("Send Nick a message", "Are you sure you want to send a message to Nick?")
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}