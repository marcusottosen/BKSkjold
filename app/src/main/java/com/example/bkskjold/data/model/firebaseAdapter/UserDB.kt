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
    Firebase.firestore.collection("users-db")
        .document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
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

//############# Get all users from database ###################
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

fun getUserFromID(idString: String): User {
    try {
        for (user in users) {
            if (idString == user.id) {
                return user
            }
        }
    } catch (e: Exception) {
        Log.d(ContentValues.TAG, "User not found from ID in getUserFromID", e)
    }
    //To avoid errors, return (almost)empty User.
    return User("", "", "", "", "", 0,
        com.google.firebase.Timestamp.now(), "", 1, 0, com.google.firebase.Timestamp.now())
}


//############# Get users from list of user-IDs ###################
var participants: MutableList<User> = mutableListOf()
fun getUsersFromId(ids: List<String>): MutableList<User> {
    val db = Firebase.firestore

    db.collection("users-db")
        .get()
        .addOnSuccessListener { result ->
            participants.clear() //Always clear participants before getting new participants for new practice
            for (id in ids) {
                for (doc in result) {

                    if (doc.id == id) {
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
                        if (participant !in participants) {
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