package com.example.bkskjold.data.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates

data class CurrentUserModel(
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
    val memberSince: com.google.firebase.Timestamp
)

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

fun updateCurrentUser() {
        Firebase.firestore.collection("users-db").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener { result ->
                CurrentUser.id = FirebaseAuth.getInstance().currentUser?.uid.toString()
                CurrentUser.firstName = result["firstName"] as String
                CurrentUser.lastName = result["lastName"] as String
                CurrentUser.email = FirebaseAuth.getInstance().currentUser?.email.toString()
                CurrentUser.address = result["address"] as String
                CurrentUser.phoneNumber = (result["phoneNumber"] as Number).toInt()
                CurrentUser.birthdate = result["birthdate"] as com.google.firebase.Timestamp
                CurrentUser.team = result["team"] as String
                CurrentUser.userType = (result["userType"] as Number).toInt()
                CurrentUser.finishedTrainings = (result["finishedTrainings"] as Number).toInt()
                CurrentUser.memberSince = result["memberSince"] as com.google.firebase.Timestamp
            }
}