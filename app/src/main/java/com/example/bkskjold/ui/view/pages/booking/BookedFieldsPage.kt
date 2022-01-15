package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationItem
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.data.model.firebaseAdapter.getBookings
import com.example.bkskjold.ui.view.reusables.TrainingCard

@Composable
fun BookedFieldsPage(navController: NavController){
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Book bane", color = Color.White) },
                onClick = { navController.navigate(NavigationRoute.BookFieldPage.route) },
                icon = { Icon(Icons.Filled.Add, "", tint = Color.White)},
                modifier = Modifier.padding(bottom = 60.dp),
                backgroundColor = colorResource(id = R.color.green)
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 25.dp)
                    .wrapContentSize(Alignment.TopCenter)
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
                    Text(
                        text = "Du har følgende bookninger",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 45.dp, top = 10.dp)
                    )
                }
                item{
                    val bookings = getBookings()
                    Column(modifier = Modifier.fillMaxSize()) {
                        for (item in bookings){
                            TrainingCard(item, navController)
                        }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 110.dp))
                }
            }
        }
    )
}