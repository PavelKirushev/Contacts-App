package com.example.contactsapp

import ContactList
import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import getContacts

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsApp() {
    val context = LocalContext.current
    val contacts = remember { mutableStateOf(emptyList<Contact>()) }

    val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
    val callPermissionState = rememberPermissionState(Manifest.permission.CALL_PHONE)

    LaunchedEffect(contactsPermissionState.status) {
        if (contactsPermissionState.status.isGranted) {
            contacts.value = getContacts(context)
        }
    }

    when {
        !contactsPermissionState.status.isGranted -> {
            PermissionDialog {
                contactsPermissionState.launchPermissionRequest()
            }
        }

        contacts.value.isNotEmpty() -> {
            ContactList(
                contacts = getSortedContacts(contacts.value),
                onContactClick = { phone ->
                    if (callPermissionState.status.isGranted) {
                        makeCall(context, phone)
                    } else {
                        callPermissionState.launchPermissionRequest()
                    }
                }
            )
        }

        else -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text("Контактов не найдено")
            }
        }
    }
}