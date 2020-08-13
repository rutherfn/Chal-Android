package com.nicholasrutherford.chal.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentProfileBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.recycler.adapters.MyProfileAdapter
import com.nicholasrutherford.chal.viewmodels.MyProfileViewModel
import com.squareup.picasso.Picasso

class MyProfileFragment : Fragment() {

    private var myProfileAdapter: MyProfileAdapter? = null
    private val typeface = Typeface()
    private val helper = Helper()
    val singleChallengeFragment = SingleChallengeFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(layoutInflater)
        main(binding)
        return binding.root
    }

    private fun main(binding: FragmentProfileBinding) {

        context?.let {
            setupToolbar(binding, it)


            typeface.setTypefaceForHeaderBold(binding.layoutProfile.tvProfileFullName, it)
            typeface.setTypefaceForSubHeaderBold(binding.layoutProfile.tvProfileSubInfo, it)
            typeface.setTypefaceForBodyBold(binding.layoutProfile.tvBodyMessage, it)
        }

        binding.layoutProfile.tvBodyMessage.text = "Here to have fun on the app by accomplishing fitness challenges, add me to learn more! "

        Picasso.get().load(R.drawable.willplaceholder).into(binding.layoutProfile.cvProfilePicture)

        binding.rvProfile.isNestedScrollingEnabled = false

        binding.rvProfile.layoutManager = GridLayoutManager(context,3)

        val viewModel = MyProfileViewModel()
        myProfileAdapter = context?.let { MyProfileAdapter(viewModel, it, this) }
        binding.rvProfile.adapter = myProfileAdapter
    }

    private fun setupToolbar(binding: FragmentProfileBinding, screenContext: Context) {

    }

}