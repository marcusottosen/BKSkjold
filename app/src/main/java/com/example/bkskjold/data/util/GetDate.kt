package com.example.bkskjold.data.util

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat.getDateInstance

//OBS Kun dag virker!
fun getDate(timestamp: com.google.firebase.Timestamp): String{
    //return "${timestamp.toDate().day}/${timestamp.toDate().month}"
    val c: Calendar = Calendar.getInstance()
    c.setTime(timestamp.toDate())
    return "${c.get(Calendar.DAY_OF_MONTH)}/${c.get(Calendar.MONTH)}"
}

fun getTime(timestamp: com.google.firebase.Timestamp): String{
    return "${timestamp.toDate().hours}:${timestamp.toDate().minutes}"
}

//OBS Virker ikke
fun getWeekDay(timestamp: com.google.firebase.Timestamp): String{
    val c: Calendar = Calendar.getInstance()
    c.setTime(timestamp.toDate())
    val k = getDateInstance().format(c.get(Calendar.DAY_OF_WEEK))
    println("the day: ${getDateInstance().format(c.get(Calendar.DAY_OF_WEEK))}")
    return getDateInstance().format(c.get(Calendar.DAY_OF_WEEK))



    //c.setTime(yourdate) // yourdate is an object of type Date
    //val dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK) // this will for example return 3 for tuesday
    /*
    If you need the output to be "Tue" rather than 3, instead of going through a calendar, just reformat the string: new SimpleDateFormat("EE").format(date) (EE meaning "day of week, short version")
     */

    // c.get(Calendar.MONTH) får fx 3 for tirsdag
}

fun getMonth(): String{
    val month = ""
    // tror der er en SimpleDateFormat("??").format(date) der virker
    return month
}

