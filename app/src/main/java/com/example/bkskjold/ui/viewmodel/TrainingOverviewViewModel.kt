package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.data.model.dataClass.Locations
import com.example.bkskjold.data.model.dataClass.Teams
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.firebaseAdapter.TrainingModel
import com.example.bkskjold.data.model.firebaseAdapter.getSignedUpTrainings
import com.example.bkskjold.data.util.getDayMonth
import com.example.bkskjold.data.util.getTime
import com.example.bkskjold.ui.view.reusables.TrainingCard

class TrainingOverviewViewModel {
    fun getTeams(): List<String> {
        val teams = mutableListOf<String>()
        for (team in Teams.values()){
            teams.add(team.toString())
        }
        return teams.toList()
    }

    @Composable
    fun GetOverviewView(navController: NavController, date: String, timeStart: String, team: String){
        val trainings = TrainingModel().loadTrainingsFromDB()
        val trainingsFinal: MutableList<Training> = mutableListOf()

        for (item in trainings){
            if (!item.userBooking){
                trainingsFinal.add(item)
            }
        }

        //Filter only if filter is applied
        if(date == "" && timeStart == "" && team == ""){
            LazyColumn() {
                items(trainingsFinal.size) { i ->
                    TrainingCard(training = trainingsFinal[i], navController)
                }
                item{ Spacer(modifier = Modifier.height(80.dp))}
            }
        }else{
            FilterAndSort(navController = navController, date = date, timeStart = timeStart, practices = trainingsFinal, team = team)
        }
    }

    @Composable
    fun GetSignedUpView(navController: NavController, date: String, timeStart: String, team: String){
        val trainings = getSignedUpTrainings()
        val trainingsFinal: MutableList<Training> = mutableListOf()

        for (item in trainings){
            if (!item.userBooking){
                trainingsFinal.add(item)
            }
        }
        //Filter only if filter is applied
        if (date == "" && timeStart == "" && team == ""){
            LazyColumn {
                items(trainingsFinal.size) { i ->
                    TrainingCard(training = trainingsFinal[i], navController)
                }
                item{ Spacer(modifier = Modifier.height(80.dp))}
            }
        }else{
            FilterAndSort(navController = navController, date = date, timeStart = timeStart, practices = trainingsFinal, team = team)
        }
    }

    @Composable
    fun FilterAndSort(navController: NavController, date: String, timeStart: String, team: String, practices: List<Training>){
        val filteredPractices: MutableList<Training> = mutableListOf()

        try {
            for ( i in practices){
                if (date != "" && timeStart == "" && team == ""){
                    if (getDayMonth(i.timeStart) == date){
                        filteredPractices.add(i)
                    }
                }else if (timeStart != "" && date == "" && team == ""){
                    //var THISTIMESTART = getTime(i.timeStart)
                    //print("")
                    if (getTime(i.timeStart).toString() == timeStart){
                        filteredPractices.add(i)
                    }
                }else if (team != "" && timeStart == "" && date == ""){
                    if (i.league == team){
                        filteredPractices.add(i)
                    }
                }else if (date != "" && timeStart != "" && team == ""){
                    if (getDayMonth(i.timeStart) == date && getTime(i.timeStart) == timeStart){
                        filteredPractices.add(i)
                    }
                }else if (date != "" && team != "" && timeStart == ""){
                    if (getDayMonth(i.timeStart) == date && i.league == team){
                        filteredPractices.add(i)
                    }
                }else if (timeStart != "" && team != "" && date == ""){
                    if (getTime(i.timeStart) == timeStart && i.league == team){
                        filteredPractices.add(i)
                    }
                }else if (date != "" && timeStart != "" && team != ""){
                    if (getDayMonth(i.timeStart) == date && getTime(i.timeStart) == timeStart && i.league == team){
                        filteredPractices.add(i)
                    }
                }
            }
        } catch (e: Exception){
            print("Something went wrong, when trying to filter through practices. Method: filterAndSort in TrainingOverviewViewModel: $e")
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


/*
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
 */