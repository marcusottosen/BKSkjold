package com.example.bkskjold.data.util

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat

//11/1
fun getDayMonth(timestamp: com.google.firebase.Timestamp): String{
    val c: Calendar = Calendar.getInstance()
    c.setTime(timestamp.toDate())
    return "${c.get(Calendar.DAY_OF_MONTH)}/${c.get(Calendar.MONTH)+1}"
}

//11 (day in month)
fun getDay(timestamp: com.google.firebase.Timestamp): String{
    val c: Calendar = Calendar.getInstance()
    c.setTime(timestamp.toDate())
    return c.get(Calendar.DAY_OF_MONTH).toString()
}

//13:30
fun getTime(timestamp: com.google.firebase.Timestamp): String{
    return "${timestamp.toDate().hours}:${timestamp.toDate().minutes}"
}

//mandag
fun getWeekDay(timestamp: com.google.firebase.Timestamp): String{
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    val dayOfWeekNum: Int = c.get(Calendar.DAY_OF_WEEK)
    return getWeekFromNum(dayOfWeekNum-1)
}

//januar
fun getMonthString(timestamp: com.google.firebase.Timestamp): String{
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    val monthNum: Int = c.get(Calendar.MONTH)
    println(monthNum)
    return getMonthFromNum(monthNum+1)
}

//2022
fun getYear(timestamp: com.google.firebase.Timestamp): String{
    val c: Calendar = Calendar.getInstance()
    c.setTime(timestamp.toDate())
    return "${c.get(Calendar.YEAR)}"
}

fun getWeekFromNum(num: Int): String{
    val dayOfWeek = when (num) {
        1 -> "mandag"
        2 -> "tirsdag"
        3 -> "onsdag"
        4 -> "torsdag"
        5 -> "fredag"
        6 -> "lørdag"
        7 -> "søndag"
        else -> "null"
    }
    return dayOfWeek
}
fun getMonthFromNum(num: Int): String{
    val month = when (num) {
        1 -> "januar"
        2 -> "februar"
        3 -> "marts"
        4 -> "april"
        5 -> "maj"
        6 -> "juni"
        7 -> "juli"
        8 -> "juli"
        9 -> "september"
        10 -> "oktober"
        11 -> "november"
        12 -> "december"
        else -> "null"
    }
    return month
}

