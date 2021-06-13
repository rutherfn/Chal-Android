package com.nicholasrutherford.chal.progressupload

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.data.responses.ActiveChallengeAndCategoryResponse
import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding
import com.nicholasrutherford.chal.ext.activitys.ProgressUploadExt
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.main.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProgressUploadFragment @Inject constructor(private val application: Application, private val progressUploadParams: ProgressUploadParams) :
    DaggerFragment(),
    ProgressUploadExt {

        @Inject
        lateinit var viewModelFactory: ViewModelProvider.Factory

        private val viewModel by lazy {
            ViewModelProvider(this, viewModelFactory)
                .get(ProgressUploadViewModel::class.java)
        }

    private val typeface = Typeface()

    private val listOfChallenges = arrayListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity: MainActivity = (activity as MainActivity)
        mainActivity.viewModel.updateCurrentScreen(Screens.UPLOAD_PROGRESS)

        val bind = FragmentProgressUploadBinding.inflate(layoutInflater)

        updateTypefaces(bind)
        collectChallengesAndCategoryResult(bind)
        updateView(bind)
        clickListeners(bind)

        return bind.root
    }

    override fun updateTypefaces(bind: FragmentProgressUploadBinding) {
        typeface.setTypefaceForHeaderBold(bind.clPostProgress.tvPostProgress, application.applicationContext)
        typeface.setTypefaceForBodyRegular(bind.clPostProgress.tvPostProgress, application.applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectChallenge, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectCategory, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvAddCaption, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvUploadImage, application.applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnPostProgressToMyFeed, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnCancelAndDiscardPost, application.applicationContext)
    }

    private fun collectChallengesAndCategoryResult(bind: FragmentProgressUploadBinding) {
        lifecycleScope.launch {
            viewModel.activeChallengeAndCategoryResponse.collect { activeChallengeAndCategoryList ->
                updateSpinners(bind, activeChallengeAndCategoryList)
            }
        }
    }

    override fun updateSpinners(bind: FragmentProgressUploadBinding, challengeAndCategoryList: List<ActiveChallengeAndCategoryResponse>) {
        val listOfCategories = arrayListOf<String>()

        challengeAndCategoryList.forEach { challengeAndCategory ->
            listOfCategories.add(challengeAndCategory.category)
            listOfChallenges.add(challengeAndCategory.challenge)
        }

        val challengesAdapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_spinner_item, listOfChallenges)
        val categoryAdapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_spinner_item, listOfCategories.distinct())

        challengesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        bind.clPostProgress.spSelectChallenge.adapter = challengesAdapter
        bind.clPostProgress.spSelectCategory.adapter = categoryAdapter
    }

    override fun updateView(bind: FragmentProgressUploadBinding) {
        bind.tbProgressUpload.tvTitle.text = viewModel.viewState.toolbarTitle
    }

    override fun clickListeners(bind: FragmentProgressUploadBinding) {
        bind.clPostProgress.btnCancelAndDiscardPost.setOnClickListener {
            viewModel.onDiscardPostClicked()
        }
        bind.clPostProgress.btnCancelAndDiscardPost.setOnClickListener {
            viewModel.onDiscardPostClicked()
        }
        bind.clPostProgress.ivUploadImage.setOnClickListener {
            viewModel.onPhotoClicked()
        }
        bind.tbProgressUpload.ibMoreBack.setOnClickListener {
            viewModel.onBackClicked()
        }
        bind.clPostProgress.btnPostProgressToMyFeed.setOnClickListener {
            val title = bind.clPostProgress.spSelectChallenge.selectedItem.toString()
            val category = bind.clPostProgress.spSelectCategory.selectedItem.toString()
            val caption = bind.clPostProgress.etAddCaption.text.toString()

            viewModel.onPostProgressClicked(title, caption, category, listOfChallenges)
        }
    }

    }