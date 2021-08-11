package com.nicholasrutherford.chal.addedProgress

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nicholasrutherford.chal.databinding.FragmentAddedProgressBinding
import com.nicholasrutherford.chal.ext.fragments.addedprogress.AddedProgressExt
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.main.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AddedProgressFragment @Inject constructor(private val application: Application) :
DaggerFragment(), AddedProgressExt {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(AddedProgressViewModel::class.java)
    }

    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bind = FragmentAddedProgressBinding.inflate(layoutInflater)

        val mainActivity: MainActivity = (activity as MainActivity)
        mainActivity.bvNavigation.visibleOrGone = false

        updateTypefaces(bind)
        updateView(bind)
        clickListeners(bind)

        return bind.root
    }


    override fun updateTypefaces(bind: FragmentAddedProgressBinding) {
        typeface.setTypefaceForSubHeaderBold(bind.tbProgressUpload.tvTitle, application.applicationContext)
        typeface.setTypefaceForHeaderBold(bind.clPostProgress.tvProgressAddedTitle, application.applicationContext)
        typeface.setTypefaceForBodyRegular(bind.clPostProgress.tvProgressAddedDesc, application.applicationContext)

        typeface.setTypefaceForSubHeaderBold(bind.clPostProgress.btnAddMoreProgress, application.applicationContext)
        typeface.setTypefaceForSubHeaderBold(bind.clPostProgress.btnContinue, application.applicationContext)
    }

    override fun updateView(bind: FragmentAddedProgressBinding) {
        bind.tbProgressUpload.ibBack.visibleOrGone = viewModel.viewState.toolbarBackButtonVisible
        bind.tbProgressUpload.tvTitle.text = viewModel.viewState.toolbarTitle
    }

    override fun clickListeners(bind: FragmentAddedProgressBinding) {
        bind.tbProgressUpload.ibBack.setOnClickListener {
            viewModel.onAddMoreProgressClicked()
        }
        bind.clPostProgress.btnAddMoreProgress.setOnClickListener {
            viewModel.onAddMoreProgressClicked()
        }
        bind.clPostProgress.btnContinue.setOnClickListener {
            viewModel.onContinueClicked()
        }
    }

}