package com.example.bkskjold.data.util

import android.icu.util.Calendar

/**
 * To have more control of how our date is displayed, a translation from googles Timestamp value to strings is being done here.
 */

//11/1
fun getDayMonth(timestamp: com.google.firebase.Timestamp): String {
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    return "${c.get(Calendar.DAY_OF_MONTH)}/${c.get(Calendar.MONTH) + 1}"
}

//11 (day in month)
fun getDay(timestamp: com.google.firebase.Timestamp): String {
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    return c.get(Calendar.DAY_OF_MONTH).toString()
}

//13:30
fun getTime(timestamp: com.google.firebase.Timestamp): String {
    var hour = timestamp.toDate().hours.toString()
    var minute = timestamp.toDate().minutes.toString()
    if (minute.length == 1) {
        minute += 0
    }
    if (hour.length == 1) {
        hour = "0$hour"
    }
    return "${hour}:${minute}"
}

//mandag
fun getWeekDay(timestamp: com.google.firebase.Timestamp): String {
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    val dayOfWeekNum: Int = c.get(Calendar.DAY_OF_WEEK)
    return getWeekFromNum(dayOfWeekNum - 1)
}

//januar
fun getMonthString(timestamp: com.google.firebase.Timestamp): String {
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    val monthNum: Int = c.get(Calendar.MONTH)
    return getMonthFromNum(monthNum + 1)
}

//2022
fun getYear(timestamp: com.google.firebase.Timestamp): String {
    val c: Calendar = Calendar.getInstance()
    c.time = timestamp.toDate()
    return "${c.get(Calendar.YEAR)}"
}

fun getWeekFromNum(num: Int): String {
    val dayOfWeek = when (num) {
        1 -> "mandag"
        2 -> "tirsdag"
        3 -> "onsdag"
        4 -> "torsdag"
        5 -> "fredag"
        6 -> "lørdag"
        7 -> "søndag"
        else -> "søndag"
    }
    return dayOfWeek
}

fun getMonthFromNum(num: Int): String {
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