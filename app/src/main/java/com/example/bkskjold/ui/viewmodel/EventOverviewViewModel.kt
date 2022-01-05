package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.bkskjold.data.model.EventData
import com.example.bkskjold.ui.view.reusables.EventsCard

open class EventOverviewViewModel {
    @Composable
    fun getEventsView(navController: NavController) {
        val events = EventData().events

        LazyColumn(){
            items(events.size) { i ->
                EventsCard(event = events[i], navController)
            }
        }


    /*var events = EventData().getEvents()
        var numberOfItems = events.size

        LazyColumn(){
            items(numberOfItems) { i ->
                EventsCard(num = i, header = events[i][0], description = events[i][1], time = events[i][2], location = events[i][3])

            }
        }*/
    }
}