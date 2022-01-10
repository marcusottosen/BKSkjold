package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.*
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
    fun GetOverviewView(navController: NavController, date: String, timeStart: String, team: String){
        val trainings = loadTrainingsFromDB()

        if(date == "" && timeStart == "" && team == ""){
            LazyColumn {
                items(trainings.size) { i ->
                    TrainingCard(training = trainings[i], navController)
                }
                item{ Spacer(modifier = Modifier.height(80.dp))}
            }
        }else{
            filterAndSort(navController = navController, date = date, timeStart = timeStart, practices = trainings, team = team)
        }
    }
    @Composable
    fun GetSignedUpView(navController: NavController, date: String, timeStart: String, team: String){
        val trainings = getSignedUpTrainings()


        if (date == "" && timeStart == "" && team == ""){
            LazyColumn {
                items(trainings.size) { i ->
                    TrainingCard(training = trainings[i], navController)
                }
                item{ Spacer(modifier = Modifier.height(80.dp))}
            }
        }else{
            filterAndSort(navController = navController, date = date, timeStart = timeStart, practices = trainings, team = team)
        }
    }

    @Composable
    fun filterAndSort(navController: NavController, date: String, timeStart: String, team: String, practices: List<Training>){
        val filteredPractices: MutableList<Training> = mutableListOf()

        try {
            for ( i in practices){
                if (date != "" && timeStart == "" && team == ""){
                    if (i.date == date){
                        filteredPractices.add(i)
                    }
                }else if (timeStart != "" && date == "" && team == ""){
                    if (i.timeStart == timeStart){
                        filteredPractices.add(i)
                    }
                }else if (team != "" && timeStart == "" && date == ""){
                    if (i.league == team){
                        filteredPractices.add(i)
                    }
                }else if (date != "" && timeStart != "" && team == ""){
                    if (i.date == date && i.timeStart == timeStart){
                        filteredPractices.add(i)
                    }
                }else if (date != "" && team != "" && timeStart == ""){
                    if (i.date == date && i.league == team){
                        filteredPractices.add(i)
                    }
                }else if (timeStart != "" && team != "" && date == ""){
                    if (i.timeStart == timeStart && i.league == team){
                        filteredPractices.add(i)
                    }
                }else if (date != "" && timeStart != "" && team != ""){
                    if (i.date == date && i.timeStart == timeStart && i.league == team){
                        filteredPractices.add(i)
                    }
                }
            }
            print("")
        } catch (e: Exception){
            print("Something went wrong, when trying to filter through practices. Method: filterAndSort in TrainingOverviewViewModel")
        }

        //Finally create the view with the right practices.
        LazyColumn {
            items(filteredPractices.size) { i ->
                TrainingCard(training = filteredPractices[i], navController = navController)
            }
            item{ Spacer(modifier = Modifier.height(80.dp))}
        }
    }
}