package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.bkskjold.ui.view.reusables.TrainingCard

class TrainingOverviewViewModel {
    /*
    time: String, daysTillTraining: String, date: String,
    participants: String, field: String, league: String,
    trainer: String, button: String
     */
    var time = "17:47"
    var daysTillTraining = "I dag"
    var date = "25 Oktober 2021"
    var participants = "8/12"
    var location = "Bane C"
    var league = "U12"
    var trainer = "Træner: Ekkart"
    var button = "Deltag"


    /* TODO These functions should eventually be altered
        The idea is that instead of hardcoding the data,
        it should be fetched from the model
    */
    @Composable
    fun getOverview(){
        LazyColumn {
            // Add 15 items
            items(15) { i ->
                TrainingCard(time, daysTillTraining, date, participants, location, league, trainer, button)
            }
        }
    }
    @Composable
    fun getSignedUp(){
        LazyColumn {
            // Add 15 items
            items(3) { i ->
                TrainingCard(time, daysTillTraining, date, participants, location, league, trainer, button)
            }
        }
    }
}