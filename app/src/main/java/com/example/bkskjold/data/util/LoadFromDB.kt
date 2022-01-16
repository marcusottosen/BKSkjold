package com.example.bkskjold.data.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationItem
import com.example.bkskjold.data.model.firebaseAdapter.NewsModel
import com.example.bkskjold.data.model.firebaseAdapter.EventDB
import com.example.bkskjold.data.model.firebaseAdapter.TrainingModel
import com.example.bkskjold.data.model.firebaseAdapter.UserDB

@Composable
fun LoadFromDB(navController: NavController){
    val trainings = TrainingModel()
    val trainingsLoading: Boolean by trainings.loading.observeAsState(initial = true)
    trainings.loadTrainingsFromDB()

    val users = UserDB()
    val usersLoading: Boolean by users.loading.observeAsState(initial = true)
    users.loadUsersFromDB()

    val events = EventDB()
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