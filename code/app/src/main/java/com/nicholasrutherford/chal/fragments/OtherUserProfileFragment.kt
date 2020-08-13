package com.nicholasrutherford.chal.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentOtherUserProfileBinding
import com.nicholasrutherford.chal.recycler.adapters.MyProfileAdapter
import com.nicholasrutherford.chal.viewmodels.MyProfileViewModel
import com.nicholasrutherford.chal.viewmodels.OtherUserProfileViewModel
import com.squareup.picasso.Picasso

class OtherUserProfileFragment : Fragment(), FragmentExt {

    val fragment = MyProfileFragment()

    private var challengesAdapter: MyProfileAdapter? = null
    private var binding: FragmentOtherUserProfileBinding? = null
    private var viewModel: OtherUserProfileViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOtherUserProfileBinding.inflate(layoutInflater)
        viewModel = OtherUserProfileViewModel()
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

        val viewModel = MyProfileViewModel()
        challengesAdapter = context?.let { MyProfileAdapter(viewModel, it, fragment) }
        binding?.rvProfile?.adapter = challengesAdapter
    }

    override fun updateFragment() {
        Picasso.get().load(viewModel?.viewState?.userProfilePreview?.get(0)?.url).into(binding?.layoutProfile?.cvProfilePicture)

        binding?.layoutProfile?.tvProfileFullName?.text = viewModel?.viewState?.userProfilePreview?.get(0)?.firstName.plus(" ").plus(
            viewModel?.viewState?.userProfilePreview?.get(0)?.lastName
        )

        binding?.layoutProfile?.tvBodyMessage?.text = viewModel?.viewState?.userProfilePreview?.get(0)?.age.plus(" ")
            .plus(viewModel?.viewState?.userProfilePreview?.get(0)?.city).plus(" ")
            .plus(viewModel?.viewState?.userProfilePreview?.get(0)?.stateAbv)

        binding?.layoutProfile?.tvBodyMessage?.text = viewModel?.viewState?.userProfilePreview?.get(0)?.sloganMessage
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle("Add as friend")

        builder.setMessage("Are you sure you want to send a request to add Nick to add as a friend?")

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
            showAlertDialog()

        }
        binding?.layoutProfile?.btnSendMessage?.setOnClickListener {

        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}