package com.example.bkskjold.data.util

import com.example.bkskjold.data.model.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Util {

    //############# FORMAT DATE FROM DATE TO STRING ON FORM "12 januar" ##############################
    fun dateFormatter(day: Int, month: Int): String {
        var finalDay = day.toString()
        var finalMonth: String

        if (month == 1){finalMonth = "januar"}
        else if (month == 2){finalMonth = "februar"}
        else if (month == 3){finalMonth = "marts"}
        else if (month == 4){finalMonth = "april"}
        else if (month == 5){finalMonth = "maj"}
        else if (month == 6){finalMonth = "juni"}
        else if (month == 7){finalMonth = "juli"}
        else if (month == 8){finalMonth = "august"}
        else if (month == 9){finalMonth = "september"}
        else if (month == 10){finalMonth = "oktober"}
        else if (month == 11){finalMonth = "november"}
        else {finalMonth = "december"}

        var formattedDate = "${finalDay} ${finalMonth}"

        return formattedDate
    }

}