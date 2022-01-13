package com.example.bkskjold.data.model

import android.content.ContentValues
import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun newNews(
    header: String,
    description: String,
    navController: NavController
){
    val news = News(header, description, com.google.firebase.Timestamp.now())

    val db = Firebase.firestore
    db.collection("news")
        .add(news)
        .addOnSuccessListener { documentReference ->
            Log.d(
                ContentValues.TAG,
                "DocumentSnapshot added with ID: ${documentReference.id}"
            )
        }
        .addOnCompleteListener {
            if (it.isSuccessful) {
                val update = NewsModel()
                update.loadNewsFromDB()
                navController.navigate("adminPanel")
            }
        }
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error adding document", e)
        }
}