package com.nicholasrutherford.chal.ui.base_fragment

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
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

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

@Suppress("MagicNumber")
const val GALLERY_TYPE = "image/*"

const val GALLERY_REQUEST_CODE = 0
const val IMAGE_CAPTURE_CODE = 1001

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    var colorBlack: Int? = null
    var colorSmokeWhite: Int? = null

    private var _binding: VB? = null
    val binding get() = _binding!!

    private var fragmentNavigation: BaseFragmentNavigation? = null

    abstract fun updateTypefaces()

    abstract fun collectAlertAsUpdated()

    abstract fun onListener()

    abstract fun updateView()

    private fun setColors(fragmentActivity: FragmentActivity) {
        colorBlack = ContextCompat.getColor(fragmentActivity.applicationContext, R.color.colorBlack)
        colorSmokeWhite = ContextCompat.getColor(fragmentActivity.applicationContext, R.color.colorSmokeWhite)
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = GALLERY_TYPE

        activity?.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun openCamera(title: String, description: String) {
        val values = ContentValues()

        values.put(MediaStore.Images.Media.TITLE, title)
        values.put(MediaStore.Images.Media.DESCRIPTION, description)

        val selectedPhotoUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedPhotoUri)

        if (!hasCameraPermission()) {
            requestPermissions(String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}
                PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
        } else {
            activity?.startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity.applicationContext, android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        requestPermissions(String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
    }

    fun navigateToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = ((Uri.parse(url)))
        activity?.startActivity(intent)
    }

    fun showAlert(title: String, message: String) {
        // exapnd on this down the line
        fragmentNavigation?.showOkAlert(title, message)
    }

    fun showClosingOutAppProgressAlert(resId: Int, title: String, message: String) {
        fragmentNavigation?.showClosingOutAppProgressAlert(resId, title, message)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        this.activity?.let { fragmentActivity ->
            setColors(fragmentActivity)
            fragmentNavigation = BaseFragmentNavigation(fragmentActivity = fragmentActivity)
        }
        updateTypefaces()
        onListener()
        updateView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}