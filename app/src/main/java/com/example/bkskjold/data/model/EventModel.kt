package com.example.bkskjold.data.model
import android.content.ContentValues
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize

fun eventWriteToDB(){
    val createEvents = listOf(
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "hygge i klubhuset", "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "Klubmøde", "Vi skal snakke om klubben\", \"05/12 kl. 21:00\", \"Klubhuset"),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "hygge i klubhuset", "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "hygge i klubhuset", "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "Klubmøde", "Vi skal snakke om klubben\", \"05/12 kl. 21:00\", \"Klubhuset"),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane K", 20, "Juniorkamp", "Juniorerne skal spille kamp mod Ballerup. Kom og se med!"),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "hygge i klubhuset", "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane A", 20, "Kampdag!", "Kom og se os tæske Lyngby Boldklub"),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "hygge i klubhuset", "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane B", 20, "Kæmpe kamp!", "Juniorerne skal spille kamp mod Ballerup. Kom og se med!"),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Bane C", 20, "Dagens kamp", "Kom og se os tæske Lyngby Boldklub"),
        Event(com.google.firebase.Timestamp.now(), com.google.firebase.Timestamp.now(), "Klubhuset", 20, "hygge i klubhuset", "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene.")
    )
    val db = Firebase.firestore
    for (i in 1..createEvents.size) {
        db.collection("events")
            .add(createEvents[i-1])
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
}

val events: MutableList<Event> = mutableListOf()

class EventModel() {
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadEventsFromDB(): MutableList<Event> {
        val db = Firebase.firestore
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                events.clear()
                for (doc in result) {
                    events.add(
                        Event(
                            timeStart = doc["timeStart"] as com.google.firebase.Timestamp,
                            timeEnd = doc["timeEnd"] as com.google.firebase.Timestamp,
                            location = doc["location"] as String,
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


@Parcelize
data class Event(
    val timeStart: com.google.firebase.Timestamp,
    val timeEnd: com.google.firebase.Timestamp,
    val location: String,
    val price: Int,
    val header: String,
    val description: String
): Parcelable