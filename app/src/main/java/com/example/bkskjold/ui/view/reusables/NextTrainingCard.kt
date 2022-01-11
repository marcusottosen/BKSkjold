package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.bkskjold.R
import com.example.bkskjold.data.model.Training


@Composable
fun NextTrainingCard(training: Training) {
    Card(
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.border)),
        modifier = Modifier
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(220.dp),
        elevation = 0.dp
    ) {
        Column() {
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
                    Text(// Header
                        text = stringResource(R.string.trainingFor) + " " + training.league,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row( //Row to display time and location
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 0.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column() { //Date & time
                            Text(text = "25. oktober", fontSize = 10.sp, color = Color.White) //TODO getDate(training.timeStart) (Kan først når timeEnd etc er timestamp
                            Text(text = "mandag", fontSize = 20.sp, color = Color.White) //TODO getWeekDay(training.timeStart)
                            Text(
                                text = "16:00 - 17:30", //TODO getTime(training.startTime) - getTime(training.endTime)
                                fontSize = 10.sp,
                                color = Color.White
                            )
                        }

                        Column(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Row( //team
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

                                Row( //location
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
                                text = "${training.participants.size}/${training.maxParticipants}", //$attending/$spots"
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
                            Text(
                                text = training.trainer,
                                fontSize = 10.sp,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                        }
                    }
                }

                Column( //Invite
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

                Column( //Cancel
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 30.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_home_cancel_button),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = stringResource(R.string.deregister),
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