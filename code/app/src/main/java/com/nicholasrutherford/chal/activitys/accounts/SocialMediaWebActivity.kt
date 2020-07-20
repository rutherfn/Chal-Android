package com.nicholasrutherford.chal.activitys.accounts

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nicholasrutherford.chal.R

class SocialMediaWebActivity : AppCompatActivity() {

    // declartions

    companion object Social {
        const val INST_GRAM_LINK = "https://www.instagram.com/nicholasrutherford/?hl=en"
        const val TWITTER_LINK = "https://twitter.com/nicholas_r122?lang=en"
        const val FACEBOOK_LINK = "https://www.facebook.com/nicholas.rutherford2"
    }

    lateinit var tbWebView: Toolbar
    lateinit var wvSocial: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_webview)
        main()
    }

    private fun setUpIds() {
        tbWebView = findViewById(R.id.tbWebView)
        wvSocial = findViewById(R.id.wvSocial)
    }

    private fun main() {
        setUpIds()
        setupWebAndToolbar()
        loadUrlToWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setupWebAndToolbar() {
        setSupportActionBar(tbWebView)

        supportActionBar!!.title = "Return To Chal"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        wvSocial.webViewClient = WebViewClient()

        wvSocial.settings.javaScriptEnabled = true
        wvSocial.settings.domStorageEnabled = true
        wvSocial.overScrollMode = WebView.OVER_SCROLL_NEVER
    }

    private fun loadUrlToWebView() {
        val isFacebook = intent.getBooleanExtra("isFacebook", false)
        val isGram = intent.getBooleanExtra("isGram",false)
        val isTwitter = intent.getBooleanExtra("isTwitter", false)

        when {
            isFacebook -> {
                wvSocial.loadUrl(FACEBOOK_LINK)
            }
            isGram -> {
                wvSocial.loadUrl(INST_GRAM_LINK)
            }
            isTwitter -> {
                wvSocial.loadUrl(TWITTER_LINK)
            }
        }
    }

}


//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.MenuItem
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import com.graydientmusuemapp.com.graydientmusuemapp.Model.CollectionCall
//import com.graydientmusuemapp.com.graydientmusuemapp.R
//
///**
// * Created by Nick R.
// */
//
//class WebActivity : AppCompatActivity() {
//
//    // declarations
//    private lateinit var postToolBar : Toolbar
//    private lateinit var myWebView : WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.web_view)
//        postToolBar = findViewById<Toolbar>(R.id.toolBarForWebView)
//        myWebView = findViewById<WebView>(R.id.mywebView)
//        main()
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun main() {
//        setSupportActionBar(postToolBar)
//        supportActionBar!!.title = "Return To Tour"
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        myWebView.webViewClient = WebViewClient()
//        myWebView.settings.javaScriptEnabled = true
//        myWebView.settings.domStorageEnabled = true
//        myWebView.overScrollMode = WebView.OVER_SCROLL_NEVER
//        loadUrl()
//    }
//
//    private fun loadUrl(){
//        if (CollectionCall.switcherGoogleLink == 0) { // depending on what the user clicks, either redirect the user to the second artist or the first artist website.
//            myWebView.loadUrl(CollectionCall.secondArtistWebsiteRedirect)
//        } else {
//            myWebView.loadUrl(CollectionCall.artistWebsite)
//        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        finish()
//        return super.onOptionsItemSelected(item)
//    }
//}
