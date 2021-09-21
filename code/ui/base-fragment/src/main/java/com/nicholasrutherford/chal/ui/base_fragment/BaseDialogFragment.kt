package com.nicholasrutherford.chal.ui.base_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

typealias dialogInflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseDialogFragment<VB: ViewBinding>(
    private val dialogInflate: dialogInflate<VB>
) : DialogFragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    var fragmentNavigation: BaseFragmentNavigation? = null

    abstract fun updateTypefaces()

    abstract fun collectAlertAsUpdated()

    abstract fun onListener()

    abstract fun updateView()

    fun showAlert(title: String, message: String) {
        // exapnd on this down the line
        fragmentNavigation?.showOkAlert(title, message)
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
        _binding = dialogInflate.invoke(inflater, container, false)
        this.activity?.let { fragmentActivity ->
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