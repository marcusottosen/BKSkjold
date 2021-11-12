package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.bkskjold.ui.view.reusables.EventsCard

open class EventOverviewViewModel {

    @Composable
    fun createEventsList() { /* TODO eventually these hardcoded test data, should be fetched from the model in /data. */
        val numberOfItems = 15
        val testHeader = "Hygge i klubhuset"
        val testDescription = "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."
        val testTime = "29/09 kl. 13:00"
        val testLocation = "Klubhuset"

        LazyColumn(){
            items(numberOfItems) { index ->
                EventsCard(header = testHeader, description = testDescription, time = testTime, location = testLocation)
            }

        }
    }
}