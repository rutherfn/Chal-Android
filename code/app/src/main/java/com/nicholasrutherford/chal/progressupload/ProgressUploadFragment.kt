package com.nicholasrutherford.chal.progressupload

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding
import com.nicholasrutherford.chal.ext.activitys.ProgressUploadExt
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.main.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProgressUploadFragment @Inject constructor(private val application: Application) :
    DaggerFragment(),
    ProgressUploadExt {

        @Inject
        lateinit var viewModelFactory: ViewModelProvider.Factory

        private val viewModel by lazy {
            ViewModelProvider(this, viewModelFactory)
                .get(ProgressUploadViewModel::class.java)
        }

    private val typeface = Typeface()
    var selectedPhotoUri: Uri? = null

    private val listOfChallenges = arrayListOf<String>()

    private var bind: FragmentProgressUploadBinding? = null

    val _test = MutableStateFlow("")
    val test: StateFlow<String> = _test

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity: MainActivity = (activity as MainActivity)
        mainActivity.viewModel.updateCurrentScreen(Screens.UPLOAD_PROGRESS)

        println(mainActivity.viewModel.viewState.currentScreen)

        bind = FragmentProgressUploadBinding.inflate(layoutInflater)

        bind?.let {
            main(it, viewModel)
        }

        lifecycleScope.launch {
            test.collect {
                bind?.let {
                    println("get here if bind is not null")
                }
                println(it)
            }
        }


        return bind?.root
    }

    override fun main(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        updateTypefaces(bind, viewModel)
        collectChallengesAndCategoryResult(bind, viewModel)
        clickListeners(bind, viewModel)

        bind.tbProgressUpload.tvTitle.text = getString(R.string.post_your_progress)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
        //     selectedPhotoUri = data.data
        //
        //     val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
        //
        //     val bitmapDrawable = BitmapDrawable(bitmap)
        //     bind?.clPostProgress?.ivUploadImage?.setBackgroundDrawable(bitmapDrawable)
        // } else if (resultCode == Activity.RESULT_OK) {
        //     val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
        //
        //     val bitmapDrawable = BitmapDrawable(bitmap)
        //     binding?.clPostProgress?.ivUploadImage?.setBackgroundDrawable(bitmapDrawable)
        // }
    }

    private fun collectChallengesAndCategoryResult(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        lifecycleScope.launch {
            viewModel.activeChallengeAndCategoryResponse.collect { activeChallengeAndCategoryList ->
                updateSpinners(bind, viewModel, activeChallengeAndCategoryList)
            }
        }
    }

    override fun updateTypefaces(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        typeface.setTypefaceForHeaderBold(bind.clPostProgress.tvPostProgress, application.applicationContext)
        typeface.setTypefaceForBodyRegular(bind.clPostProgress.tvPostProgress, application.applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectChallenge, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectCategory, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvAddCaption, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvUploadImage, application.applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnPostProgressToMyFeed, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnCancelAndDiscardPost, application.applicationContext)
    }

    override fun updateSpinners(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel, challengeAndCategoryList: List<ProgressUploadViewModel.ActiveChallengeAndCategoryResponse>) {
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

    override fun clickListeners(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel) {
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
            var selectedIndex = 0

            listOfChallenges.forEachIndexed { index, challenges ->
                if (title == challenges) {
                    selectedIndex = index
                }
            }

            viewModel.onPostProgressClicked(title, caption, category, selectedIndex, selectedPhotoUri)
        }
    }
    }