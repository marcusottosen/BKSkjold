package com.example.bkskjold.data.model.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val timeStart: com.google.firebase.Timestamp,
    val timeEnd: com.google.firebase.Timestamp,
    val location: String,
    var participants: MutableList<String>,
    val price: Int,
    val header: String,
    val description: String,
) : Parcelable