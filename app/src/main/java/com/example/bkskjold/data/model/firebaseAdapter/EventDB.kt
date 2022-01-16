package com.example.bkskjold.data.model.firebaseAdapter
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.Event
import com.example.bkskjold.data.model.dataClass.Training
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

val events: MutableList<Event> = mutableListOf()

class EventDB() {
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadEventsFromDB(): MutableList<Event> {
        val db = Firebase.firestore
        db.collection("events")
            .orderBy("timeStart", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                events.clear()
                for (doc in result) {
                    events.add(
                        Event(
                            timeStart = doc["timeStart"] as Timestamp,
                            timeEnd = doc["timeEnd"] as Timestamp,
                            location = doc["location"] as String,
                            participants = doc["participants"] as MutableList<String>,
                            price = (doc["price"] as Number).toInt(),
                            header = doc["header"] as String,
                            description = doc["description"] as String
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: events", exception)
            }
        _loading.value = false
        return events
    }
}

fun addEventToDB(item: Event, navController: NavController){
    val db = Firebase.firestore
    db.collection("events")
        .add(item)
        .addOnSuccessListener { documentReference ->
            Log.d(
                ContentValues.TAG,
                "DocumentSnapshot added with ID: ${documentReference.id}"
            )
        }
        .addOnCompleteListener {
            if (it.isSuccessful) {
                navController.navigate("adminPanel")
            }
        }
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error adding document", e)
        }
}


fun updateEventParticipants(event: Event, participants: MutableList<String>, userId: String): Event {
    val db = Firebase.firestore
    db.collection("events")
        .get()
        .addOnSuccessListener { result ->
            for (doc in result) {
                if (doc["timeStart"] == event.timeStart && doc["location"] == event.location && doc["description"] == event.description){

                    //Create a mutable list, so we can add items to it.
                    val mutableParticipants = participants

                    //This if statement is now done in the onclicks calling this method, instead of in this method.
                    /*if (mutableParticipants.contains(userId)){
                        mutableParticipants.remove(userId)
                    }else{
                        mutableParticipants.add(userId)
                    }*/

                    //map of field to update
                    val updatedEvent = hashMapOf(
                        "participants" to mutableParticipants
                    )

                    //Update field
                    val updateable = db.collection("events").document(doc.id)
                    updateable
                        .set(updatedEvent, SetOptions.merge())
                        .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully updated!") }
                        .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                    //Finally update participant on training object, so it can be returned later in the method
                    event.participants = mutableParticipants
                }
            }
        }
        .addOnCompleteListener{
            EventDB().loadEventsFromDB()
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
        }
    return event
}