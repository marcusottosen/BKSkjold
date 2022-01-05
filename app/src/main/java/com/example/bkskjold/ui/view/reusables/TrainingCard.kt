package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R
import com.example.bkskjold.data.model.Trainings

@Composable
fun TrainingCard(training: Trainings

    /*
    id: String,
    timeStart: String,
    timeEnd: String,
    date: String,
    weekday: String,
    participants: String,
    location: String,
    league: String,
    trainer: String,
    attending: String,
    color: Color
*/
) { //TODO Gør så der kan skiftes mellem deltager/deltager ikke
    var isAttending = ""
    val color: Color

    if (training.isAttending ){
        isAttending = "Afmeld Deltagelse"
        color = colorResource(R.color.green)
    } else {
        isAttending = "Deltag"
        color = colorResource(R.color.red)
    }

    Card(
        modifier = Modifier
            .padding(30.dp, 10.dp, 30.dp, 20.dp)
            .height(85.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(9.dp),
        elevation = 12.dp
    ) {
        Row {
            Column {
                Text( //date
                    text = training.date,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(10.dp, 18.dp, 5.dp),
                    color = Color.DarkGray
                )
                //Spacer(modifier = Modifier.height(8.dp))
                Text( //day
                    text = training.weekday,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                    fontSize = 20.sp,
                )
                Text( //time
                    text = "${training.timeStart} - ${training.timeEnd}",
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
                            text = training.trainer,
                            modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                DefaultButton(text = isAttending,
                    checked = true,
                    onClick = { /*TODO*/},
                    modifier = Modifier.padding(20.dp,2.dp,40.dp,2.dp)
                )
            }
        }

        Row (
            horizontalArrangement = Arrangement.End,
        ){
            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .width(20.dp)
                    .fillMaxHeight()
                    .background(color)
            )
        }
    }
}