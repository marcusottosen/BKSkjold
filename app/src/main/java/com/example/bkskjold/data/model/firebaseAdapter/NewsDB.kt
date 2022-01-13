package com.example.bkskjold.data.model

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.News
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val news: MutableList<News> = mutableListOf()

class NewsModel() {
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadNewsFromDB(): MutableList<News> {
        val db = Firebase.firestore
        db.collection("news")
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                news.clear()
                for (doc in result) {
                    news.add(
                        News(
                            header = doc["header"] as String,
                            description = doc["description"] as String,
                            date = doc["date"] as com.google.firebase.Timestamp
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
                Log.d(ContentValues.TAG, "Error getting documents: news", exception)
            }
        return news
    }
}

fun newNews(
    header: String,
    description: String,
    navController: NavController
){
    val news = News(header, description, Timestamp.now())

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