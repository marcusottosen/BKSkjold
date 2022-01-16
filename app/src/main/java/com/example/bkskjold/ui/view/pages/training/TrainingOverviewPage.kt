package com.example.bkskjold.ui.view.pages.training
import android.widget.CalendarView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.ui.viewmodel.TrainingOverviewViewModel


@Composable
fun trainingOverview(navController: NavController) {
    val shouldShowOverview = remember { mutableStateOf(true) }
    val showFilterOptions = remember { mutableStateOf(true) }

    //main filter menu variables
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Hold", "Tidspunkt")
    //var selectedIndex by remember { mutableStateOf(0) }

    //Tidspunkt filter menu variables
    var expandedTidspunkt by remember { mutableStateOf(false) }
    val times = listOf("15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00")

    //team filter menu variables
    var expandedTeam by remember { mutableStateOf(false) }
    val teams = listOf("U18", "U19", "U20", "U21", "Senior")


    //passed filter values
    val tidspunkt = remember { mutableStateOf("") }
    val team = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }

    val viewModel = TrainingOverviewViewModel()

    Column (
        //verticalArrangement = Arrangement.spacedBy(30.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = { shouldShowOverview.value = true },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if(shouldShowOverview.value) colorResource(R.color.primary)
                    else colorResource(R.color.primary_light)),

                ){
                Text(text = "Alle Træninger", color = colorResource(id = R.color.main_background))
            }
            Button(
                onClick = { shouldShowOverview.value = false },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if(shouldShowOverview.value) colorResource(R.color.primary_light)
                    else colorResource(R.color.primary)),
            ) {
                Text(text = "Tilmeldte Træninger", color = colorResource(id = R.color.main_background))
            }
        }

        //filter button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 15.dp, 10.dp)
            , verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.End

        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_filter)
                , contentDescription = null
                , modifier = Modifier
                    .width(60.dp)
                    .height(30.dp)
                    .clickable { expanded = true }
            )

            //menu with filter options
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.primary_light))
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                        , horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        items.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                onClick = {
                                    //expanded = false
                                    if (item == "Tidspunkt"){expandedTidspunkt = !expandedTidspunkt}
                                    else if (item == "Hold"){expandedTeam = !expandedTeam}
                            }
                            , modifier = Modifier
                                    .width(125.dp)
                        ) {
                            Text(text = item)
                            }
                        }
                    }

                    //dropdown menu for time filtering
                    DropdownMenu(
                        expanded = expandedTidspunkt
                        , onDismissRequest = { expandedTidspunkt = false }
                        , modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .height(170.dp)
                                .verticalScroll(rememberScrollState(), enabled = true)
                            ,
                        ) {
                            times.forEachIndexed { index, time ->
                                DropdownMenuItem(
                                    onClick = {
                                        expandedTidspunkt = false
                                        expanded = false
                                        tidspunkt.value = time
                                    }
                                    , modifier = Modifier
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                        , horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = time
                                        )
                                    }
                                }
                            }
                        }
                    }

                    //dropdown menu for team filtering
                    DropdownMenu(
                        expanded = expandedTeam
                        , onDismissRequest = { expandedTeam = false }
                        , modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .height(170.dp)
                                .verticalScroll(rememberScrollState(), enabled = true)
                            ,
                        ) {
                            teams.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    onClick = {
                                        expandedTeam = false
                                        expanded = false
                                        team.value = item
                                    }
                                    , modifier = Modifier
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                        , horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = item
                                        )
                                    }
                                }
                            }
                        }
                    }

                    //Calendar shown as standard in filter menu, to filter by date.
                    AndroidView(
                        { CalendarView(it) }
                        , modifier = Modifier.wrapContentWidth()
                        , update = { views ->
                            views.setOnDateChangeListener { calendarView, year, month, day ->
                                val monthShifted = month+1
                                date.value = "$day/$monthShifted"
                                //date.value = Util().dateFormatter(day, monthShifted)
                            }
                        }
                    )
                }
            }
        }
        if(shouldShowOverview.value){
            viewModel.GetOverviewView(navController, date = date.value, timeStart = tidspunkt.value, team = team.value)
        }else {
            viewModel.GetSignedUpView(navController, date = date.value, timeStart = tidspunkt.value, team = team.value)
        }
    }
}

fun gotoTrainingDetails(training: Training, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("training", training)
    navController.navigate(NavigationRoute.TrainingDetails.route)
}
