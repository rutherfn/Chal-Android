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

        supportActionBar!!.title = ""
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

