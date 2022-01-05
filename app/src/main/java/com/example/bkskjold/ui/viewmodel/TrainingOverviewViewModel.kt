package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.getSignedUpTrainings
import com.example.bkskjold.data.model.trainings
import com.example.bkskjold.data.model.BookingData
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.ui.view.reusables.TrainingCard

class TrainingOverviewViewModel {
    @Composable
    fun GetOverviewView(navController: NavController){
        LazyColumn {
            items(trainings.size) { i ->
                TrainingCard(training = trainings[i], navController)
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
    @Composable
    fun GetSignedUpView(navController: NavController){
        val trainings = getSignedUpTrainings()
        LazyColumn {
            items(trainings.size) { i ->
                TrainingCard(training = trainings[i], navController)
            }
            item {Spacer(modifier = Modifier.height(80.dp))}
        }
    }

    @Composable
    fun filterAndSort(navController: NavController, date: Int? = null, timeStart: Int? = null, overviewOrSignedUp: Boolean = false){


        //First decide which list of practices to filter through
        var practices = if (overviewOrSignedUp == true){
            BookingData().getSignedUpTrainings()
        } else{
            BookingData().bookings
        }

        var filteredPractices: MutableList<Training> = mutableListOf()
        var date = date.toString()
        var timestamp = timeStart.toString()


        try {
            for (i in practices){
                if(i.date == date!! && i.timeStart == timestamp!!){
                    filteredPractices.add(i)
                }
            }
        } catch (e: Exception){

        }

        //Finally create the view with the right practices.
        LazyColumn {
            items(practices.size) { i ->
                TrainingCard(training = practices[i], navController)
            }
        }

    }
}

