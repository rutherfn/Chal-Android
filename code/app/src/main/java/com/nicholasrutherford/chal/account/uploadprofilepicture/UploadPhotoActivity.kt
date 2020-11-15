package com.nicholasrutherford.chal.account.uploadprofilepicture

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.realdata.*
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UploadPhotoActivity : AppCompatActivity() {

    // declarations
    private var flowerLoadingDialog: ACProgressFlower? = null
    private lateinit var cvTakeAPhoto: CircleImageView
    private lateinit var tvTakeAPictureOrChooseFromLibrary: TextView
    private lateinit var btnChooseFromLibrary: Button
    private lateinit var btnContinueUpload: Button

    private var username = ""
    private var email = ""
    private var password = ""

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001

    private var selectedPhotoUri: Uri? = null

    private val typeface = Typeface()
    private val helper = Helper()
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
        setTypeface()
    }

    fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(this)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.show()
        }
    }

    fun hideAcProgress() {
        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }

    private fun setupIds() {
        cvTakeAPhoto = findViewById(R.id.cvTakeAPhoto)
        tvTakeAPictureOrChooseFromLibrary = findViewById(R.id.tvTakeAPictureOrChooseFromLibrary)

        btnChooseFromLibrary = findViewById(R.id.btnChooseFormLibrary)
        btnContinueUpload = findViewById(R.id.btnContinueUpload)
    }

    private fun setTypeface() {
        typeface.setTypefaceForSubHeaderRegular(tvTakeAPictureOrChooseFromLibrary, baseContext)

        typeface.setTypefaceForBodyBold(btnChooseFromLibrary, baseContext)
        typeface.setTypefaceForBodyBold(btnContinueUpload, baseContext)
    }

    private fun retrieveDataFromCreateActivity() {
        username = intent.getStringExtra("username")
        email = intent.getStringExtra("email")
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
            attemptToCreateUserWithEmailAndPassword()
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

    private fun attemptToCreateUserWithEmailAndPassword() {

        showAcProgress()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {  task ->

                if(task.isSuccessful) {
                    println("it works")
                    uploadImageToFirebaseStorage()
                }

            }.addOnFailureListener{
                println("error here")
                hideAcProgress()
                // show a error alert here
                // failed to create user for whatever reason
            }
    }

    private fun attemptToCreateUserFirebase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val friends = CurrentFriends(0, "", "", "")
        val friendsList: ArrayList<CurrentFriends> = ArrayList()

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        val comments = Comments(0, "", "", "", 0)
        val commentsList: ArrayList<Comments> = ArrayList()

        commentsList.add(comments)

        val activeChallengesPosts = ActiveChallengesPosts(0, "", "", 0, "", 0, commentsList)
        val activeChallengesPostList: ArrayList<ActiveChallengesPosts> = ArrayList()

        activeChallengesPostList.add(activeChallengesPosts)

        val activeChallenges = ActiveChallenges(0, "", "", 0, currentDate, 0, "", activeChallengesPostList)
        val activeChallengesList: ArrayList<ActiveChallenges> = ArrayList()

        activeChallengesList.add(activeChallenges)

        friendsList.add(friends)
        val newUser = Account(0, username, email, profileImageUrl, password, "", "", "", 0, friendsList, activeChallengesList)

        ref.setValue(newUser)
            .addOnSuccessListener {
                sendUserAQuickEmailVerification()
                hideAcProgress()
                // switch to main activity with onboarding?
            }.addOnFailureListener {
                hideAcProgress()
                // give me a error saying we cant create the account for some odd reason
            }
    }

    private fun sendUserAQuickEmailVerification() {
        val user = FirebaseAuth.getInstance().currentUser
        user!!.sendEmailVerification()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
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
                hideAcProgress()
                println("Failed uploading image up")
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