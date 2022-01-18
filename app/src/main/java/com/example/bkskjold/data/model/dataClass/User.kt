package com.example.bkskjold.data.model.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.properties.Delegates

@Parcelize
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String,
    val phoneNumber: Int,
    val birthdate: com.google.firebase.Timestamp,
    val team: String,
    val userType: Int,
    val finishedTrainings: Int,
    val memberSince: com.google.firebase.Timestamp,
) : Parcelable

object CurrentUser {
    lateinit var id: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var address: String
    var phoneNumber by Delegates.notNull<Int>()
    lateinit var birthdate: com.google.firebase.Timestamp
    lateinit var team: String
    var userType by Delegates.notNull<Int>()
    var finishedTrainings by Delegates.notNull<Int>()
    lateinit var memberSince: com.google.firebase.Timestamp
}