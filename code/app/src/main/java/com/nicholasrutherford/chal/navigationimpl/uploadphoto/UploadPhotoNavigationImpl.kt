package com.nicholasrutherford.chal.navigationimpl.uploadphoto

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.MediaStore
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.uploadphoto.IMAGE_CAPTURE_CODE
import com.nicholasrutherford.chal.account.uploadphoto.UploadPhotoActivity
import com.nicholasrutherford.chal.account.uploadphoto.UploadPhotoNavigation

const val GALLERY_TYPE = "image/*"
const val GALLERY_REQUEST_CODE = 0

class UploadPhotoNavigationImpl : UploadPhotoNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun createAccountAlert(title: String, message: String, uploadPhotoActivity: UploadPhotoActivity) {
        val createAccountAlertDialogBuilder = AlertDialog.Builder(uploadPhotoActivity)

        createAccountAlertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(uploadPhotoActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val createAccountAlert = createAccountAlertDialogBuilder.create()
        createAccountAlert.setTitle(title)

        createAccountAlert.show()
    }

    override fun showAcProgress(uploadPhotoActivity: UploadPhotoActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(uploadPhotoActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }

    override fun mainActivity(context: Context, uploadPhotoActivity: UploadPhotoActivity) {
        val intent = Intent(context, MainActivity::class.java)

        uploadPhotoActivity.startActivity(intent)
        uploadPhotoActivity.finish()
    }

    override fun openGallery(uploadPhotoActivity: UploadPhotoActivity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = GALLERY_TYPE

        uploadPhotoActivity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun openCamera(uploadPhotoActivity: UploadPhotoActivity) { // come back in here later, and edit the strings for open camera
        val values = ContentValues()

        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Profile Picture from camera")

        uploadPhotoActivity.selectedPhotoUri = uploadPhotoActivity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uploadPhotoActivity.selectedPhotoUri)
        uploadPhotoActivity.startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }
}