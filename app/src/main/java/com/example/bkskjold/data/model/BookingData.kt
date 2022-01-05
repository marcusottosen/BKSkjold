package com.example.bkskjold.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class BookingData {
    /*
    var training1  = listOf(1 , "21:00", "22:00", "mandag", "25 oktober", "Bane C", "Senior", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training2  = listOf(2 , "17:00", "22:00", "mandag", "25 oktober", "Bane A", "U20", "Træner: Ekkart", 1, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training3  = listOf(3 , "17:00", "22:00", "mandag", "25 oktober", "Bane B", "U20", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training4  = listOf(4 , "17:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", 1, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training5  = listOf(5 , "18:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", 1, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training6  = listOf(6 , "17:00", "22:00", "mandag", "25 oktober", "Bane D", "U20", "Træner: Ekkart", 1, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training7  = listOf(7 , "17:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training8  = listOf(8 , "17:00", "22:00", "mandag", "25 oktober", "Bane F", "U20", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training9  = listOf(9 , "17:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training10 = listOf(10, "16:30", "22:00", "mandag", "26 oktober", "Bane A", "U20", "Træner: Ekkart", 1, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training11 = listOf(11, "17:00", "22:00", "mandag", "26 oktober", "Bane C", "U20", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    var training12 = listOf(12, "17:00", "22:00", "mandag", "26 oktober", "Bane D", "U20", "Træner: Ekkart", 0, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)

    fun getAllTraining(): List<List<Any>> {
        /* TODO This should eventually fetch the data from a database.
            This should be all available training, including the ones
             already signed up for. This should take a parameter,
             to find the specific users data and trainings.*/
        var allTrainings = listOf(training1, training2, training3, training4, training5, training6, training7, training8, training9, training10, training11, training12)

        return allTrainings
    }

    fun getSignedUpTrainings(): MutableList<List<Any>>? { //
    /* TODO This should eventually fetch the data from a database.
            Data should include all the current trainings a user has signed up for.
            This method should probably take a parameter, to find the correct user and data */
        var allTrainings = getAllTraining()
        var signedUpTrainings: MutableList<List<Any>> = mutableListOf()

        for (i in allTrainings.indices){
            var current = allTrainings[i]

            if(current[7] == 0){
                signedUpTrainings?.add(current)
            }
        }
        return signedUpTrainings
    }*/

    //TODO Fetch from database instead
    val bookings = listOf(
        Training(1 , "21:00", "22:00", "mandag", "25 oktober", "Bane C", "Senior", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(2 , "17:00", "22:00", "mandag", "25 oktober", "Bane A", "U20", "Træner: Ekkart", true, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(3 , "17:00", "22:00", "mandag", "25 oktober", "Bane B", "U20", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(4 , "17:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", true, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(5 , "18:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", true, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(6 , "17:00", "22:00", "mandag", "25 oktober", "Bane D", "U20", "Træner: Ekkart", true, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(7 , "17:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(8 , "17:00", "22:00", "mandag", "25 oktober", "Bane F", "U20", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(9 , "17:00", "22:00", "mandag", "25 oktober", "Bane C", "U20", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(10, "16:30", "22:00", "mandag", "26 oktober", "Bane A", "U20", "Træner: Ekkart", true, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(11, "17:00", "22:00", "mandag", "26 oktober", "Bane C", "U20", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training(12, "17:00", "22:00", "mandag", "26 oktober", "Bane D", "U20", "Træner: Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3)
    )

    fun getSignedUpTrainings(): MutableList<Training> {
        var bookings = bookings
        var signedUpTrainings: MutableList<Training> = mutableListOf()

        for (training in bookings){
            if (training.isAttending){
                signedUpTrainings.add(training)
            }
        }
        return signedUpTrainings
    }
}


@Parcelize
data class Training(
    val id: Int,
    val timeStart: String,
    val timeEnd: String,
    val weekday: String,
    val date: String,
    val location: String,
    val league: String,
    val trainer: String,
    val isAttending: Boolean,
    val price: Int,
    val description: String,
    val maxParticipants: Int,
    val team1: Int,
    val team2: Int
    ): Parcelable
