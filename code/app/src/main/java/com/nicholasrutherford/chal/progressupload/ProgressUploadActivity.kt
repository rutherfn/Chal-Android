package com.nicholasrutherford.chal.progressupload

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityProgressUploadBinding
import com.nicholasrutherford.chal.ext.activitys.ProgressUploadExtension
import com.nicholasrutherford.chal.helpers.Typeface

class ProgressUploadActivity : AppCompatActivity(), ProgressUploadExtension {

    private var binding: ActivityProgressUploadBinding? = null
    private val typeface = Typeface()
    private var viewModel: ProgressUploadViewModel? = null
    var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressUploadBinding.inflate(layoutInflater)
        viewModel = ProgressUploadViewModel(this, applicationContext, containerId())

        setContentView(binding?.root)

        binding?.let { bind ->
            viewModel?.let { progressUploadViewModel ->
                main(bind, progressUploadViewModel)
            }
        }
    }

    override fun main(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        updateTypefaces(bind, viewModel)
        updateSpinners(bind, viewModel)
        clickListeners(bind, viewModel)
    }

    override fun updateTypefaces(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        typeface.setTypefaceForHeaderBold(bind.clPostProgress.tvPostProgress, applicationContext)
        typeface.setTypefaceForBodyRegular(bind.clPostProgress.tvPostProgress, applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectChallenge, applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectCategory, applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvAddCaption, applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvUploadImage, applicationContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnPostProgressToMyFeed, applicationContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnCancelAndDiscardPost, applicationContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            binding?.clPostProgress?.ivUploadImage?.setBackgroundDrawable(bitmapDrawable)
        } else if (resultCode == Activity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            binding?.clPostProgress?.ivUploadImage?.setBackgroundDrawable(bitmapDrawable)
        }
    }

    override fun updateSpinners(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        val listOfCategories = arrayOf("Lifestyle")
        val listOfChallenges = arrayOf("7 Days Meditation")

        val challengesAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listOfChallenges)
        val categoryAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listOfCategories)

        challengesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        bind.clPostProgress.spSelectChallenge.adapter = challengesAdapter
        bind.clPostProgress.spSelectCategory.adapter = categoryAdapter
    }

    override fun clickListeners(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel) {
        bind.clPostProgress.btnCancelAndDiscardPost.setOnClickListener {
            viewModel.onDiscardPostClicked()
        }
        bind.clPostProgress.ivUploadImage.setOnClickListener {
            viewModel.onPhotoClicked()
        }
        bind.clPostProgress.btnPostProgressToMyFeed.setOnClickListener {
            val title = bind.clPostProgress.spSelectChallenge.selectedItem.toString()
            val category = bind.clPostProgress.spSelectCategory.selectedItem.toString()
            val caption = bind.clPostProgress.etAddCaption.text.toString()

            viewModel.onPostProgressClicked(title, caption, category, selectedPhotoUri)
        }
    }
}