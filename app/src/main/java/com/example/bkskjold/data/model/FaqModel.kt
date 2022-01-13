package com.example.bkskjold.data.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Faq(
    val question: String,
    val answer: String
)

val faqs = mutableListOf<Faq>()

fun updateFAQ() {
    Firebase.firestore.collection("faqs").get().addOnSuccessListener { result ->
        faqs.clear()
        for (faq in result) {
           faqs.add(Faq(
               question = faq["question"] as String,
               answer = faq["answer"] as String
           ))
        }
    }
}