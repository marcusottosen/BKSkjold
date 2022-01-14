package com.example.bkskjold.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.*
import com.example.bkskjold.data.model.firebaseAdapter.addEventToDB
import com.example.bkskjold.data.model.firebaseAdapter.addTrainingToDB
import java.text.SimpleDateFormat

/**
 * ViewModel for the creation pages: BookFieldPage, CreateEventPage, NewTrainingPage, NewNewsPage
 * They are using the same viewModel as many of the functions are used by several pages.
 *
 */
class DocumentCreationViewModel {
    private val dateformat = "yyyy-MM-dd-k:m"

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

    fun getPriceList(): MutableList<String>{
        val priceOptions = mutableListOf<String>()
        var num = 0
        for (i in 0..10){
            priceOptions.add(num.toString())
            num += 5
        }
        return priceOptions
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
            val booking = Training(
                timeStart = toTimeStamp(date, startTime),
                timeEnd = toTimeStamp(date, endTime),
                location = location,
                league = "Brugerbooking",
                trainer = CurrentUser.id,
                description = description,
                maxParticipants = maxParticipants,
                participants = listOf(CurrentUser.id) as MutableList<String>,
                userBooking = true,
            )
            addTrainingToDB(booking, navController)
            Toast.makeText(context, "Bookning af bane oprettet", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            errorToast(context)
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
            val training = Training(
                timeStart = toTimeStamp(date, startTime),
                timeEnd = toTimeStamp(date, endTime),
                location = location,
                league = team,
                trainer = CurrentUser.id,
                description = description,
                maxParticipants = maxParticipants,
                participants = mutableListOf(),
                userBooking = false,
            )
            addTrainingToDB(training, navController)
            Toast.makeText(context, "Træning oprettet", Toast.LENGTH_SHORT).show()

        }catch (e: Exception){
            errorToast(context)
        }
    }

    fun newEvent(
        header: String,
        description: String,
        location: String,
        date: String,
        startTime: String,
        endTime: String,
        price: Int,
        navController: NavController,
        context: Context
    ){
        try {
            val event = Event(
                timeStart = toTimeStamp(date, startTime),
                timeEnd = toTimeStamp(date, endTime),
                location = location,
                price = price,
                header = header,
                description = description
            )
            addEventToDB(event, navController)
            Toast.makeText(context, "Event oprettet", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception){
            errorToast(context)
        }
    }

    fun toTimeStamp(date: String, time: String): com.google.firebase.Timestamp{
        return com.google.firebase.Timestamp(SimpleDateFormat(dateformat).parse(("$date-$time").toString()))
    }

    fun errorToast(context: Context){
        Toast.makeText(context, "Der skete en fejl i oprettelsen! \n Husk at væle dato og tid", Toast.LENGTH_LONG).show()
    }
}