package com.example.bkskjold.ui.viewmodel


import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.BookingData
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.ui.view.reusables.TrainingCard
import java.lang.Exception

class TrainingOverviewViewModel {
    fun getColor(cards: List<List<String>>, i: Int): Int { //Used to fetch the correct color corresponding to participating status
        if (cards[i][7] == "Deltag") {
            return R.color.notParticipating
        } else {
            return R.color.participating
        }

    }


    @Composable
    fun GetOverviewView(navController: NavController, date: String, timeStart: String){
        val trainings = BookingData().bookings


        if(date == "" && timeStart == ""){
            LazyColumn {
                items(trainings.size) { i ->
                    TrainingCard(training = trainings[i], navController)
                }
            }
        }else{
            filterAndSort(navController = navController, date = date, timeStart = timeStart, practices = trainings)
        }


    }
    @Composable
    fun GetSignedUpView(navController: NavController, date: String, timeStart: String){
        val trainings = BookingData().getSignedUpTrainings()


        if (date == "" && timeStart == ""){
            LazyColumn {
                items(trainings.size) { i ->
                    TrainingCard(training = trainings[i], navController)
                }
            }
        }else{
            filterAndSort(navController = navController, date = date, timeStart = timeStart, practices = trainings)
        }



    }

    @Composable
    fun filterAndSort(navController: NavController, date: String, timeStart: String, practices: List<Training>){
        var filteredPractices: MutableList<Training> = mutableListOf()

        try {
            for ( i in practices){
                if (date != "" && timeStart != ""){
                    if (i.date == date && i.timeStart == timeStart){
                        filteredPractices.add(i)
                    }
                }else if(date != "" || timeStart != ""){
                    if (i.date == date || i.timeStart == timeStart){
                        filteredPractices.add(i)
                    }
                }
            }
        } catch (e: Exception){
            print("Something went wrong, when trying to filter through practices. Method: filterAndSort in TrainingOverviewViewModel")
        }

        //Finally create the view with the right practices.
        LazyColumn {
            items(filteredPractices.size) { i ->
                TrainingCard(training = filteredPractices[i], navController)
            }
        }
    }
}