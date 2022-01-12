package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.data.model.*
import com.example.bkskjold.data.util.getDay
import com.example.bkskjold.data.util.getMonthString
import com.example.bkskjold.data.util.getTime

@Composable
fun TrainingInfoPage(training: Training, navController: NavController) {
    var training = training
    var participantsID = training.participants
    val participants = remember { mutableStateOf(getUsersFromId(participantsID)) }

    val userId = CurrentUser.id
    val isAttending = remember { mutableStateOf(false) }

    isAttending.value = participantsID.contains(userId)


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        item {
            Box(modifier = Modifier.fillMaxSize()) {
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

            Column() {
                Row(Modifier.padding(top = 20.dp, bottom = 10.dp)) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "hold",
                        Modifier.padding(end = 10.dp)
                    )
                    Text(text = training.league + "-" + stringResource(R.string.TeamTraining),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(Modifier.padding(bottom = 10.dp)) {
                    Icon(
                        Icons.Outlined.Place,
                        contentDescription = "lokation",
                        Modifier.padding(end = 10.dp)
                    )
                    Text(text = training.location)
                }
                //date
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Icon(
                        Icons.Outlined.DateRange,
                        contentDescription = "dato",
                        Modifier.padding(end = 10.dp)
                    )
                    Text(
                        text = "${getDay(training.timeStart)}. ${getMonthString(training.timeStart)}")
                }
                //Time
                Row(modifier = Modifier.padding(start = 1.dp, bottom = 10.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_time),
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.height(22.dp),
                        contentDescription = "tidspunkt",
                    )
                    Text(
                        text = "${getTime(training.timeStart)} - ${getTime(training.timeEnd)}",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                //trainer
                Row(modifier = Modifier.padding(start = 1.dp, bottom = 10.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_whistle_outlined),
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.height(22.dp),
                        contentDescription = "tidspunkt",
                    )
                    Text(
                        text = training.trainer,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                Row(Modifier.padding(bottom = 10.dp)) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "deltagere",
                        Modifier.padding(end = 10.dp)
                    )
                    Text(text = ( /*TODO Count antal deltagere!*/ "" + "/" + training.maxParticipants + " " + stringResource(
                        R.string.participating)))
                }
                Row() {
                    Icon(
                        Icons.Outlined.ShoppingCart,
                        contentDescription = "pris",
                        Modifier.padding(end = 10.dp)
                    )
                    Text(text = stringResource(R.string.TrainingPrice, "20", "0"))
                }
            }


            //Description
            Column() {
                Text(text = training.description,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
                )
            }

            //Tilmeld/afmeld deltagelse
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (isAttending.value) {
                    Button(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(320.dp, 50.dp),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            training = updateParticipants(training, userId)
                            participantsID = training.participants
                            participants.value = getUsersFromId(participantsID)
                            isAttending.value = !isAttending.value
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(R.color.red)
                        ),
                    ) {
                        Row() {
                            Icon(
                                Icons.Outlined.Clear,
                                contentDescription = "tilmeld/afmeld træning",
                                Modifier.padding(end = 10.dp),
                                colorResource(id = R.color.main_background)
                            )
                            Text(
                                text = stringResource(R.string.Unattend),
                                color = colorResource(id = R.color.main_background),
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(320.dp, 50.dp),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            training = updateParticipants(training, userId)
                            participantsID = training.participants
                            participants.value = getUsersFromId(participantsID)
                            isAttending.value = !isAttending.value
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(R.color.green)
                        ),
                    ) {
                        Row() {
                            Icon(
                                Icons.Outlined.Check,
                                contentDescription = "tilmeld/afmeld træning",
                                Modifier.padding(end = 10.dp),
                                colorResource(id = R.color.main_background)
                            )
                            Text(
                                text = stringResource(R.string.Attend),
                                color = colorResource(id = R.color.main_background),
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp),
                horizontalAlignment = Alignment.End) {
                Button(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(120.dp, 50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary)),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Inviter",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon_share_white),
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(20.dp),
                        contentDescription = null,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(text = "Deltagere",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
//                Row(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 30.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween) {
//                    Text(text = "Navn")
//                    Text(text = "Tlfnr.")
//                }
                if (isAttending.value){
                    LazyColumn(Modifier.height(200.dp)) {
                        items(participants.value.size) { i ->
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.5.dp)
                                .padding(horizontal = 30.dp), Arrangement.SpaceBetween) {
                                if (participants.value[i].team != "guest") {
                                    Text(
                                        text = participants.value[i].firstName + " " + participants.value[i].lastName,
                                        Modifier.padding(vertical = 2.5.dp)
                                    )
                                } else {
                                    Text(
                                        text = participants.value[i].firstName + " " + participants.value[i].lastName + " (Gæst)",
                                        Modifier.padding(vertical = 2.5.dp)
                                    )
                                }
                                Text(
                                    text = participants.value[i].phoneNumber.toString()
                                        .chunked(2).joinToString(separator = " "),
                                    Modifier.padding(vertical = 2.5.dp)
                                )
                            }
                        }
                    }
                }else{
                    LazyColumn(Modifier.height(200.dp)) {
                        items(participants.value.size) { i ->
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.5.dp)
                                .padding(horizontal = 30.dp), Arrangement.SpaceBetween) {
                                if (participants.value[i].team != "guest") {
                                    Text(
                                        text = participants.value[i].firstName + " " + participants.value[i].lastName,
                                        Modifier.padding(vertical = 2.5.dp)
                                    )
                                } else {
                                    Text(
                                        text = participants.value[i].firstName + " " + participants.value[i].lastName + " (Gæst)",
                                        Modifier.padding(vertical = 2.5.dp)
                                    )
                                }
                                Text(
                                    text = participants.value[i].phoneNumber.toString()
                                        .chunked(2).joinToString(separator = " "),
                                    Modifier.padding(vertical = 2.5.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}






