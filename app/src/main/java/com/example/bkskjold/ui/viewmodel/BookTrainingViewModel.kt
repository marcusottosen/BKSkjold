package com.example.bkskjold.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.data.model.dataClass.Locations
import com.example.bkskjold.data.model.dataClass.Teams
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.firebaseAdapter.addToDB
import java.text.SimpleDateFormat

class BookTrainingViewModel {
    fun getFields(): MutableList<String> {
        val fields = mutableListOf<String>()
        for (field in Locations.values()){
            fields.add(field.toString())
        }
        return fields
    }

    fun getTeams(): MutableList<String> {
        val teams = mutableListOf<String>()
        for (team in Teams.values()){
            teams.add(team.toString())
        }
        return teams
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
            val startTimestamp = com.google.firebase.Timestamp(SimpleDateFormat(dateformat).parse(("$date-$startTime").toString()))
            val endTimestamp = com.google.firebase.Timestamp(SimpleDateFormat(dateformat).parse(("$date-$endTime").toString()))

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
        date: String,
        startTime: String,
        endTime: String,
        maxParticipants: Int,
        description: String,
        team: String,
        navController: NavController,
        context: Context
    ){
        try {
            val dateformat = "yyyy-MM-dd-k:m"
            val startTimestamp =
                com.google.firebase.Timestamp(SimpleDateFormat(dateformat).parse(("$date-$startTime").toString()))
            val endTimestamp =
                com.google.firebase.Timestamp(SimpleDateFormat(dateformat).parse(("$date-$endTime").toString()))

            val training = Training(
                timeStart = startTimestamp,
                timeEnd = endTimestamp,
                location = location,
                league = team,
                trainer = CurrentUser.id,
                description = description,
                maxParticipants = maxParticipants,
                participants = mutableListOf(),
                userBooking = false,
            )
            addToDB(training, navController)
            Toast.makeText(context, "Træning oprettet", Toast.LENGTH_SHORT).show()

        }catch (e: Exception){
            Toast.makeText(context, "Der skete en fejl i oprettelsen! \n Husk at væle dato og tid", Toast.LENGTH_LONG).show()
        }
    }
}