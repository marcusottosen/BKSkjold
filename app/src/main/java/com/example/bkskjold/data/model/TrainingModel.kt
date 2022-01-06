package com.example.bkskjold.data.model
import android.content.ContentValues
import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize

//class BookingData {
    /*val bookings = listOf(
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
    )*/
    /*
fun writeToDB() {
    val db = Firebase.firestore
    for (i in 1..trainings.size) {
        db.collection("trainings")
            .add(trainings[i-1])
            .addOnSuccessListener { documentReference ->
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }
}*/
//}

val trainings: MutableList<Training> = mutableListOf()

fun loadTrainingsFromDB(): MutableList<Training>{

    val db = Firebase.firestore
    db.collection("trainings")
        .get()
        .addOnSuccessListener { result ->
            for (doc in result) {
                val test : Number = doc["price"] as Number
                //val training = doc.toObject(Training::class.java) //Virker hvis der ikke bruges Int
                //trainings.add(training)

                trainings.add(
                    Training(
                        timeStart       = doc["timeStart"] as String,
                        timeEnd         = doc["timeEnd"] as String,
                        weekday         = doc["weekday"] as String,
                        date            = doc["date"] as String,
                        location        = doc["location"] as String,
                        league          = doc["league"] as String,
                        trainer         = doc["trainer"] as String,
                        attending       = doc["attending"] as Boolean,
                        price           = (doc["price"] as Number).toInt(),
                        description     = doc["description"] as String,
                        maxParticipants = (doc["maxParticipants"] as Number).toInt(),
                        team1           = (doc["team1"] as Number).toInt(),
                        team2           = (doc["team2"] as Number).toInt(),
                        id              = (doc["id"] as Number).toInt()
                    )
                )
                println(trainings.size)
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
        }
    return trainings
}

fun getSignedUpTrainings(): MutableList<Training> {
    val signedUpTrainings: MutableList<Training> = mutableListOf()

    for (training in trainings){
        if (training.attending){
            signedUpTrainings.add(training)
        }
    }
    return signedUpTrainings
}

@Parcelize
data class Training(
    val id: Int = 0,
    val timeStart: String = "",
    val timeEnd: String= "",
    val weekday: String= "",
    val date: String= "",
    val location: String= "",
    val league: String= "",
    val trainer: String= "",
    val attending: Boolean = false,
    val price: Int,
    val description: String= "",
    val maxParticipants: Int = 0,
    val team1: Int = 0,
    val team2: Int = 0
): Parcelable
