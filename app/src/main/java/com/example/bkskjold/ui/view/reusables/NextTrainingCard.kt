package com.example.bkskjold.ui.view.reusables

import androidx.compose.ui.tooling.preview.Preview

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R



@Composable
fun NextTrainingCard(header: String, description: String, date: String, time: String, location: String) {
    Card( //event card
        modifier = Modifier
            .padding(15.dp)
        .height(200.dp),
        backgroundColor = colorResource(R.color.primary),
        shape = RoundedCornerShape(22.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_nexttrainingcard),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.alpha(0.6F).padding(190.dp,10.dp,0.dp,0.dp).width(160.dp),
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 15.dp, 20.dp, 15.dp)
        ) {
            Text(//Card title
                text = "Næste Træning" ,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Text(// Header
                text = header,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp,0.dp,0.dp, 5.dp)
            )
            Text( // Small title
                text = description,
                modifier = Modifier.padding(0.dp,0.dp,0.dp, 5.dp),
                fontWeight=FontWeight.SemiBold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row( //Row to display time and location
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Column() { //Date & time
                    Text(text = time, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                    Text(text = "I dag", fontSize = 10.sp)
                    Text(text = date, color = Color.Gray, fontSize = 10.sp)
                }

                Row(
                    modifier = Modifier
                        .padding(70.dp, 0.dp, 0.dp, 0.dp)
                        .align(alignment = Alignment.Bottom),
                ) { //location
                    Image(
                        painter = painterResource(id = R.drawable.icon_location),
                        contentDescription = null,
                    )
                    Text(
                        text = location,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(5.dp, 1.dp, 0.dp, 0.dp)
                            .align(alignment = Alignment.Bottom),
                    )
                }
            }
        }
    }
}