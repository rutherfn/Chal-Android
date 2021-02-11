package com.nicholasrutherford.chal.account.uploadphoto

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.databinding.ActivityUploadProfilePictureBinding
import com.nicholasrutherford.chal.ext.activitys.UploadPhotoExtension
import com.nicholasrutherford.chal.helpers.Typeface
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission

const val PERMISSION_CODE = 1000
const val IMAGE_CAPTURE_CODE = 1001

const val INTENT_EMAIL = "email"
const val INTENT_PASSWORD = "password"
const val INTENT_USERNAME = "username"

class UploadPhotoActivity : AppCompatActivity(), UploadPhotoExtension {

    private var binding: ActivityUploadProfilePictureBinding? = null
    private val typeface = Typeface()
    private var viewModel: UploadPhotoViewModel? = null
    var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadProfilePictureBinding.inflate(layoutInflater)
        viewModel = UploadPhotoViewModel(this, applicationContext)
        setContentView(binding?.root)

        binding?.let { bind ->
            main(bind)
        }
    }

    override fun main(bind: ActivityUploadProfilePictureBinding) {
        val userEmail = intent.getStringExtra(INTENT_EMAIL)
        val userPassword = intent.getStringExtra(INTENT_PASSWORD)
        val userUsername = intent.getStringExtra(INTENT_USERNAME)

        viewModel?.retrieveAccountInfoFromPreviousActivity(userEmail, userPassword, userUsername)
        updateTypefaces(bind)
        clickListeners(bind)
    }

    fun openCameraForUpload() {
        val buildVersion = Build.VERSION.SDK_INT
        val buildVersionM = Build.VERSION_CODES.M

        if (buildVersion > buildVersionM) {
            val cameraPermission = checkSelfPermission(applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED
            val writeExternalPermission = checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED

            if (!cameraPermission || !writeExternalPermission) {
                val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permission, PERMISSION_CODE)
            } else {
                viewModel?.openCamera()
            }
        } else {
            viewModel?.openCamera()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel?.openCamera()
                } else {
                    // show some type of alert, stating we need permission to continue
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            binding?.cvTakeAPhoto?.setBackgroundDrawable(bitmapDrawable)
        } else if (resultCode == Activity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            binding?.cvTakeAPhoto?.setBackgroundDrawable(bitmapDrawable)
        }
    }

    override fun updateTypefaces(bind: ActivityUploadProfilePictureBinding) {
        typeface.setTypefaceForSubHeaderRegular(bind.tvTakeAPictureOrChooseFromLibrary, applicationContext)

        typeface.setTypefaceForBodyBold(bind.btnChooseFormLibrary, applicationContext)
        typeface.setTypefaceForBodyBold(bind.btnContinueUpload, applicationContext)
    }

    override fun clickListeners(bind: ActivityUploadProfilePictureBinding) {
        bind.cvTakeAPhoto.setOnClickListener {
            openCameraForUpload()
        }
        bind.btnChooseFormLibrary.setOnClickListener {
            viewModel?.openGallery()
        }
        bind.btnContinueUpload.setOnClickListener {
            viewModel?.createUser(selectedPhotoUri)
        }
    }
}