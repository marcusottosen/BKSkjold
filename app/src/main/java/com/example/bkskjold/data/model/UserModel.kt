package com.example.bkskjold.data.model

import android.content.ContentValues
import android.os.Parcelable
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
import java.time.LocalDateTime.now

// *** Uncomment hvis dataen skal tilføjes. (Uncomment også i PreloadDB.kt ***
fun userWriteToDB() {
    val createUers = listOf(
        User("Ekkart", "Kindler", "ekkart@dtu.dk", "DTU Compute Secret HQ, Danmark", 11223344, com.google.firebase.Timestamp.now(),"Senior", 2, 26, com.google.firebase.Timestamp.now(),null),
        User("Ian", "Kindlerine", "Ian@dtu.dk", "Rådmandsgade 12, 2200 København N", 56156476, com.google.firebase.Timestamp.now(),"U12", 1, 52, com.google.firebase.Timestamp.now(),null),
        User("Thomas", "Berg", "ThomasB@dtu.dk", "Eddagården 6, 2200 København N", 74885216, com.google.firebase.Timestamp.now(),"U13", 1, 45, com.google.firebase.Timestamp.now(),null),
        User("Bjarne", "Sørensen", "BarjneS@dtu.dk", "Bragesgade 35, 2200 København N", 69568515, com.google.firebase.Timestamp.now(),"U14", 1, 12, com.google.firebase.Timestamp.now(),null),
        User("Tim", "Timeresn", "TimT@dtu.dk", "Titangade 2, 2200 København N", 12345655, com.google.firebase.Timestamp.now(),"U11", 1, 64, com.google.firebase.Timestamp.now(),null),
        User("Kasper", "Kaspersen", "KasperK@dtu.dk", "Nørrebrogade 66C, 2200 København N", 12564896, com.google.firebase.Timestamp.now(),"U9", 1, 56, com.google.firebase.Timestamp.now(),null),
        User("Søren", "Sørensen", "Sørensø@dtu.dk", "Nørre Allé 19E, 2200 København N", 48852645, com.google.firebase.Timestamp.now(),"U16", 1, 21, com.google.firebase.Timestamp.now(),null),
        User("Thomas", "Kastrup", "Thomaskas@dtu.dk", "Kastrup Lufthavn", 48213654, com.google.firebase.Timestamp.now(),"U95", 1, 5, com.google.firebase.Timestamp.now(),null)
    )

    val db = Firebase.firestore
    for (i in 1..createUers.size) {
        db.collection("users")
            .add(createUers[i-1])
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


val users: MutableList<User> = mutableListOf()

fun loadUsersFromDB(): MutableList<User>{
    val db = Firebase.firestore
    db.collection("users")
        .get()
        .addOnSuccessListener { result ->
            users.clear()
            for (doc in result) {
                //val test : Number = doc["price"] as Number
                //val training = doc.toObject(Training::class.java) //Virker hvis der ikke bruges Int
                //trainings.add(training)

                users.add(
                    User(
                        firstname = doc["firstname"] as String,
                        surname = doc["surname"] as String,
                        email = doc["email"] as String,
                        address = doc["address"] as String,
                        phoneNumber = (doc["phoneNumber"] as Number).toInt(),
                        birthdate = doc["birthdate"] as com.google.firebase.Timestamp,
                        team = doc["team"] as String,
                        userType = (doc["userType"] as Number).toInt(),
                        finishedTrainings = (doc["finishedTrainings"] as Number).toInt(),
                        memberSince = doc["memberSince"] as com.google.firebase.Timestamp,
                        loggedIn = doc["loggedIn"] as Boolean?
                    )
                )
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: users", exception)
        }
    return users
}

//############# GET USERS FROM LIST OF USER IDS ###################
var participants: MutableList<User> = mutableListOf()

fun getUsersFromId(ids: List<String>): MutableList<User> {
    val db = Firebase.firestore

    db.collection("users")
        .get()
        .addOnSuccessListener { result ->
            participants.clear() //Always clear participants before getting new participants for new pratice
            for (id in ids) {
                for (doc in result){

                    if (doc.id == id){
                        var participant = User(
                            firstname = doc["firstname"] as String,
                            surname = doc["surname"] as String,
                            email = doc["email"] as String,
                            address = doc["address"] as String,
                            phoneNumber = (doc["phoneNumber"] as Number).toInt(),
                            birthdate = doc["birthdate"] as com.google.firebase.Timestamp,
                            team = doc["team"] as String,
                            userType = (doc["userType"] as Number).toInt(),
                            finishedTrainings = (doc["finishedTrainings"] as Number).toInt(),
                            memberSince = doc["memberSince"] as com.google.firebase.Timestamp,
                            loggedIn = doc["loggedIn"] as Boolean?
                        )
                        if (participant !in participants){
                            participants.add(participant)
                        }
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting participants: ", exception)
        }

    return participants
}



@Parcelize
data class User(
    val firstname: String = "",
    val surname: String = "",
    val email: String = "",
    val address: String = "",
    val phoneNumber: Int = 1,
    val birthdate: com.google.firebase.Timestamp,
    val team: String = "",
    val userType: Int = 1,
    val finishedTrainings: Int = 0,
    val memberSince: com.google.firebase.Timestamp,
    val loggedIn: Boolean?
    ): Parcelable

