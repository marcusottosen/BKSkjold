package com.example.bkskjold.data.model

import com.example.bkskjold.data.util.getMonthFromString
import java.time.LocalDate

fun newTraining(
    field: String,
    month: String,
    day: Int,
    startHour: Int,
    startMin: Int,
    endHour: Int,
    endMin: Int,
    maxParticipants: Int,
    description: String
    ){

    val date = LocalDate.of(2022, getMonthFromString(month),day)
    println(date)


}

/*
Fjern Date, team1, team2, wekkday, price
add: userBooking
Ændre: Trainer: userID

 */