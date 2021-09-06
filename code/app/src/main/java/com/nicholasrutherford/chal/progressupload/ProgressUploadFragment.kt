package com.nicholasrutherford.chal.progressupload

import android.app.Application
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.data.responses.ActiveChallengeResponse
import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding
import com.nicholasrutherford.chal.ext.activitys.ProgressUploadExt
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.main.helper.MainActivityHelperImpl
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProgressUploadFragment @Inject constructor(
    private val application: Application,
    private val mainActivityHelper: MainActivityHelperImpl,
    private val params: ProgressUploadParams
    ) : DaggerFragment(), ProgressUploadExt {

        @Inject
        lateinit var viewModelFactory: ViewModelProvider.Factory

        private val viewModel by lazy {
            ViewModelProvider(this, viewModelFactory)
                .get(ProgressUploadViewModel::class.java)
        }

    private val typeface = Typeface()

    private val listOfChallenges = arrayListOf<String>()

    private var selectedPhotoUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainActivityHelper.updateCurrentScreen(currentScreen = Screens.UPLOAD_PROGRESS)
        mainActivityHelper.updateBottomNavigationVisibility(isVisible = false)

        val bind = FragmentProgressUploadBinding.inflate(layoutInflater)

        updateSelectedPhotoUri()
        updateTypefaces(bind)
        collectChallengesListResult(bind)
        updateView(bind)
        clickListeners(bind)

        return bind.root
    }

    override fun updateSelectedPhotoUri() {
        params.photoUri?.let { uri ->
            selectedPhotoUri = uri
        }
    }

    override fun updateTypefaces(bind: FragmentProgressUploadBinding) {
        typeface.setTypefaceForHeaderBold(bind.clPostProgress.tvPostProgress, application.applicationContext)
        typeface.setTypefaceForBodyRegular(bind.clPostProgress.tvPostProgress, application.applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectChallenge, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvAddCaption, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvUploadImage, application.applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnPostProgressToMyFeed, application.applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnCancelAndDiscardPost, application.applicationContext)
    }

    private fun collectChallengesListResult(bind: FragmentProgressUploadBinding) {
        lifecycleScope.launch {
            viewModel.activeChallengeList.collect { activeChallengeList ->
                updateSpinners(bind, activeChallengeList)
            }
        }
    }

    override fun updateSpinners(bind: FragmentProgressUploadBinding, challengeList: List<ActiveChallengeResponse>) {
        challengeList.forEach { data ->
            listOfChallenges.add(data.challenge)
        }

        val challengesAdapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_spinner_item, listOfChallenges)
        challengesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        bind.clPostProgress.spSelectChallenge.adapter = challengesAdapter

        if (params.isUpdate) {
            listOfChallenges.forEachIndexed { index, challenges ->
                if (params.title == challenges) {
                    bind.clPostProgress.spSelectChallenge.setSelection(index)
                }
            }
        }
    }

    override fun updateView(bind: FragmentProgressUploadBinding) {
        bind.tbProgressUpload.tvTitle.text = viewModel.viewState.toolbarTitle

        params.caption?.let {
            bind.clPostProgress.etAddCaption.setText(it)
        }

        params.bitmapDrawable?.let { bitmapDrawable ->
              bind.clPostProgress.ivUploadImage.setBackgroundDrawable(bitmapDrawable)
        }
    }

    override fun clickListeners(bind: FragmentProgressUploadBinding) {
        bind.clPostProgress.btnCancelAndDiscardPost.setOnClickListener {
            viewModel.onDiscardPostClicked()
        }
        bind.clPostProgress.ivUploadImage.setOnClickListener {
            val title = bind.clPostProgress.spSelectChallenge.selectedItem.toString()
            val caption = bind.clPostProgress.etAddCaption.text.toString()
            viewModel.onPhotoClicked(title, caption)
        }
        bind.tbProgressUpload.ibMoreBack.setOnClickListener {
            viewModel.onBackClicked()
            val mainActivity: MainActivity = (activity as MainActivity)
            mainActivity.bvNavigation.visibleOrGone = true
        }
        bind.clPostProgress.btnPostProgressToMyFeed.setOnClickListener {
            val title = bind.clPostProgress.spSelectChallenge.selectedItem.toString()
            val caption = bind.clPostProgress.etAddCaption.text.toString()

            viewModel.onPostProgressClicked(title, caption, listOfChallenges, selectedPhotoUri)
        }
    }

    }