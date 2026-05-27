package com.workload.recorder

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature

class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val wv = WebView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                allowFileAccess = false
                allowContentAccess = true
                setSupportZoom(true)
                builtInZoomControls = false
                displayZoomControls = false
                loadWithOverviewMode = true
                useWideViewPort = true
                cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
                mediaPlaybackRequiresUserGesture = false
                mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_NEVER_ALLOW

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    safeBrowsingEnabled = true
                }
            }

            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                WebSettingsCompat.setForceDark(this@apply.settings,
                    WebSettingsCompat.FORCE_DARK_OFF)
            }

            setBackgroundColor(android.graphics.Color.parseColor("#F5F5F7"))

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView, request: WebResourceRequest
                ): Boolean {
                    return false
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView: WebView,
                    filePathCallback: ValueCallback<Array<Uri>>,
                    fileChooserParams: FileChooserParams
                ): Boolean {
                    val intent = fileChooserParams.createIntent().apply {
                        type = "*/*"
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/json", "text/*"))
                    }
                    try {
                        startActivityForResult(
                            Intent.createChooser(intent, "选择备份文件"),
                            FILE_CHOOSER_REQUEST_CODE
                        )
                    } catch (e: Exception) {
                        return false
                    }
                    uploadCallback = filePathCallback
                    return true
                }
            }

            addJavascriptInterface(WebAppInterface(this@MainActivity, this), "AndroidBridge")

            loadUrl("file:///android_asset/www/index.html")
        }
        webView = wv

        setContentView(webView)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    finish()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }

    // File chooser callback
    private var uploadCallback: ValueCallback<Array<Uri>>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILE_CHOOSER_REQUEST_CODE) {
            val callback = uploadCallback
            uploadCallback = null
            if (resultCode == RESULT_OK && data != null) {
                val result = if (data.data != null) {
                    arrayOf(data.data!!)
                } else if (data.clipData != null) {
                    val uris = mutableListOf<Uri>()
                    for (i in 0 until data.clipData!!.itemCount) {
                        uris.add(data.clipData!!.getItemAt(i).uri)
                    }
                    uris.toTypedArray()
                } else {
                    null
                }
                callback?.onReceiveValue(result)
            } else {
                callback?.onReceiveValue(null)
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val FILE_CHOOSER_REQUEST_CODE = 10001
    }
}