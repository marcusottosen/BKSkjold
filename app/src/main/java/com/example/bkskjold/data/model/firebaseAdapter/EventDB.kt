package com.example.bkskjold.data.model.firebaseAdapter
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.Event
import com.example.bkskjold.data.util.getMonthFromString
import com.google.firebase.Timestamp
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
            .get()
            .addOnSuccessListener { result ->
                events.clear()
                for (doc in result) {
                    events.add(
                        Event(
                            timeStart = doc["timeStart"] as Timestamp,
                            timeEnd = doc["timeEnd"] as Timestamp,
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

fun newEvent(
    header: String,
    description: String,
    location: String,
    month: String,
    day: Int,
    startHour: Int,
    startMin: Int,
    endHour: Int,
    endMin: Int,
    price: Int,
    navController: NavController
){
    val dateformat = "yyyy-MM-dd-k-m"
    val startTimestamp = com.google.firebase.Timestamp(
        SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$startHour-$startMin").toString()))
    val endTimestamp = com.google.firebase.Timestamp(
        SimpleDateFormat(dateformat).parse(("2022-${getMonthFromString(month)}-$day-$endHour-$endMin").toString()))

    val event = Event(
        timeStart = startTimestamp,
        timeEnd = endTimestamp,
        location = location,
        price = price,
        header = header,
        description = description
    )

    val db = Firebase.firestore
    db.collection("events")
        .add(event)
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


/*
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
*/