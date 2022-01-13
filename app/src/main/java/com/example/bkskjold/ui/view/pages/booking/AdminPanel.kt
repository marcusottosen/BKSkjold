package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationItem

@Composable
fun AdminPanel(navController: NavController){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = 25.dp)) {
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
                    onClick = { navController.navigate(NavigationItem.Home.route) },
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
        }
        item {

            Image(
                painter = painterResource(id = R.drawable.img_admin_panel),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .height(250.dp),
                alignment = Alignment.Center,
                contentDescription = null,
            )
            Button(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(320.dp, 50.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = { navController.navigate("newTrainingPage") },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.primary)
                )
            ) {
                Text(
                    text = "Opret ny træning",
                    color = colorResource(id = R.color.main_background)
                )
            }

            Button(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(320.dp, 50.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = {navController.navigate("createEventPage") },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.primary)
                )
            ) {
                Text(
                    text = "Opret nyt event",
                    color = colorResource(id = R.color.main_background)
                )
            }

            Button(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(320.dp, 50.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = {navController.navigate("newNewsPage") },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.primary)
                )
            ) {
                Text(
                    text = "Opret nyhed",
                    color = colorResource(id = R.color.main_background)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }

}