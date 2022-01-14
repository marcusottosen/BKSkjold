package com.example.bkskjold.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.data.model.dataClass.Locations
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.firebaseAdapter.addToDB
import com.example.bkskjold.data.util.getMonthFromString
import java.text.SimpleDateFormat

class BookTrainingViewModel {

    fun getFields(): MutableList<String> {
        val fields = mutableListOf<String>()
        for (field in Locations.values()){
            fields.add(field.toString())
        }
        return fields
    }

    fun getParticipantList(): MutableList<String>{
        val participantList = mutableListOf<String>()
        for (i in 1..60){
            participantList.add(i.toString())
        }
        return participantList
    }

    fun createTrainingFromBooking(
        location: String,
        date: String,
        startTime: String,
        endTime: String,
        maxParticipants: Int,
        description: String,
        navController: NavController,
        context: Context
    ) {
        try {
            val dateformat = "yyyy-MM-dd-k:m"
            val startTimestamp = com.google.firebase.Timestamp(
                SimpleDateFormat(dateformat).parse(("$date-$startTime").toString()))

            val endTimestamp = com.google.firebase.Timestamp(
                SimpleDateFormat(dateformat).parse(("$date-$endTime").toString()))

            val booking = Training(
                timeStart = startTimestamp,
                timeEnd = endTimestamp,
                location = location,
                league = "Brugerbooking",
                trainer = CurrentUser.id,
                description = description,
                maxParticipants = maxParticipants,
                participants = listOf(CurrentUser.id) as MutableList<String>,
                userBooking = true,
            )
            addToDB(booking, navController)
            Toast.makeText(context, "Bookning af bane oprettet", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            Toast.makeText(context, "Der skete en fejl i oprettelsen! \n Husk at væle dato og tid", Toast.LENGTH_LONG).show()
        }
    }

    fun newTraining(
        location: String,
        month: String,
        day: Int,
        startHour: Int,
        startMin: Int,
        endHour: Int,
        endMin: Int,
        maxParticipants: Int,
        team: String,
        description: String,
        navController: NavController
    ){
        val dateformat = "yyyy-MM-dd-k-m"
        val startTimestamp = com.google.firebase.Timestamp(
            SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$startHour-$startMin").toString()))
        val endTimestamp = com.google.firebase.Timestamp(
            SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$endHour-$endMin").toString()))

        val training = Training(
            timeStart = startTimestamp,
            timeEnd = endTimestamp,
            location = location,
            league = team,
            trainer = CurrentUser.id,
            description = description,
            maxParticipants = maxParticipants,
            participants = mutableListOf<String>(),
            userBooking = false,
        )
        addToDB(training, navController)
    }

}