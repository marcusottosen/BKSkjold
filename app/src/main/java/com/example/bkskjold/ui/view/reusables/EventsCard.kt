package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

@Composable
fun EventsCard(header: String, description: String, time: String, location: String) {
    Card( //event card
        modifier = Modifier
            .padding(15.dp)
            .border(1.dp, colorResource(R.color.main_border)),
            //.height(200.dp),
        backgroundColor = colorResource(R.color.main_background),
        shape = RoundedCornerShape(18.dp),
        elevation = 12.dp,

    ) {
        Column(

        ){
            Column(
                modifier = Modifier
                    .padding(20.dp, 15.dp, 20.dp, 15.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(// event header
                    text = header,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp, 15.dp)
                )
                //Spacer(modifier = Modifier.height(8.dp))
                Text( // event description
                    text = description,
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
                        Text(text = time, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp, 1.dp, 0.dp, 0.dp))
                    }
                    Row() { //location
                        Image(painter = painterResource(id = R.drawable.icon_location), contentDescription = null)
                        Text(text = location, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp, 1.dp, 0.dp, 0.dp))
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