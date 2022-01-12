package com.example.bkskjold.data.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.*
import com.example.bkskjold.ui.view.pages.HomeScreenPage

@Composable
fun LoadFromDB(navController: NavController){
    //userWriteToDB()
    //trainingsWriteToDB()
    //eventWriteToDB()
    //newsWriteToDB()

    val trainings = TrainingModel()
    val trainingsLoading: Boolean by trainings.loading.observeAsState(initial = true)
    trainings.loadTrainingsFromDB()

    val users = UserModel()
    val usersLoading: Boolean by users.loading.observeAsState(initial = true)
    users.loadUsersFromDB()

    val events = EventModel() //bruges pt ikke på homepage
    val eventsLoading: Boolean by events.loading.observeAsState(initial = true)
    events.loadEventsFromDB()

    val news = NewsModel()
    val newsLoading: Boolean by news.loading.observeAsState(initial = true)
    news.loadNewsFromDB()

    if (newsLoading && trainingsLoading && usersLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(color = colorResource(R.color.primary))
        }
    } else {
        navController.navigate(NavigationItem.Home.route)
    }
}