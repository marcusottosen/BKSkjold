package com.example.bkskjold.ui.viewmodel;


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import com.example.bkskjold.data.model.ProfileInfo;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.tooling.preview.Preview;
import com.example.bkskjold.data.model.EventData
import com.example.bkskjold.ui.view.reusables.EventsCard
import com.example.bkskjold.ui.view.reusables.InvitationCard

class ProfileOverviewViewModel {
    var profile = ProfileInfo().testProfile
    @Preview
    @Composable

    fun getProfileView() {
        Text(text = profile[0])
        Text(text = profile[1])
        Text(text = profile[2])
        Text(text = profile[3])
        }

    @Preview
    @Composable
    fun getPersonView() {
        Text(text = profile[4])
        Text(text = profile[5])
        Text(text = profile[6])

    }
    @Preview
    @Composable
    fun getProfileInvitationView() {
        var events = EventData().getEvents()
        var numberOfItems = events.size

        LazyColumn(){
            items(numberOfItems) { i ->
                InvitationCard(name = events[i][0], date = events[i][1], location = events[i][2])

            }
        }
    }
}

