package com.example.bkskjold.data.model.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val header: String,
    val description: String,
    val date: com.google.firebase.Timestamp,
): Parcelable