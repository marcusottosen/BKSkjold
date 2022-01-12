package com.example.bkskjold.data.model
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize

/*
// *** Uncomment hvis dataen skal tilføjes. (Uncomment også i LoadFromDB.kt ***
fun trainingsWriteToDB() {
    val bookings = listOf(
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane C", "Use", "Ekkart",  "Normal træning for u13. Kom i god tid!"     , 6,  listOf("4Tjz5r8ckZDua7Gl9cXg"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane A", "U21", "Ekkart",  "Normal træning for u13. Kom i god tid!"     , 12, listOf("4Tjz5r8ckZDua7Gl9cXg"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane B", "U20", "Ian",  "Kom glad. Husk vand"                           , 12, listOf("uqYviRk77BegdJdx9BW5"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane C", "U20", "Kasper",  "Normal træning for u13. Kom i god tid!"     , 24, listOf("uqYviRk77BegdJdx9BW5"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane E", "U22", "Ekkart",  "Kom glad. Husk vand"                        , 12, listOf("uqYviRk77BegdJdx9BW5"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane D", "U20", "Ian", "Ekkart tager mad med"                           , 10, listOf("4Tjz5r8ckZDua7Gl9cXg"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane C", "U14", "Ekkart", "Normal træning for u13. Kom i god tid!"      , 12, listOf("4Tjz5r8ckZDua7Gl9cXg"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane F", "U14", "Peter", "Ekkart tager mad med"                         , 24, listOf("OwSPgideDIgGiOzaUXWo"), true),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane Y", "U20", "Ian",  "Ian tager mad med"                             , 12, listOf("OwSPgideDIgGiOzaUXWo"), true),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane A", "U26", "Peter", "Peter kom til tiden denne gang! Tak!!"        , 12, listOf("OwSPgideDIgGiOzaUXWo"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane C", "Use", "Ekkarts far",  "Ældre mennesker der spiller fodbold"   , 12, listOf("4Tjz5r8ckZDua7Gl9cXg"), false),
        Training(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane D", "U20", "Ian","Peter kom til tiden denne gang! Tak!!"           , 12, listOf("4Tjz5r8ckZDua7Gl9cXg"), false)
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

class TrainingModel() {
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadTrainingsFromDB(): MutableList<Training> {
        val db = Firebase.firestore
        db.collection("trainings")
            .get()
            .addOnSuccessListener { result ->
                trainings.clear()
                for (doc in result) {
                    //val test : Number = doc["price"] as Number
                    //val training = doc.toObject(Training::class.java) //Virker hvis der ikke bruges Int
                    //trainings.add(training)

                    trainings.add(
                        Training(
                            //Telmeldte personer
                            timeStart = doc["timeStart"] as com.google.firebase.Timestamp,
                            timeEnd = doc["timeEnd"] as com.google.firebase.Timestamp,
                            location = doc["location"] as String,
                            league = doc["league"] as String,
                            trainer = doc["trainer"] as String,
                            description = doc["description"] as String,
                            maxParticipants = (doc["maxParticipants"] as Number).toInt(),
                            participants = (doc["participants"]) as List<String>,
                            userBooking = doc["userBooking"] as Boolean
                        )
                    )
                }
            }
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _loading.value = false
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: trainings", exception)
            }
        return trainings
    }
}

fun getSignedUpTrainings(): MutableList<Training> {
    val signedUpTrainings: MutableList<Training> = mutableListOf()
 //TODO Få currentUser i stedet for "uqYviRk77BegdJdx9BW5"
    for (training in trainings){
        if (training.participants.contains("uqYviRk77BegdJdx9BW5")){
            signedUpTrainings.add(training)
        }
    }
    return signedUpTrainings
}

fun getBookings(): MutableList<Training> {
    val bookings: MutableList<Training> = mutableListOf()
    for (training in trainings){
        if (training.participants.contains(CurrentUser.id) && training.userBooking){
            bookings.add(training)
        }
    }
    return bookings
}

fun updateParticipants(training: Training, userId: String){

    val db = Firebase.firestore
    db.collection("trainings")
        .get()
        .addOnSuccessListener { result ->
            for (doc in result) {// timeStart her var date før //TODO Få til at virke igen hvis den ikke allerede gør
                if (doc["timeStart"] == training.timeStart && doc["location"] == training.location && doc["timeStart"] == training.timeStart){

                    //Create a mutable list, so we can add items to it.
                    var mutableParticipants = training.participants.toMutableList()
                    if (mutableParticipants.contains(userId)){
                        mutableParticipants.remove(userId)
                    }else{
                        mutableParticipants.add(userId)
                    }

                    //map of field to update
                    var updatedTraining = hashMapOf(
                        "participants" to mutableParticipants
                    )

                    //Update field
                    val updateable = db.collection("trainings").document(doc.id)
                    updateable
                        .set(updatedTraining, SetOptions.merge())
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                    //Finally load the updated trainings from DB again.
                    val trainings = TrainingModel()
                    trainings.loadTrainingsFromDB()

                }
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
        }
}


@Parcelize
data class Training(
    val timeStart: com.google.firebase.Timestamp,
    val timeEnd: com.google.firebase.Timestamp,
    val location: String= "",
    val league: String= "",
    val trainer: String= "",
    val description: String= "",
    val maxParticipants: Int = 0,
    val participants: List<String> = listOf(),
    val userBooking: Boolean = false
): Parcelable


