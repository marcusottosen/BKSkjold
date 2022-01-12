package com.example.bkskjold.ui.view.reusables

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.CurrentUser
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.data.model.getUserFromID
import com.example.bkskjold.data.model.updateParticipants
import com.example.bkskjold.data.util.*
import com.example.bkskjold.ui.view.pages.gotoTrainingDetails

@Composable
fun TrainingCard(training: Training, navController: NavController) {
    val userId = CurrentUser.id
    val isAttending = remember { mutableStateOf(false)}
    var participants = training.participants

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
            Column (modifier = Modifier.width(95.dp)){
                Text( //date
                    text = "${getDay(training.timeStart)} ${getMonthString(training.timeStart)}",
                    fontSize = 10.sp,
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
                    fontSize = 10.sp,
                    color = Color.DarkGray
                )
            }

            Column {
                Box(
                    modifier = Modifier
                        //.padding(20.dp, 0.dp, 0.dp, 0.dp)
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(Color.Gray)
                        .weight(1f)
                )
            }

            Column {
                Row (
                    modifier = Modifier.padding(20.dp,2.dp,40.dp,2.dp)
                        ) {
                    Column() {
                        Text(
                            text = training.maxParticipants.toString(),
                            modifier = Modifier.padding(10.dp, 10.dp, 0.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = training.location,
                            modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                    }
                    Column() {
                        Text(
                            text = training.league,
                            modifier = Modifier.padding(10.dp, 10.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = getUserFromID(training.trainer).firstName + " " + getUserFromID(training.trainer).lastName,
                            modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                    }
                }
                /// NEW DEFAULT BUTTON
                Column(
                    modifier = Modifier
                        .padding(20.dp,2.dp,40.dp,2.dp)
                    , horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                        , onClick = {
                            updateParticipants(training, userId)
                            isAttending.value = !isAttending.value
                                    }
                        , shape = RoundedCornerShape(18.dp)
                        , colors= ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
                    ) {
                        if (isAttending.value){
                            Text(
                                text = "Afmeld Deltagelse"
                                , color = colorResource(id =R.color.main_background))
                        }else{
                            Text(
                                text = "Deltag"
                                , color = colorResource(id =R.color.main_background))
                        }
                    }
                }

            }
        }

        Row (
            horizontalArrangement = Arrangement.End,
        ){
            if (isAttending.value){
                Box(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .width(20.dp)
                        .fillMaxHeight()
                        .background(colorResource(R.color.green))
                )
            }else{
                Box(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .width(20.dp)
                        .fillMaxHeight()
                        .background(colorResource(R.color.red))
                )
            }

        }
    }
}