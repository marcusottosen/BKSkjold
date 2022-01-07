package com.example.bkskjold.data.util

fun getDate(timestamp: com.google.firebase.Timestamp): String{
    return "${timestamp.toDate().day}/${timestamp.toDate().month}"
}

fun getTime(timestamp: com.google.firebase.Timestamp): String{
    return "${timestamp.toDate().hours}:${timestamp.toDate().minutes}"
}


