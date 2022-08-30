package com.wadud.bmi.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.view.View
import java.io.FileNotFoundException
import java.io.FileOutputStream


object ImageUtil {

    fun captureView(view: View, filePath: String, bOpen: Boolean,context: Context) {
        view.isDrawingCacheEnabled = true
        val b: Bitmap = view.drawingCache
        try {
            b.compress(CompressFormat.JPEG, 95, FileOutputStream(filePath))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        if (bOpen) {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(Uri.parse("file://$filePath"), "image/*")
            context.startActivity(intent)
        }
    }

}