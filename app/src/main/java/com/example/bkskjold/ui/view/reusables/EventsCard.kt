package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.Event
import com.example.bkskjold.data.util.getDay
import com.example.bkskjold.data.util.getDayMonth
import com.example.bkskjold.data.util.getMonthString
import com.example.bkskjold.data.util.getTime
import com.example.bkskjold.ui.view.pages.gotoEventDetails


@Composable
fun EventsCard(event: Event, navController: NavController) {
    Card( //event card
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .padding(15.dp)
            .clickable { gotoEventDetails(event, navController)},
        backgroundColor = colorResource(R.color.main_background),
        elevation = 3.dp,

    ) {
        Column(

        ){
            Column(
                modifier = Modifier
                    .padding(20.dp, 15.dp, 20.dp, 15.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(// event header
                    text = event.header,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp, 15.dp)
                )
                Text( // event description
                    text = event.description,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp, 5.dp),
                    color = Color.DarkGray
                )
                Row( //Row to display time and location
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 35.dp, 0.dp, 0.dp)
                        .height(20.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row() { //time
                        Image(painter = painterResource(id = R.drawable.icon_calendar), contentDescription = null)
                        Text(text = "${getDay(event.timeStart)}. ${getMonthString(event.timeStart)}  kl. ${getTime(event.timeStart)}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp, 1.dp, 0.dp, 0.dp))
                    }
                    Row() { //location
                        Image(painter = painterResource(id = R.drawable.icon_location), contentDescription = null)
                        Text(text = event.location, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp, 1.dp, 0.dp, 0.dp))
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .height(6.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.primary))
            )
        }
    }
}