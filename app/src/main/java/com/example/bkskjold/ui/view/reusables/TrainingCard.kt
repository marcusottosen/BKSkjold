package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrainingCard() { //TODO Gør så der kan skiftes mellem deltager/deltager ikke
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
                Text( //Time
                    text = "17:47",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 5.dp)
                )
                //Spacer(modifier = Modifier.height(8.dp))
                Text( //Days to Training
                    text = "I dag",
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                    fontSize = 10.sp,
                    color = Color.DarkGray
                )
                Text( //Date
                    text = "25 Oktober 2021",
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
                Row {
                    Column() {
                        Text(
                            text = "8/12",
                            modifier = Modifier.padding(10.dp, 10.dp, 0.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "Bane C",
                            modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                    }
                    Column() {
                        Text(
                            text = "U12",
                            modifier = Modifier.padding(10.dp, 10.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "Træner: Ekkart",
                            modifier = Modifier.padding(10.dp, 0.dp, 10.dp),
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                    }

                }

                DefaultButton(text = "Deltag",
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
                    .background(Color(247, 172, 155))
            )
        }
    }
}