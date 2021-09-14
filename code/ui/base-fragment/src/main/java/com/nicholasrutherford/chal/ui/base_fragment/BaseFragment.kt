package com.nicholasrutherford.chal.ui.base_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
            println(isProgressUpdated)
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