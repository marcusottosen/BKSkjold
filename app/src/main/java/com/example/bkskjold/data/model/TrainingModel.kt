package com.example.bkskjold.data.model
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize

/*
// *** Uncomment hvis dataen skal tilføjes. (Uncomment også i PreloadDB.kt ***
fun trainingsWriteToDB() {
    val bookings = listOf(
        Training("21:00", "22:00", "mandag", "10 januar", "Bane C", "Senior", "Ekkart", false, 10, "Normal træning for u13. Kom i god tid!", 6, 3, 2),
        Training("14:30", "18:30", "tirsdag", "11 januar", "Bane A", "U21", "Ekkart", true, 20, "Normal træning for u13. Kom i god tid!", 12, 5, 6),
        Training("19:45", "22:00", "onsdag", "12 januar", "Bane B", "U20", "Ian", false, 21, "Kom glad. Husk vand", 12, 5, 3),
        Training("17:00", "18:30", "onsdag", "12 januar", "Bane C", "U20", "Kasper", true, 20, "Normal træning for u13. Kom i god tid!", 24, 10, 9),
        Training("18:00", "22:00", "torsdag", "13 januar", "Bane E", "U22", "Ekkart", true, 25, "Kom glad. Husk vand", 12, 2, 3),
        Training("14:30", "15:30", "tirsdag", "13 januar", "Bane D", "U20", "Ian", true, 20, "Ekkart tager mad med", 10, 2, 3),
        Training("19:45", "22:00", "mandag", "10 januar", "Bane C", "U14", "Ekkart", false, 20, "Normal træning for u13. Kom i god tid!", 12, 2, 3),
        Training("17:00", "18:30", "fredag", "14 januar", "Bane F", "U14", "Peter", true, 25, "Ekkart tager mad med", 12, 2, 3),
        Training("14:30", "22:00", "mandag", "10 januar", "Bane Y", "U20", "Ian", false, 10, "Ian tager mad med", 12, 2, 3),
        Training("16:30", "18:30", "mandag", "10 januar", "Bane A", "U26", "Peter", true, 20, "Peter kom til tiden denne gang! Tak!!", 12, 7, 3),
        Training("19:45", "22:00", "fredag", "14 januar", "Bane C", "Senior", "Ekkarts far", false, 20, "Ældre mennesker der spiller fodbold", 12, 6, 5),
        Training("17:00", "19:45", "mandag", "10 januar", "Bane D", "U20", "Ian", false, 20, "Peter kom til tiden denne gang! Tak!!", 12, 3, 4)
    )

    val db = Firebase.firestore
    for (i in 1..bookings.size) {
        db.collection("trainings")
            .add(bookings[i-1])
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
                        //Telmeldte personer
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
                        participants    = (doc["participants"]) as List<String>,
                        team1           = (doc["team1"] as Number).toInt(),
                        team2           = (doc["team2"] as Number).toInt()
                    )
                )
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

fun updateParticipants(training: Training){
    val userId = "ybdyou1ynl9KfgHjn6Mq" // TODO THIS SHOULD BE CHANGES WHEN PROFILE LOGIN IS AVAILABLE - shouldny be hardcoded

    val db = Firebase.firestore
    // TODO THIS METHODE IS PRONE FOR ERROR: MAKE SURE TO CHECK USER DOESNT GET ADDED TWICE
    //var dbTraining: QueryDocumentSnapshot? = null
    //var dbTraining
    db.collection("trainings")
        .get()
        .addOnSuccessListener { result ->
            for (doc in result) {
                if (doc["date"] == training.date && doc["location"] == training.location && doc["timeStart"] == training.timeStart){
                    val dbTraining = doc
                    //doc.reference
                    //var participants = training.participants
                    //var mutableList = participants.toMutableList()
                    //mutableList.add(userId)
                    //var updatedList = listOf(mutableList)
                    val updateable = db.collection("trainings").document(dbTraining.reference.toString())

                    updateable
                        .update("participants", FieldValue.arrayUnion(userId))
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                    print("")

                }
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
        }
    //val updatedList = training.participants.toMutableList().add(userId)

    //val updateableTraining = db.collection("trainings").document(trainingRef)
    /*if (dbTraining != null){
        try {
            val updateable = db.collection("trainings").document(dbTraining!!.reference.toString())
            print("")
        }catch (e: Exception){}

    }*/




}


@Parcelize
data class Training(
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
    val participants: List<String> = listOf(),
    val team1: Int = 0,
    val team2: Int = 0
): Parcelable


