package com.example.bkskjold.ui.view.pages.training

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.dropDownMenu
import com.example.bkskjold.ui.viewmodel.BookTrainingViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun NewTrainingPage(navController: NavController){


    val showDialog = remember {mutableStateOf(false)}
    if (showDialog.value) {
        Toast.makeText(LocalContext.current, "Træning oprettet", Toast.LENGTH_SHORT).show()
    }



    val viewModel = BookTrainingViewModel()

    var team = viewModel.getTeams()[0]
    var field = viewModel.getFields()[0]
    var maxParticipants = 1
    val description =  remember { mutableStateOf(TextFieldValue()) }
    val date =  remember { mutableStateOf("Vælg dato") }
    val startTime =  remember { mutableStateOf("Vælg starttid") }
    val endTime =  remember { mutableStateOf("Vælg sluttid") }

    val context = LocalContext.current



    //Variables to keep track of when to open/close date/time pickers
    val dateDialogState = rememberMaterialDialogState()
    val startTimeDialogState = rememberMaterialDialogState()
    val endTimeDialogState = rememberMaterialDialogState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Save", color = Color.White) },
                onClick = {
                    showDialog.value = true
                    viewModel.newTraining(
                        team = team,
                        location = field,
                        date = date.value,
                        startTime = startTime.value,
                        endTime = endTime.value,
                        maxParticipants = maxParticipants,
                        description = description.value.text,
                        navController = navController,
                        context = context
                    )
                },
                icon = { Icon(Icons.Filled.Check, "Back", tint = Color.White) },
                modifier = Modifier.padding(bottom = 60.dp),
                backgroundColor = colorResource(id = R.color.green)
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                item {
                    Box {
                        Button(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(50.dp, 50.dp)
                                .align(alignment = Alignment.TopStart),
                            shape = RoundedCornerShape(40.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp
                            ),
                            onClick = { navController.navigateUp() },
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_back_arrow),
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .height(20.dp),
                                contentDescription = null,
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.bookingBackground))
                    ) {
                        Column(modifier = Modifier.padding(start = 15.dp)) {
                            Text(
                                text = "Hold",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )

                            team = dropDownMenu(items = viewModel.getTeams(), menuWidth = 110)

                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            Text(
                                text = "Bane",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )

                            field = dropDownMenu(items = viewModel.getFields(), menuWidth = 60)

                            Spacer(modifier = Modifier.padding(top = 40.dp))

                            Text(
                                text = "Dato",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Button(
                                onClick = { dateDialogState.show() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = date.value)
                            }
                            MaterialDialog(
                                dialogState = dateDialogState,
                                buttons = {
                                    positiveButton("Ok")
                                    negativeButton("Cancel")
                                }
                            ) {
                                datepicker { dateChosen ->
                                    date.value = dateChosen.toString()
                                    println(date.value)
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 40.dp))



                                                        Text(
                                text = "Tidspunkt",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Text(text = "Start", fontWeight = FontWeight.Bold)
                            Button(
                                onClick = { startTimeDialogState.show() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = startTime.value)
                            }
                            MaterialDialog(
                                dialogState = startTimeDialogState,
                                buttons = {
                                    positiveButton("Ok")
                                    negativeButton("Cancel")
                                }
                            ) {
                                timepicker { time ->
                                    startTime.value = time.toString()
                                    println(startTime.value)
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            Text(text = "Slut", fontWeight = FontWeight.Bold)
                            Button(
                                onClick = { endTimeDialogState.show() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = endTime.value)
                            }
                            MaterialDialog(
                                dialogState = endTimeDialogState,
                                buttons = {
                                    positiveButton("Ok")
                                    negativeButton("Cancel")
                                }
                            ) {
                                timepicker { time ->
                                    endTime.value = time.toString()
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 40.dp))

                            Text(
                                text = "Andet",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Text(
                                text = "Max antal deltagere",

                                )
                            maxParticipants = dropDownMenu(items = viewModel.getParticipantList(), menuWidth = 60).toInt()

                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            Text(
                                text = "Beskrivelse",

                                )
                            TextField(
                                value = description.value,
                                onValueChange = { description.value = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    cursorColor = colorResource(R.color.primary),
                                    focusedIndicatorColor = colorResource(R.color.primary)
                                )
                            )
                            Spacer(modifier = Modifier.padding(top = 50.dp))

                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 200.dp))
                }
            }
        }
    )
}