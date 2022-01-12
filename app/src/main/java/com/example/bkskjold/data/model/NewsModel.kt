package com.example.bkskjold.data.model

import android.content.ContentValues
import android.os.Parcelable
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.bkskjold.ui.view.pages.HomeScreenPage
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize

fun newsWriteToDB(){
    val createNews = listOf(
        News("Ny Bane!", "Se den nye bane ved sine af bane C. Den nye bane kommer til at hedde bane Q", com.google.firebase.Timestamp.now()),
        News("U14 vinder mesterskab", "U14 holdet vindet guld efter kamp mod HB Køge", com.google.firebase.Timestamp.now()),
        News("U14 vinder mesterskab", "U14 holdet vindet guld efter kamp mod HB Køge", com.google.firebase.Timestamp.now()),
        News("Ny Bane!", "Se den nye bane ved sine af bane C. Den nye bane kommer til at hedde bane Q", com.google.firebase.Timestamp.now()),
        News("Something something", "Something mod something vinder something efter something!", com.google.firebase.Timestamp.now())
    )
    val db = Firebase.firestore
    for (i in 1..createNews.size) {
        db.collection("news")
            .add(createNews[i-1])
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


@Parcelize
data class News(
    val header: String,
    val description: String,
    val date: com.google.firebase.Timestamp,
): Parcelable