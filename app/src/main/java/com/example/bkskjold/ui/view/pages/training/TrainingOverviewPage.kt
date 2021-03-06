package com.example.bkskjold.ui.view.pages.training

import android.widget.CalendarView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.ui.viewmodel.TrainingOverviewViewModel

@Composable
fun TrainingOverview(navController: NavController) {
    val viewModel = TrainingOverviewViewModel()
    val shouldShowOverview = remember { mutableStateOf(true) }

    //main filter menu variables
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(stringResource(R.string.Team), stringResource(R.string.Time))
    //var selectedIndex by remember { mutableStateOf(0) }

    //Time filter menu variables
    var expandedTidspunkt by remember { mutableStateOf(false) }
    val times = listOf("15:00",
        "15:30",
        "16:00",
        "16:30",
        "17:00",
        "17:30",
        "18:00",
        "18:30",
        "19:00",
        "19:30",
        "20:00",
        "20:30",
        "21:00")

    //team filter menu variables
    var expandedTeam by remember { mutableStateOf(false) }
    val teams = viewModel.getTeams()

    //passed filter values
    val chosenTime = remember { mutableStateOf("") }
    val team = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }

    val filterModifier = Modifier
        .wrapContentWidth()
        .padding(15.dp, 0.dp)
        .height(30.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(colorResource(R.color.primary))

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { shouldShowOverview.value = true },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (shouldShowOverview.value) colorResource(R.color.primary)
                    else colorResource(R.color.primary_light)),

                ) {
                Text(text = stringResource(R.string.AllTrainings),
                    color = colorResource(id = R.color.main_background))
            }
            Button(
                onClick = { shouldShowOverview.value = false },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (shouldShowOverview.value) colorResource(R.color.primary_light)
                    else colorResource(R.color.primary)),
            ) {
                Text(text = stringResource(R.string.AttendingTrainings),
                    color = colorResource(id = R.color.main_background))
            }
        }

        //filter buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp, 10.dp, 15.dp, 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                if (chosenTime.value != "") {
                    Box(modifier = filterModifier.clickable { chosenTime.value = "" }
                    ) {
                        Row(Modifier.padding(5.dp, 5.dp)) {
                            Text(text = chosenTime.value,
                                Modifier.padding(start = 10.dp, end = 5.dp),
                                color = Color.White
                            )
                            Icon(imageVector = Icons.Outlined.Close,
                                contentDescription = "Close",
                                tint = Color.White)
                        }
                    }
                }

                if (team.value != "") {
                    Box(modifier = filterModifier.clickable { team.value = "" }
                    ) {
                        Row(Modifier.padding(5.dp, 5.dp)) {
                            Text(text = team.value,
                                Modifier.padding(start = 10.dp, end = 5.dp),
                                color = Color.White
                            )
                            Icon(imageVector = Icons.Outlined.Close,
                                contentDescription = "Close",
                                tint = Color.White)
                        }
                    }
                }

                if (date.value != "") {
                    Box(modifier = filterModifier.clickable { date.value = "" }
                    ) {
                        Row(Modifier.padding(5.dp, 5.dp)) {
                            Text(text = date.value,
                                Modifier.padding(start = 10.dp, end = 5.dp),
                                color = Color.White
                            )
                            Icon(imageVector = Icons.Outlined.Close,
                                contentDescription = "Close",
                                tint = Color.White)
                        }
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.icon_filter),
                    contentDescription = null,
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .clickable { expanded = true }
                )
            }

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
                            .background(colorResource(id = R.color.primary)),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        items.forEachIndexed { _, item ->
                            DropdownMenuItem(
                                onClick = {
                                    if (item == "Tidspunkt") {
                                        expandedTidspunkt = !expandedTidspunkt
                                    } else if (item == "Hold") {
                                        expandedTeam = !expandedTeam
                                    }
                                }, modifier = Modifier.width(125.dp)
                            ) {
                                Text(text = item, color = Color.White)
                            }
                        }
                    }

                    //dropdown menu for time filtering
                    DropdownMenu(
                        expanded = expandedTidspunkt,
                        onDismissRequest = { expandedTidspunkt = false },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .height(170.dp)
                                .verticalScroll(rememberScrollState(), enabled = true),
                        ) {
                            times.forEachIndexed { _, time ->
                                DropdownMenuItem(
                                    onClick = {
                                        expandedTidspunkt = false
                                        expanded = false
                                        chosenTime.value = time
                                    }, modifier = Modifier
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
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
                        expanded = expandedTeam,
                        onDismissRequest = { expandedTeam = false },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .height(170.dp)
                                .verticalScroll(rememberScrollState(), enabled = true),
                        ) {
                            teams.forEachIndexed { _, item ->
                                DropdownMenuItem(
                                    onClick = {
                                        expandedTeam = false
                                        expanded = false
                                        team.value = item
                                    }, modifier = Modifier
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
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
                        { CalendarView(it) },
                        modifier = Modifier.wrapContentWidth(),
                        update = { views ->
                            views.setOnDateChangeListener { _, _, month, day ->
                                val monthShifted = month + 1
                                date.value = "$day/$monthShifted"
                            }
                        }
                    )
                }
            }
        }
        if (shouldShowOverview.value) {
            viewModel.GetOverviewView(navController,
                date = date.value,
                timeStart = chosenTime.value,
                team = team.value)
        } else {
            viewModel.GetSignedUpView(navController,
                date = date.value,
                timeStart = chosenTime.value,
                team = team.value)
        }
    }
}

fun gotoTrainingDetails(training: Training, navController: NavController) {
    navController.currentBackStackEntry?.arguments?.putParcelable("training", training)
    navController.navigate(NavigationRoute.TrainingDetails.route)
}