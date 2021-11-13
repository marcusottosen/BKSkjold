package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.bkskjold.R
import com.example.bkskjold.data.model.BookingData
import com.example.bkskjold.ui.view.reusables.TrainingCard

class TrainingOverviewViewModel {
/*
    var time = "17:47"
    var daysTillTraining = "I dag"
    var date = "25 Oktober 2021"
    var participants = "8/12"
    var location = "Bane C"
    var league = "U12"
    var trainer = "Træner: Ekkart"
    var button = "Deltag"
    //var color = R.color.notParticipating*/


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
    fun getOverviewView(){
        val allTrainings = BookingData().getAllTraining()

        LazyColumn {
            items(allTrainings.size) { i ->
                TrainingCard( //create the cards
                    time = allTrainings[i][0],
                    daysTillTraining = allTrainings[i][1],
                    date = allTrainings[i][2],
                    participants = allTrainings[i][3],
                    location = allTrainings[i][4],
                    league = allTrainings[i][5],
                    trainer = allTrainings[i][6],
                    button = allTrainings[i][7],
                    color = colorResource(id = getColor(allTrainings, i))
                )
            }
        }
    }
    @Composable
    fun getSignedUpView(){
        val signedUpTrainings = BookingData().getSignedUpTrainings()

        LazyColumn {
            if (signedUpTrainings != null) {
                items(signedUpTrainings.size) { i ->
                    //TrainingCard(time, daysTillTraining, date, participants, location, league, trainer, button, colorResource(color))
                    TrainingCard( //create the cards
                        time = signedUpTrainings[i][0],
                        daysTillTraining = signedUpTrainings[i][1],
                        date = signedUpTrainings[i][2],
                        participants = signedUpTrainings[i][3],
                        location = signedUpTrainings[i][4],
                        league = signedUpTrainings[i][5],
                        trainer = signedUpTrainings[i][6],
                        button = signedUpTrainings[i][7],
                        color = colorResource(id = getColor(signedUpTrainings, i))
                    )
                }
            }/* TODO handle null exception */
        }
    }
}