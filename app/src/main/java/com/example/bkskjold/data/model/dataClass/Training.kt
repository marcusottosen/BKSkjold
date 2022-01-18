package com.example.bkskjold.data.model.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Training(
    val timeStart: com.google.firebase.Timestamp,
    val timeEnd: com.google.firebase.Timestamp,
    val location: String = "",
    val league: String = "",
    val trainer: String = "",
    val description: String = "",
    val maxParticipants: Int = 0,
    var participants: MutableList<String> = mutableListOf(),
    val userBooking: Boolean = false,
) : Parcelable