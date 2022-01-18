package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.data.model.dataClass.Training
import com.example.bkskjold.data.model.firebaseAdapter.getUserFromID
import com.example.bkskjold.data.model.firebaseAdapter.updateParticipants
import com.example.bkskjold.data.util.getDay
import com.example.bkskjold.data.util.getMonthString
import com.example.bkskjold.data.util.getTime
import com.example.bkskjold.data.util.getWeekDay
import com.example.bkskjold.ui.view.pages.training.gotoTrainingDetails

@Composable
fun TrainingCard(trainings: Training, navController: NavController) {
    var training = trainings

    val iconSize = 11
    val userId = CurrentUser.id
    val isAttending = remember { mutableStateOf(false) }
    val participants = training.participants

    isAttending.value = participants.contains(userId)

    Card(
        modifier = Modifier
            .padding(30.dp, 10.dp, 30.dp, 20.dp)
            .height(85.dp)
            .fillMaxWidth()
            .clickable {
                gotoTrainingDetails(training, navController)
            },
        shape = RoundedCornerShape(9.dp),
        elevation = 3.dp
    ) {
        Row {
            Column(modifier = Modifier.width(95.dp)) {
                Text( //date
                    text = "${getDay(training.timeStart)} ${getMonthString(training.timeStart)}",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(10.dp, 18.dp, 5.dp),
                    color = Color.DarkGray
                )
                Text( //day
                    text = getWeekDay(training.timeStart),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                    fontSize = 20.sp
                )
                Text( //time
                    text = "${getTime(training.timeStart)} - ${getTime(training.timeEnd)}",
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                    fontSize = 11.sp,
                    color = Color.DarkGray
                )
            }
            Column { //Separation line
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(Color.Gray)
                        .weight(1f)
                )
            }

            Column { //Number of attending & location
                Row(
                    modifier = Modifier.padding(20.dp, 2.dp, 40.dp, 2.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_group_black),
                                contentDescription = null,
                                modifier = Modifier.size(iconSize.dp),
                            )
                            Text(
                                text = "${training.participants.size}/${training.maxParticipants}",
                                modifier = Modifier.padding(start = 5.dp, end = 15.dp),
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(0.dp, 5.dp, 0.dp, 0.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_location_black),
                                contentDescription = null,
                                modifier = Modifier.size(iconSize.dp),
                            )
                            Text(
                                text = training.location,
                                modifier = Modifier.padding(start = 5.dp, end = 15.dp),
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                        }
                    }

                    Column { //Team & trainer name
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_person),
                                contentDescription = null,
                                modifier = Modifier.size(iconSize.dp),
                            )
                            Text(
                                text = training.league,
                                modifier = Modifier.padding(start = 5.dp),
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_whistle),
                                contentDescription = null,
                                modifier = Modifier.size(iconSize.dp),
                            )
                            Text(
                                text = getUserFromID(training.trainer).firstName + " " + getUserFromID(
                                    training.trainer).lastName,
                                modifier = Modifier.padding(start = 5.dp),
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(20.dp, 2.dp, 40.dp, 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth(),
                        onClick = {
                            if (training.participants.contains(userId)) {
                                training.participants.remove(userId)
                            } else {
                                training.participants.add(userId)
                            }
                            training = updateParticipants(training, participants, userId)
                            isAttending.value = !isAttending.value
                        },
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
                    ) {
                        if (isAttending.value) {
                            Text(
                                text = stringResource(R.string.Unattend),
                                color = colorResource(id = R.color.main_background))
                        } else {
                            Text(
                                text = stringResource(R.string.Attend),
                                color = colorResource(id = R.color.main_background))
                        }
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.End,
        ) {
            if (isAttending.value) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .fillMaxHeight()
                        .background(colorResource(R.color.green))
                )
            } else {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .fillMaxHeight()
                        .background(colorResource(R.color.red))
                )
            }

        }
    }
}