package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun NextTrainingCard(trainings: Training, navController: NavController) {
    var training = trainings
    val participants = training.participants

    val userId = CurrentUser.id
    val isAttending = remember { mutableStateOf(participants.contains(userId)) }


    Card(
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.border)),
        modifier = if (training.location != stringResource(R.string.NotAttendingAnyTrainings)) {
            Modifier
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .height(220.dp)
                .clickable {
                    gotoTrainingDetails(training, navController)
                }
        } else {
            Modifier
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .height(150.dp)
        },
        elevation = 0.dp
    ) {
        Column {
            Card(
                //event card
                modifier = Modifier
                    .height(150.dp),
                backgroundColor = colorResource(R.color.primary),
                shape = RoundedCornerShape(22.dp),
                elevation = 0.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_football),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .alpha(0.05F)
                        .padding(200.dp, 0.dp, 0.dp, 0.dp)
                        .width(800.dp),
                )
                Column(
                    modifier = Modifier
                        .padding(20.dp, 15.dp, 20.dp, 15.dp)
                ) {
                    Text(//Card title
                        text = stringResource(R.string.nextTraining),
                        color = Color.White
                    )
                    if (training.location != stringResource(R.string.NotAttendingAnyTrainings)) {
                        Text(// Header
                            text = stringResource(R.string.trainingFor) + " " + training.league,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                            color = Color.White
                        )
                    } else {
                        Text(// Header
                            text = stringResource(R.string.noTraining),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                            color = Color.White
                        )
                    }

                    Box(modifier = Modifier.fillMaxSize()) {
                        Row(
                            //Row to display time and date
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                .align(alignment = Alignment.BottomStart)
                        ) {
                            if (training.location != stringResource(R.string.NotAttendingAnyTrainings)) {
                                Column(modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Bottom) { //Date & time
                                    Text(
                                        text = "${getDay(training.timeStart)}. ${
                                            getMonthString(training.timeStart)
                                        }", fontSize = 10.sp, color = Color.White
                                    )
                                    Text(text = getWeekDay(training.timeStart),
                                        fontSize = 20.sp,
                                        color = Color.White)
                                    Text(
                                        text = "${getTime(training.timeStart)} - ${getTime(training.timeEnd)}",
                                        fontSize = 10.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            Column(modifier = Modifier.fillMaxSize()) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Row(
                                        //team
                                        modifier = Modifier
                                            .padding(20.dp, 0.dp, 0.dp, 20.dp)
                                            .align(alignment = Alignment.BottomStart),
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon_group_white),
                                            contentDescription = null,
                                            modifier = Modifier.size(15.dp),
                                        )
                                        Text(
                                            text = training.league,
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .padding(start = 5.dp)
                                                .align(alignment = Alignment.Bottom),
                                            color = Color.White
                                        )
                                    }

                                    if (training.location != stringResource(R.string.NotAttendingAnyTrainings)) {
                                        Row(
                                            //location
                                            modifier = Modifier
                                                .padding(start = 20.dp)
                                                .align(alignment = Alignment.BottomStart),
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icon_location_white),
                                                contentDescription = null,
                                                modifier = Modifier.size(15.dp),
                                            )
                                            Text(
                                                text = training.location,
                                                fontSize = 10.sp,
                                                modifier = Modifier
                                                    .padding(start = 5.dp)
                                                    .align(alignment = Alignment.Bottom),
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (training.location != stringResource(R.string.NotAttendingAnyTrainings)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 25.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxHeight()) {

                            Row( //number of players
                                modifier = Modifier
                                    .padding(top = 15.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon_person),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = "${training.participants.size}/${training.maxParticipants}",
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                )
                            }

                            Row( //trainer
                                modifier = Modifier
                                    .padding(top = 40.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon_whistle),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(15.dp),
                                )
                                if (training.location != stringResource(R.string.NotAttendingAnyTrainings)) {
                                    Text(
                                        text = getUserFromID(training.trainer).firstName + " " + getUserFromID(
                                            training.trainer).lastName,
                                        fontSize = 10.sp,
                                        modifier = Modifier
                                            .padding(start = 5.dp)
                                    )
                                } else {
                                    Text(
                                        text = "N/A",
                                        fontSize = 10.sp,
                                        modifier = Modifier
                                            .padding(start = 5.dp)
                                    )
                                }
                            }
                        }
                    }

                    Column(
                        //Invite
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_home_share_button),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = stringResource(R.string.Invite),
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }

                    Column(
                        //Cancel
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 30.dp)
                            .clickable {
                                if (training.participants.contains(userId)) {
                                    training.participants.remove(userId)
                                } else {
                                    training.participants.add(userId)
                                }
                                training = updateParticipants(training, participants, userId)
                                isAttending.value = !isAttending.value
                            },
                        verticalArrangement = Arrangement.Center,
                    ) {
                        if (isAttending.value) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_home_cancel_button),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp)
                            )
                            Text(
                                text = stringResource(R.string.deregister),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.icon_home_accept_button),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp)
                            )
                            Text(
                                text = stringResource(R.string.Attend),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}