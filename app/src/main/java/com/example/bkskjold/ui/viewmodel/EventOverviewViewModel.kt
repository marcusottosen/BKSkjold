package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.data.model.events
import com.example.bkskjold.ui.view.reusables.EventsCard

open class EventOverviewViewModel {
    @Composable
    fun getEventsView(navController: NavController) {

        LazyColumn(){
            item {Spacer(modifier = Modifier.height(40.dp))}
            items(events.size) { i ->
                EventsCard(event = events[i], navController)
            }
            item {Spacer(modifier = Modifier.height(80.dp))}
        }
    }
}