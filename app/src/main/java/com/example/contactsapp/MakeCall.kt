package com.example.contactsapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri


@SuppressLint("QueryPermissionsNeeded")
fun makeCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_CALL).apply {
        data = "tel:$phoneNumber".toUri()
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}