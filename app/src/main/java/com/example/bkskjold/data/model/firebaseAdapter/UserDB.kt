package com.example.bkskjold.data.model.firebaseAdapter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.data.model.dataClass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


fun updateCurrentUser() {
    Firebase.firestore.collection("users-db").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
        .addOnSuccessListener { result ->
            CurrentUser.id = FirebaseAuth.getInstance().currentUser?.uid.toString()
            CurrentUser.firstName = result["firstName"] as String
            CurrentUser.lastName = result["lastName"] as String
            CurrentUser.email = FirebaseAuth.getInstance().currentUser?.email.toString()
            CurrentUser.address = result["address"] as String
            CurrentUser.phoneNumber = (result["phoneNumber"] as Number).toInt()
            CurrentUser.birthdate = result["birthdate"] as com.google.firebase.Timestamp
            CurrentUser.team = result["team"] as String
            CurrentUser.userType = (result["userType"] as Number).toInt()
            CurrentUser.finishedTrainings = (result["finishedTrainings"] as Number).toInt()
            CurrentUser.memberSince = result["memberSince"] as com.google.firebase.Timestamp
        }
}

//OBS Er dette ikke præcist det samme som bare at skrive CurrentUser.variable ? TODO Læs <--
fun getCurrentUserAsCurrentUserModel(): User {
    return User(
        id = CurrentUser.id,
        firstName = CurrentUser.firstName,
        lastName = CurrentUser.lastName,
        email = CurrentUser.email,
        address = CurrentUser.address,
        phoneNumber = CurrentUser.phoneNumber,
        birthdate = CurrentUser.birthdate,
        team = CurrentUser.team,
        userType = CurrentUser.userType,
        finishedTrainings = CurrentUser.finishedTrainings,
        memberSince = CurrentUser.memberSince
    )
}


//############# MAKES LIST OF ALL USERS FROM users-db ###################
val users: MutableList<User> = mutableListOf()
class UserDB() {
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadUsersFromDB(): MutableList<User> {
        val db = Firebase.firestore
        db.collection("users-db")
            .get()
            .addOnSuccessListener { result ->
                users.clear()
                for (doc in result) {

                    users.add(
                        User(
                            id = doc["id"] as String,
                            firstName = doc["firstName"] as String,
                            lastName = doc["lastName"] as String,
                            email = doc["email"] as String,
                            address = doc["address"] as String,
                            phoneNumber = (doc["phoneNumber"] as Number).toInt(),
                            birthdate = doc["birthdate"] as com.google.firebase.Timestamp,
                            team = doc["team"] as String,
                            userType = (doc["userType"] as Number).toInt(),
                            finishedTrainings = (doc["finishedTrainings"] as Number).toInt(),
                            memberSince = doc["memberSince"] as com.google.firebase.Timestamp,
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
                Log.d(ContentValues.TAG, "Error getting documents: users", exception)
            }
        return users
    }
}


fun getUserFromID(idString: String): User{
    try {
        for (user in users) {
            if (idString == user.id) {
                return user
            }
        }
    } catch (e: Exception) {
        Log.d(ContentValues.TAG, "User not found from ID in getUserFromID", e)
    }
    return users[0]
}



//############# GET USERS FROM LIST OF USER IDS ###################
var participants: MutableList<User> = mutableListOf()

fun getUsersFromId(ids: List<String>): MutableList<User> {
    val db = Firebase.firestore

    db.collection("users-db")
        .get()
        .addOnSuccessListener { result ->
            participants.clear() //Always clear participants before getting new participants for new pratice
            for (id in ids) {
                for (doc in result){

                    if (doc.id == id){
                        var participant = User(
                            id = doc["id"] as String,
                            firstName = doc["firstName"] as String,
                            lastName = doc["lastName"] as String,
                            email = doc["email"] as String,
                            address = doc["address"] as String,
                            phoneNumber = (doc["phoneNumber"] as Number).toInt(),
                            birthdate = doc["birthdate"] as com.google.firebase.Timestamp,
                            team = doc["team"] as String,
                            userType = (doc["userType"] as Number).toInt(),
                            finishedTrainings = (doc["finishedTrainings"] as Number).toInt(),
                            memberSince = doc["memberSince"] as com.google.firebase.Timestamp
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



/*
TODO Skal nok fjernes inden aflevering
//VIRKER PT IKKE
// *** Uncomment hvis dataen skal tilføjes. (Uncomment også i LoadFromDB.kt ***
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
}*/