package com.example.bkskjold.data.model

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.bkskjold.MainActivity
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.firebaseAdapter.*
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class TrainingDBTest : TestCase() {

    //@get:Rule
    //val composeTestRule = createComposeRule()

    @Test
    fun testGetTrainings() {

    }

    @Test
    fun testGetSignedUpTrainings() {
        val trainings = getSignedUpTrainings()

        assertTrue(trainings is MutableList<Training>)
    }

    @Test
    fun testGetBookings() {
        val bookings = getBookings()

        assertTrue(bookings is MutableList<Training>)
    }
}