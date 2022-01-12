package com.example.bkskjold.data.model

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.util.getDayMonth
import com.example.bkskjold.data.util.getMonthFromString
import com.example.bkskjold.data.util.getTime
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun newTrainingFromBooking(
    location: String,
    month: String,
    day: Int,
    startHour: Int,
    startMin: Int,
    endHour: Int,
    endMin: Int,
    maxParticipants: Int,
    description: String,
    navController: NavController
) {
    val dateformat = "yyyy-MM-dd-k-m"
    val startTimestamp = com.google.firebase.Timestamp(
        SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$startHour-$startMin").toString()))
    val endTimestamp = com.google.firebase.Timestamp(
        SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$endHour-$endMin").toString()))

    val booking = Training(
        timeStart = startTimestamp,
        timeEnd = endTimestamp,
        location = location,
        league = "Brugerbooking",
        trainer = CurrentUser.id,
        description = description,
        maxParticipants = maxParticipants,
        participants = listOf(CurrentUser.id),
        userBooking = true,
    )
    addToDB(booking, navController)
}

fun createTrainingInDB(
    location: String,
    month: String,
    day: Int,
    startHour: Int,
    startMin: Int,
    endHour: Int,
    endMin: Int,
    maxParticipants: Int,
    league: String,
    description: String,
    navController: NavController
){
    val dateformat = "yyyy-MM-dd-k-m"
    val startTimestamp = com.google.firebase.Timestamp(
        SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$startHour-$startMin").toString()))
    val endTimestamp = com.google.firebase.Timestamp(
        SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$endHour-$endMin").toString()))

    val booking = Training(
        timeStart = startTimestamp,
        timeEnd = endTimestamp,
        location = location,
        league = league,
        trainer = CurrentUser.id,
        description = description,
        maxParticipants = maxParticipants,
        participants = listOf(CurrentUser.id),
        userBooking = true,
    )
    addToDB(booking, navController)
}


fun addToDB(item: Training, navController: NavController){
    val db = Firebase.firestore
    db.collection("trainings")
        .add(item)
        .addOnSuccessListener { documentReference ->
            Log.d(
                ContentValues.TAG,
                "DocumentSnapshot added with ID: ${documentReference.id}"
            )
        }
        .addOnCompleteListener {
            if (it.isSuccessful) {
                navController.navigate("bookedFieldsPage")
            }
        }
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error adding document", e)
        }
}