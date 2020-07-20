package com.nicholasrutherford.chal.activitys.accounts

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.fragments.ErrorCreateAccount
import com.nicholasrutherford.chal.fragments.LoadingDialog
import com.nicholasrutherford.chal.fragments.SuccessCreateAccountDialog
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.models.UserAccount
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class UploadPhotoActivity : AppCompatActivity() {

    // declarations
    private lateinit var tbUploadPhoto: Toolbar
    private lateinit var tvClickMe: TextView
    private lateinit var cvTakeAPhoto: CircleImageView
    private lateinit var tvTakeAPictureOrChooseFromLibrary: TextView
    private lateinit var btnChooseFromLibrary: Button
    private lateinit var btnAddPhotoLater: Button
    private lateinit var btnClearPhoto: Button
    private lateinit var btnContinueUpload: Button

    private var fullName = ""
    private var userName = ""
    private var email = ""
    private var phone = ""
    private var password = ""

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001

    private var selectedPhotoUri: Uri? = null

    private val typeface = Typeface()
    private val helper = Helper()

    private var loadingDialog = LoadingDialog()
    private var successCreateAccountDialog = SuccessCreateAccountDialog()
    private var errorCreateAccountDialog = ErrorCreateAccount()
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_profile_picture)
        main()
    }

    private fun main() {
        setupView()
        retrieveDataFromCreateActivity()
        listenersForUpload()
      //  attemptToCreateUserFirebase()
    }

    private fun setupView() {
        setupIds()
        btnClearPhoto.visibility = View.GONE
        setupUploadPhotoToolbar()
        setTypeface()
        setTextViewsColor()
    }

    private fun setupIds() {

        tbUploadPhoto = findViewById(R.id.tbUploadPhoto)
        tvClickMe = findViewById(R.id.tvClickMe)
        cvTakeAPhoto = findViewById(R.id.cvTakeAPhoto)
        tvTakeAPictureOrChooseFromLibrary = findViewById(R.id.tvTakeAPictureOrChooseFromLibrary)

        btnChooseFromLibrary = findViewById(R.id.btnChooseFormLibrary)
        btnAddPhotoLater = findViewById(R.id.btnAddPhotoLater)
        btnClearPhoto = findViewById(R.id.btnClearPhoto)
        btnContinueUpload = findViewById(R.id.btnContinueUpload)
    }

    private fun setupUploadPhotoToolbar() {

        setSupportActionBar(tbUploadPhoto)

        supportActionBar!!.title = "Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setTypeface() {

        typeface.setTypefaceForHeaderBold(tvClickMe, baseContext)
        typeface.setTypefaceForSubHeaderRegular(tvTakeAPictureOrChooseFromLibrary, baseContext)

        typeface.setTypefaceForBodyBold(btnChooseFromLibrary, baseContext)
        typeface.setTypefaceForBodyBold(btnAddPhotoLater, baseContext)
        typeface.setTypefaceForBodyBold(btnClearPhoto, baseContext)
        typeface.setTypefaceForBodyBold(btnContinueUpload, baseContext)
    }

    private fun setTextViewsColor() {

        helper.setTextViewColor(baseContext, tvClickMe, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvTakeAPictureOrChooseFromLibrary, R.color.colorPrimary)

        helper.setTextViewColor(baseContext, btnChooseFromLibrary, R.color.colorBlack)
        helper.setTextViewColor(baseContext, btnAddPhotoLater, R.color.colorBlack)
        helper.setTextViewColor(baseContext, btnClearPhoto, R.color.colorBlack)
        helper.setTextViewColor(baseContext, btnContinueUpload, R.color.colorBlack)
    }

    private fun retrieveDataFromCreateActivity() {

        fullName = intent.getStringExtra("fullName")
        userName = intent.getStringExtra("username")
        email = intent.getStringExtra("email")
        phone = intent.getStringExtra("phone")
        password = intent.getStringExtra("password")
    }

    private fun listenersForUpload() {

        cvTakeAPhoto.setOnClickListener {
            attemptToTakeAPictureForUser()
        }

        btnChooseFromLibrary.setOnClickListener {
            choosePictureFromLibrary()
        }

        btnContinueUpload.setOnClickListener {
            uploadImageToFirebaseStorage()
        }

    }

    private fun attemptToTakeAPictureForUser() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
                //permission was not enabled
                val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                //show popup to request permission
                requestPermissions(permission, PERMISSION_CODE)
            }
            else{
                //permission already granted
                openCamera()
            }
        }
        else{
            //system os is < marshmallow
            openCamera()
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        selectedPhotoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedPhotoUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    private fun choosePictureFromLibrary() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    private fun attemptToCreateUserFirebase(profileImageurl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val newUser = UserAccount(fullName, userName, email, phone, password, profileImageurl)

        ref.setValue(newUser)
            .addOnSuccessListener {
                loadingDialog.dismiss()
                successCreateAccountDialog.show(fm, "SuccessCreateAccountDialog")
            }.addOnFailureListener {
                loadingDialog.dismiss()
                errorCreateAccountDialog.show(fm, "ErrorCreatingAccountDialog")
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera()
                }
                else{
                    //permission from popup was denied
                }
            }
        }
    }

    private fun uploadImageToFirebaseStorage() {
        if(selectedPhotoUri ==  null) return

        loadingDialog.show(fm, "LoadingDialog")
        var filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                // it did it
                ref.downloadUrl.addOnSuccessListener {
                    it.toString()
                    attemptToCreateUserFirebase(it.toString())
                }
            }
            .addOnFailureListener {
                // it failed for some reason
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
           selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            cvTakeAPhoto.setBackgroundDrawable(bitmapDrawable)

        } else if(resultCode == Activity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            cvTakeAPhoto.setBackgroundDrawable(bitmapDrawable)

        }

        }

}