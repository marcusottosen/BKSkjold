package com.example.bkskjold.ui.view.pages.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.data.model.dataClass.Event
import com.example.bkskjold.ui.viewmodel.EventOverviewViewModel

@Composable
fun EventOverview(navController: NavController){
    Column (
        modifier = Modifier
            .background(color = colorResource(R.color.main_background)),
    )
    {
        EventOverviewViewModel().GetEventsView(navController)
    }
}

fun gotoEventDetails(event: Event, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("event", event)
    navController.navigate(NavigationRoute.EventDetails.route)
}