package com.nicholasrutherford.chal.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.data.isGrateful
import com.nicholasrutherford.chal.data.isUserProfileUpdated
import com.nicholasrutherford.chal.databinding.EditProfileLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.squareup.picasso.Picasso
import java.util.*

class EditProfileFragment : Fragment(), FragmentExt {

    private var bind: EditProfileLayoutBinding? =  null
    private val typeface = Typeface()
    private var screenContext: Context? = null
    private var datePicker: DatePickerDialog? = null

    // when this is ready
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = EditProfileLayoutBinding.inflate(layoutInflater)
        bind()
        updateFragment()
        clickListeners()
        return bind?.root
    }

    override fun onAttach(context: Context) {
        screenContext = context
        super.onAttach(context)
    }

    fun setTypeface() {
        bind?.editFirstNameLayout?.etField?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.editFirstNameLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.editLastNameLayout?.etField?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.editLastNameLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.emailLayout?.etEmailField?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.emailLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.passwordLayout?.etFieldPassword?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.passwordLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.currentLocationLayout?.etLocationField?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.currentLocationLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.currentDateOfBirthLayout?.etField?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.currentDateOfBirthLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.sloganMessageLayout?.etFieldSloganMessage?.let { typeface.setTypefaceForBodyBold(it, it.context) }
        bind?.sloganMessageLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }

        bind?.profileImageLayout?.tvEtLabel?.let { typeface.setTypefaceForHeaderBold(it, it.context) }
    }

    override fun bind() {
        setTypeface()

        // toolbar
        bind?.tbEditProfile?.tvEditProfile?.text = "Edit Profile"

        // first name layout
        bind?.editFirstNameLayout?.tvEtLabel?.text = "First Name"
        bind?.editFirstNameLayout?.etField?.text =  Editable.Factory.getInstance().newEditable("Will")

        // last name layout
        bind?.editLastNameLayout?.tvEtLabel?.text = "Last Name"
        bind?.editLastNameLayout?.etField?.text = Editable.Factory.getInstance().newEditable("Shelton")

        // email layout
        bind?.emailLayout?.etEmailField?.isEnabled = false
        bind?.emailLayout?.tvEtLabel?.text = "Email address"
        bind?.emailLayout?.etEmailField?.text = Editable.Factory.getInstance().newEditable("willshelton11@gmail.com")

        // password layout
        bind?.passwordLayout?.etFieldPassword?.isEnabled = false
        bind?.passwordLayout?.tvEtLabel?.text = "Password"
        bind?.passwordLayout?.etFieldPassword?.text = Editable.Factory.getInstance().newEditable("Password12345")

        // current location
        bind?.currentLocationLayout?.etLocationField?.isEnabled = false
        bind?.currentLocationLayout?.tvEtLabel?.text = "Current Location"
        bind?.currentLocationLayout?.etLocationField?.text = Editable.Factory.getInstance().newEditable("Milwaukee, Wisconsin")

        // date of birth layout
        bind?.currentDateOfBirthLayout?.tvEtLabel?.text = "Date Of Birth"
        bind?.currentDateOfBirthLayout?.etField?.text = Editable.Factory.getInstance().newEditable("05-28-1992")

        // slogan message layout
        bind?.sloganMessageLayout?.tvEtLabel?.text = "Bio"
        bind?.sloganMessageLayout?.etFieldSloganMessage?.text = Editable.Factory.getInstance().newEditable("Here to have fun on the app by accomplishing fitness challenges, add me to learn more.")

        // profile image layout
        bind?.profileImageLayout?.tvEtLabel?.text = "Profile Picture"
        Picasso.get().load(R.drawable.willplaceholder).into(bind?.profileImageLayout?.cvEditProfilePicture)
    }

    override fun updateFragment() {
    }

    private fun exchangeProfilePicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    private fun loadForNewProfileChanges() {
        isUserProfileUpdated = true
        fragmentManager?.let {
            loadingDialog.show(it, "loadingDialog")
        }

        val timer = object: CountDownTimer(3000, 100) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                showMyProfileFragment()
                loadingDialog.dismiss()
            }
        }
        timer.start()
    }

    override fun clickListeners() {
        bind?.currentDateOfBirthLayout?.etField?.setOnClickListener {
            val dpd =
                activity?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    }, year, month, day)
                }

            dpd?.show()
        }

        bind?.tbEditProfile?.ibBack?.setOnClickListener {
            showMyProfileFragment()
            // go back to the edit profile fragment
        }
        bind?.tbEditProfile?.ivSave?.setOnClickListener {
            loadForNewProfileChanges()
            // save data really for now show loading dialog for a couple of seconds then go back to profile
        }

        bind?.profileImageLayout?.cvEditProfilePicture?.setOnClickListener {
            exchangeProfilePicture()
        }
    }

    private fun showMyProfileFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, myProfileFragment, myProfileFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int {
        return R.id.container
    }

}