package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bkskjold.ui.view.reusables.EventsCard

open class EventOverviewViewModel {

    @Preview
    @Composable
    fun getEventsList() { /* TODO eventually this hardcoded test data, should be fetched from the model in /data. */
        var numberOfItems = 15
        var testHeader = "Hygge i klubhuset"
        var testDescription = "Kom og hyg i klubuset!! Der vil være pølsehorn og sodavand til de små, samt øl og vin de forældrene."
        var testTime = "29/09 kl. 13:00"
        var testLocation = "Klubhuset"


        LazyColumn(){
            items(numberOfItems) { index ->
                EventsCard(header = testHeader, description = testDescription, time = testTime, location = testLocation)
            }
        }
    }
}