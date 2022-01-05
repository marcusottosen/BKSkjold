package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
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


    /* TODO These functions should eventually be altered
        The idea is that instead of hardcoding the data,
        it should be fetched from the model
    */
    @Composable
    fun GetOverviewView(navController: NavController){
        val trainings = BookingData().bookings
        LazyColumn {
            items(trainings.size) { i ->
                TrainingCard(training = trainings[i], navController)
            }
        }
/*
        val allTrainings = BookingData().getAllTraining()
        LazyColumn {
            items(allTrainings.size) { i ->
                TrainingCard( //create the cards
                    id          = allTrainings[i][0],
                    timeStart   = allTrainings[i][1],
                    timeEnd     = allTrainings[i][2],
                    weekday     = allTrainings[i][3],
                    date        = allTrainings[i][4],
                    participants= allTrainings[i][5],
                    location    = allTrainings[i][6],
                    league      = allTrainings[i][7],
                    trainer     = allTrainings[i][8],
                    attending      = allTrainings[i][9],
                    color       = colorResource(id = getColor(allTrainings, i))
                )
            }
        }*/
    }
    @Composable
    fun GetSignedUpView(navController: NavController){
        val trainings = BookingData().getSignedUpTrainings()
        LazyColumn {
            items(trainings.size) { i ->
                TrainingCard(training = trainings[i], navController)
            }
        }

/*        val signedUpTrainings = BookingData().getSignedUpTrainings()
        LazyColumn {
            if (signedUpTrainings != null) {
                items(signedUpTrainings.size) { i ->
                    //TrainingCard(time, daysTillTraining, date, participants, location, league, trainer, button, colorResource(color))
                    TrainingCard( //create the cards
                        id          = signedUpTrainings[i][0],
                        timeStart   = signedUpTrainings[i][1],
                        timeEnd     = signedUpTrainings[i][2],
                        weekday     = signedUpTrainings[i][3],
                        date        = signedUpTrainings[i][4],
                        participants= signedUpTrainings[i][5],
                        location    = signedUpTrainings[i][6],
                        league      = signedUpTrainings[i][7],
                        trainer     = signedUpTrainings[i][8],
                        attending      = signedUpTrainings[i][9],
                        color = colorResource(id = getColor(signedUpTrainings, i))
                    )
                }
            }else{
                print("$signedUpTrainings was null")
            /* TODO handle null exception */
            }
        }*/
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