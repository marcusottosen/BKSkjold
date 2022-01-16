package com.example.bkskjold.data.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bkskjold.MainScreen
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationItem
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.data.model.dataClass.Event
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.dataClass.User
import com.example.bkskjold.data.model.updateFAQ
import com.example.bkskjold.ui.view.pages.*
import com.example.bkskjold.ui.view.pages.booking.BookFieldPage
import com.example.bkskjold.ui.view.pages.booking.NewNewsPage
import com.example.bkskjold.ui.view.pages.event.CreateEventPage
import com.example.bkskjold.ui.view.pages.event.eventOverview
import com.example.bkskjold.ui.view.pages.profile.profileOverview
import com.example.bkskjold.ui.view.pages.training.NewTrainingPage
import com.example.bkskjold.ui.view.pages.training.TrainingInfoPage
import com.example.bkskjold.ui.viewmodel.Action
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination =
    if (Firebase.auth.currentUser != null)
        NavigationRoute.LoadFromDB.route
    else
        "authenticationOption"

    ) {
        // Navbar
        composable(NavigationItem.Home.route) {
            HomeScreenPage(navController)
        }
        composable(NavigationItem.Trainings.route) {
            trainingOverview(navController)
        }
        composable(NavigationItem.Events.route) {
            eventOverview(navController)
        }
        composable(NavigationItem.Profile.route) {
            profileOverview(navController)
            updateFAQ()
        }

        // Login pages
        composable("authenticationOption") {
            AuthenticationView(
                register = remember(navController) { Action(navController) }.register,
                login = remember(navController) { Action(navController) }.login
            )
        }
        composable("register") {
            RegisterView(
                home = remember(navController) { Action(navController) }.home,
                back = remember(navController) { Action(navController) }.navigateBack
            )
        }
        composable("login") {
            LoginView(
                home = remember(navController) { Action(navController) }.home,
                back = remember(navController) { Action(navController) }.navigateBack
            )
        }
        composable("mainScreen") {
            MainScreen()
        }

        //Load before homepage is shown
        composable(NavigationRoute.LoadFromDB.route) {
            LoadFromDB(navController)
        }

        //Subpages
        composable(NavigationRoute.TrainingDetails.route){
            val trainingModel = navController.previousBackStackEntry?.arguments?.getParcelable<Training>("training")
            trainingModel?.let { 
                TrainingInfoPage(training = it, navController = navController)
            }
        }
        composable(NavigationRoute.EventDetails.route){
            val eventModel = navController.previousBackStackEntry?.arguments?.getParcelable<Event>("event")
            eventModel?.let {
                EventInfoPage(event = it, navController = navController)
            }
        }
        composable(NavigationRoute.SettingsPage.route){
            val settingModel = navController.previousBackStackEntry?.arguments?.getParcelable<User>("user")
            settingModel?.let {
                settingsPage(navController = navController)
            }
        }
        composable(NavigationRoute.EditProfile.route){
            val settingModel = navController.previousBackStackEntry?.arguments?.getParcelable<User>("user")
            settingModel?.let {
               editProfilePage(navController = navController)
            }
        }
        composable(NavigationRoute.BookedFieldsPage.route) {
            BookedFieldsPage(navController)
        }
        composable(NavigationRoute.BookFieldPage.route) {
            BookFieldPage(navController)
        }
        composable(NavigationRoute.FaqPage.route) {
            FaqPage(navController)
        }
        composable(NavigationRoute.AdminPanel.route) {
            AdminPanel(navController)
        }
        composable(NavigationRoute.NewTrainingPage.route) {
            NewTrainingPage(navController)
        }
        composable(NavigationRoute.NewNewsPage.route) {
            NewNewsPage(navController)
        }
        composable(NavigationRoute.CreateEventPage.route) {
            CreateEventPage(navController)
        }
    }
}

/**
 * GUIDE TIL SIMPEL SUB-NAVIGATION
 * 1. lav composable som fx "BookedFieldsPage" ovenfor
 * 2. Ved en knap/clickable skriv: .clickable {navController.navigate("BookedFieldsPage")}
 *      (Hvis Button brug onClick = { navController.navigate("BookedFieldsPage")}
 *
 * Brug onClick = { navController.navigateUp() ved en tilbageknap
 */


/**
 * NavBar inspiration from https://github.com/johncodeos-blog/BottomNavigationBarComposeExample
 */
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Trainings,
        NavigationItem.Events,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.main_background),
        elevation = 12.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(25.dp)) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )
        }
    }
}