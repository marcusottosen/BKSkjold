package com.example.bkskjold.data.model.firebaseAdapter

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.data.model.dataClass.Training
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val trainings: MutableList<Training> = mutableListOf()

class TrainingModel {
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadTrainingsFromDB(): MutableList<Training> {
        val db = Firebase.firestore
        db.collection("trainings")
            .orderBy("timeStart", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                trainings.clear()
                for (doc in result) {
                    trainings.add(
                        Training(
                            timeStart = doc["timeStart"] as com.google.firebase.Timestamp,
                            timeEnd = doc["timeEnd"] as com.google.firebase.Timestamp,
                            location = doc["location"] as String,
                            league = doc["league"] as String,
                            trainer = doc["trainer"] as String,
                            description = doc["description"] as String,
                            maxParticipants = (doc["maxParticipants"] as Number).toInt(),
                            participants = (doc["participants"]) as MutableList<String>,
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
                Log.d(TAG, "Error getting documents: trainings", exception)
            }
        return trainings
    }
}

fun getSignedUpTrainings(): MutableList<Training> {
    val signedUpTrainings: MutableList<Training> = mutableListOf()

    for (training in trainings) {
        if (training.participants.contains(CurrentUser.id)) {
            signedUpTrainings.add(training)
        }
    }
    return signedUpTrainings
}

fun getBookings(): MutableList<Training> {
    val bookings: MutableList<Training> = mutableListOf()
    for (training in trainings) {
        if (training.participants.contains(CurrentUser.id) && training.userBooking) {
            bookings.add(training)
        }
    }
    return bookings
}

fun updateParticipants(
    training: Training,
    participants: MutableList<String>,
    userId: String,
): Training {
    val db = Firebase.firestore
    db.collection("trainings")
        .get()
        .addOnSuccessListener { result ->
            for (doc in result) {
                if (doc["timeStart"] == training.timeStart && doc["location"] == training.location && doc["timeStart"] == training.timeStart) {

                    //Create a mutable list, so we can add items to it.
                    val mutableParticipants = participants

                    //map of field to update
                    val updatedTraining = hashMapOf(
                        "participants" to mutableParticipants
                    )

                    //Update field
                    val updateable = db.collection("trainings").document(doc.id)
                    updateable
                        .set(updatedTraining, SetOptions.merge())
                        .addOnSuccessListener {
                            Log.d(TAG,
                                "DocumentSnapshot successfully updated!")
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                    //Finally update participant on training object, so it can be returned later in the method
                    training.participants = mutableParticipants
                }
            }
        }
        .addOnCompleteListener {
            TrainingModel().loadTrainingsFromDB()
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }
    return training
}

fun addTrainingToDB(item: Training, navController: NavController) {
    val db = Firebase.firestore
    db.collection("trainings")
        .add(item)
        .addOnSuccessListener { documentReference ->
            Log.d(
                TAG,
                "DocumentSnapshot added with ID: ${documentReference.id}"
            )
        }
        .addOnCompleteListener {
            if (it.isSuccessful) {
                if (item.userBooking)
                    navController.navigate("bookedFieldsPage")
                else
                    navController.navigate("adminPanel")
            }
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
}


