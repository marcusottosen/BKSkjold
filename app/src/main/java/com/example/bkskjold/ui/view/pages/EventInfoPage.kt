package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.Event
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.data.util.getDate
import com.example.bkskjold.data.util.getTime

//TODO Alt tekst skal hentes fra database! Evt igennem et event objekt?
@Composable
fun EventInfoPage(event: Event, navController: NavController) {
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

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.img_event_topimg),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(177.dp),
                    alignment = Alignment.Center,
                    contentDescription = null,
                )
            }


            Spacer(modifier = Modifier.height(25.dp))
            Text(//Title
                text = event.header,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(//Description
                text = event.description,
                fontSize = 14.sp,
                color = Color.Gray
            )

            //Location
            Row(modifier = Modifier.padding(top = 30.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.icon_location),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(20.dp),
                    contentDescription = null,
                )
                Text(
                    text = event.location,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            //Date
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.icon_calendar),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(20.dp),
                    contentDescription = null,
                )
                Text(
                    text = getDate(event.timeStart),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            //Time
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.icon_time),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(20.dp),
                    contentDescription = null,
                )
                Text(
                    text = "${getTime(event.timeStart)} - ${getTime(event.timeEnd)}",
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            //Price
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.icon_money),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(20.dp),
                    contentDescription = null,
                )
                Text(
                    text = "Pris: ${event.price} kr.",
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(320.dp, 50.dp),
                shape = RoundedCornerShape(12.dp),
                colors= ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary)),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "DELTAG",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
                horizontalAlignment = Alignment.End) {
                Button(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(120.dp, 50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.green)),
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

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Deltagere",
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            //TODO Hent navne fra database
            for (i in 0..10){
                Text(//Deltagere
                    text = "Fornavn Efternavn",
                    modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}