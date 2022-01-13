package com.example.bkskjold.ui.viewmodel

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
import com.example.bkskjold.data.model.dataClass.Event
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.dataClass.User
import com.example.bkskjold.data.util.LoadFromDB
import com.example.bkskjold.ui.view.pages.*
import com.example.bkskjold.ui.view.pages.event.eventOverview
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * NavBar inspiration from https://github.com/johncodeos-blog/BottomNavigationBarComposeExample
 */

@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination =
    if (Firebase.auth.currentUser != null)
        "loadFromDB"
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
        composable("loadFromDB") {
            LoadFromDB(navController)
        }


        //Subpages
        composable("trainingDetails"){
            val trainingModel = navController.previousBackStackEntry?.arguments?.getParcelable<Training>("training")
            trainingModel?.let { 
                TrainingInfoPage(training = it, navController = navController)
            }
        }
        composable("eventDetails"){
            val eventModel = navController.previousBackStackEntry?.arguments?.getParcelable<Event>("event")
            eventModel?.let {
                EventInfoPage(event = it, navController = navController)
            }
        }
        composable("settingsPage"){
            val settingModel = navController.previousBackStackEntry?.arguments?.getParcelable<User>("user")
            settingModel?.let {
                settingsPage(navController = navController)
            }
        }

        composable("editProfile"){
            val settingModel = navController.previousBackStackEntry?.arguments?.getParcelable<User>("user")
            settingModel?.let {
               editProfilePage(navController = navController)
            }
        }
        composable("bookedFieldsPage") {
            BookedFieldsPage(navController)
        }
        composable("bookFieldPage") {
            BookFieldPage(navController)
        }
        composable("faqPage") {
            FaqPage(navController)
        }
        composable("adminPanel") {
            AdminPanel(navController)
        }
        composable("newTrainingPage") {
            NewTrainingPage(navController)
        }
        composable("newNewsPage") {
            NewNewsPage(navController)
        }
        composable("newEventPage") {
            //NewEventPage(navController)
        }
    }
} //Læs guide nedenfor til navigation!

/**
 * GUIDE TIL SIMPEL SUB-NAVIGATION
 * 1. lav composable som fx "BookedFieldsPage" ovenfor
 * 2. Ved en knap/clickable skriv: .clickable {navController.navigate("BookedFieldsPage")}
 *      (Hvis Button brug onClick = { navController.navigate("BookedFieldsPage")}
 *
 * Brug onClick = { navController.navigateUp() ved en tilbageknap
 *
 *
 * GUIDE TIL SUB-NAVIGATION MED OBJEKT
 * (ved objekt i parameter)
 * 1.
 *   Ovenfor copypaste den nederste composable og ret den til. Der er 6 steder der skal ændres til den ønskede side.
 *      "route" er stien den leder efter, så dette skal skrives ved punkt 2.
 *      den val der oprettes skal rettes til hvor .getParcelable<###> indeholder det objekt der parses. key bruges også ved punkt 2.
 *      Den nederste metode er sidens function. Skal indeholde navController hvis der skal navigeres fra den (DVS. ALTID!)
 *
 *  2.
 *      På siden der navigeres FRA (fx EventOverviewPage) copy/pastes nedenstående
                fun gotoEventDetails(event: Event, navController: NavController){
                navController.currentBackStackEntry?.arguments?.putParcelable("event", event)
                navController.navigate("eventDetails")
                }
 *      Hvor første parameter er variablen den skal overføre. Husk at ændre navnet på funktionen
 *      Linje 2 bør være sigende hvor "event" svarer til key fra punkt 1.
 *      Linje 3 skal "route" indsættes fra den oprettede composable i Navigation.
 *
 *  3.
 *      På knappen på siden der navigeres FRA (fx et card/button) skal der i modifier stå .clickable {navn på funktion fra punkt 2(variable/object, navController)}
 *      fx ved Card: modifier = Modifier.clickable { gotoEventDetails(event, navController)}
 *      Hvis en knap: onClick = {gotoEventDetails(event, navController)}
 *
 * 4.
 *      For at returnere fra den nye side skrives onClick = { navController.navigateUp() ved en knap
 *
 *
 * ps. Lidt anderledes ved overførsel af andre types
 *
 *
 * Spørgsmål spørg Marcus
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
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}