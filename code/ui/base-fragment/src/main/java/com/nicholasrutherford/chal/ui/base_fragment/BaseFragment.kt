package com.nicholasrutherford.chal.ui.base_fragment

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import android.os.Build
import androidx.navigation.fragment.findNavController
import com.nicholasrutherford.chal.data.elert.Alert
import com.nicholasrutherford.chal.data.elert.AlertType

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

@Suppress("MagicNumber")
const val GALLERY_TYPE = "image/*"

// used to rotate a image 360 degrees
const val ROTATE_IMAGE_360 = 360f

const val GALLERY_REQUEST_CODE = 0
const val CAMERA_CAPTURE_REQUEST = 1888
const val CAMERA_PERMISSION_CODE = 100

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    var colorBlack: Int? = null
    var colorSmokeWhite: Int? = null

    private var _binding: VB? = null
    val binding get() = _binding ?: null

    private var fragmentNavigation: BaseFragmentNavigation? = null

    abstract fun updateTypefaces()

    abstract fun collectAlertAsUpdated()

    abstract fun onListener()

    abstract fun updateView()

    private fun setColors(fragmentActivity: FragmentActivity) {
        colorBlack = ContextCompat.getColor(fragmentActivity.applicationContext, R.color.colorBlack)
        colorSmokeWhite =
            ContextCompat.getColor(fragmentActivity.applicationContext, R.color.colorSmokeWhite)
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = GALLERY_TYPE

        activity?.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (!hasCameraPermission()) {
            requestCameraPermission()
        } else {
            activity?.startActivityForResult(cameraIntent, CAMERA_CAPTURE_REQUEST)
        }
    }

    private fun hasCameraPermission(): Boolean {
        return activity?.applicationContext?.let {
            ContextCompat.checkSelfPermission(
                it, Manifest.permission.CAMERA
            )
        } == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    fun navigateToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = ((Uri.parse(url)))
        activity?.startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) { // do some work here in the future
    }

    fun showAlert(id: Int, title: String?, message: String?, shouldCloseApp: Boolean, alertType: AlertType?) {
        if (alertType == AlertType.REGULAR_OK_ALERT) {
            fragmentNavigation?.showOkAlert(title, message)
        } else if (alertType == AlertType.YES_ALERT_WITH_ACTION) {
            fragmentNavigation?.showYesAlert(id, title, message, shouldCloseApp)
        } else if (alertType == AlertType.YES_NO_ALERT_WITH_ACTION) {
            fragmentNavigation?.showYesAndNoAlert(id, title, message)
        }
    }

    fun showOkAlert(title: String, message: String) {
        // exapnd on this down the line
        fragmentNavigation?.showOkAlert(title, message)
    }

    fun Bitmap.rotate(degrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    fun showClosingOutAppProgressAlert(resId: Int, title: String, message: String, shouldCloseApp: Boolean) {
        fragmentNavigation?.showYesAlert(resId, title, message, shouldCloseApp)
    }

    suspend fun collectViewStateResult(viewStateUpdated: StateFlow<Boolean>, _viewStateAsUpdated: MutableStateFlow<Boolean>) {
        viewStateUpdated.collect { isViewStateUpdated ->
            if (isViewStateUpdated) {
                updateView()
            }
            _viewStateAsUpdated.value = false
        }
    }

    suspend fun collectShouldShowProgressResult(shouldShowProgress: StateFlow<Boolean>, _shouldShowProgress: MutableStateFlow<Boolean>) {
        shouldShowProgress.collect { isProgressUpdated ->
            if (isProgressUpdated) {
                fragmentNavigation?.showFlowerProgess()
            }
            _shouldShowProgress.value = false
        }
    }

    suspend fun collectShouldDismissProgressResult(shouldDismProgress: StateFlow<Boolean>, _shouldDismissProgress: MutableStateFlow<Boolean>) {
        shouldDismProgress.collect { isDismissProgressUpdated ->
            if (isDismissProgressUpdated) {
                fragmentNavigation?.hideFlowerProgress()
            }
            _shouldDismissProgress.value = false
        }
    }

    suspend fun collectShouldShowAlertResult(
        id: Int,
        shouldShowAlert: StateFlow<Alert>,
        _shouldShowAlert: MutableStateFlow<Alert>
        ) {
        shouldShowAlert.collect { isShouldShowAlertUpdated ->
            if (isShouldShowAlertUpdated.type != null) {
                showAlert(
                    id = id,
                    title = isShouldShowAlertUpdated.title,
                    message = isShouldShowAlertUpdated.message,
                    shouldCloseApp = isShouldShowAlertUpdated.shouldCloseAppAfterDone,
                    alertType = isShouldShowAlertUpdated.type
                )
            }
            _shouldShowAlert.value = Alert(null, null, null, false)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        this.activity?.let { fragmentActivity ->
            setColors(fragmentActivity)
            fragmentNavigation = BaseFragmentNavigation(fragmentActivity = fragmentActivity)
        }
        updateTypefaces()
        onListener()
        updateView()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}