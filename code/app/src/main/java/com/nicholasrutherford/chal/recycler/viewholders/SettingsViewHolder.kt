package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ProfileChallengesBinding
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileFragment
import com.nicholasrutherford.chal.fragments.singleChallengeFragment
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileViewModel

class SettingsViewHolder(private val binding: ProfileChallengesBinding, private val viewModel: MyProfileViewModel, private val mContext: Context, private val fragment: MyProfileFragment) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pos: Int) {
        clickListeners()
    }

    private fun clickListeners() {
        binding.ivChallenges.setOnClickListener {
            viewModel.onclickChallenges()
            showSingleChallengeFragment()
        }
    }

    private fun showSingleChallengeFragment() {
        fragment.fragmentManager?.beginTransaction()
            ?.replace(R.id.container, singleChallengeFragment, singleChallengeFragment::javaClass.name)
            ?.commit()
    }

}