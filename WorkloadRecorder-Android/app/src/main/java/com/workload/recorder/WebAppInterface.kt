package com.workload.recorder

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

class WebAppInterface(
    private val context: Context,
    private val webView: WebView
) {

    @JavascriptInterface
    fun showToast(message: String) {
        webView.post {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun exportJson(jsonContent: String, fileName: String) {
        try {
            val safeName = fileName.ifBlank { "workload_backup" }
            val finalName = if (safeName.endsWith(".json")) safeName else "$safeName.json"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, finalName)
                    put(MediaStore.Downloads.MIME_TYPE, "application/json")
                    put(MediaStore.Downloads.IS_PENDING, 1)
                }

                val uri = context.contentResolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues
                )

                uri?.let {
                    context.contentResolver.openOutputStream(it)?.use { outputStream ->
                        outputStream.write(jsonContent.toByteArray(Charsets.UTF_8))
                    }

                    contentValues.clear()
                    contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
                    context.contentResolver.update(it, contentValues, null, null)

                    webView.post {
                        Toast.makeText(context, "导出成功: $finalName", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                val downloadsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                )
                val file = File(downloadsDir, finalName)
                FileOutputStream(file).use { outputStream ->
                    outputStream.write(jsonContent.toByteArray(Charsets.UTF_8))
                }

                webView.post {
                    Toast.makeText(context, "导出成功: $finalName", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            webView.post {
                Toast.makeText(context, "导出失败: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}