package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.BookingData
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.ui.view.reusables.TrainingCard

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
        //First decide which list of practices to filter through
        /*var practices = if (overviewOrSignedUp == true){
            BookingData().getSignedUpTrainings()
        } else{
            BookingData().bookings
        }*/

        var filteredPractices: MutableList<Training> = mutableListOf()

        for ( i in practices){
            if (i.timeStart == timeStart){
                filteredPractices.add(i)
                print("")
            }
        }
        //Finally create the view with the right practices.
        LazyColumn {
            items(filteredPractices.size) { i ->
                TrainingCard(training = filteredPractices[i], navController)
            }
        }
    }
}