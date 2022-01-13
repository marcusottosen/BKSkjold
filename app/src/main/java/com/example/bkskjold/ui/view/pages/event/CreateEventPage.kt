package com.example.bkskjold.ui.view.pages

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.dataClass.Locations
import com.example.bkskjold.data.model.firebaseAdapter.newEvent
import com.example.bkskjold.data.model.firebaseAdapter.newTrainingFromBooking
import com.example.bkskjold.ui.view.reusables.dropDownMenu
import java.text.DateFormatSymbols

@Composable
fun CreateEventPage(navController: NavController){
    val fields = mutableListOf<String>()
    for (field in Locations.values()){
        fields.add(field.toString())
    }
    val priceOptions = mutableListOf<String>()
    var num = 0
    for (i in 0..10){
        priceOptions.add(num.toString())
        num += 5
    }
    val months = mutableListOf<String>()
    for (month in DateFormatSymbols().months){
        months.add(month.toString())
    }
    val days = mutableListOf<String>()
    for (i in 1..31){
        days.add(i.toString())
    }
    val hours = mutableListOf<String>()
    for (i in 1..24){
        hours.add(i.toString())
    }
    val minutes = mutableListOf<String>()
    for (i in 1..60){
        minutes.add(i.toString())
    }
    var location = "A"
    var month = "January"
    var day = 1
    var startHour = 1
    var startMin = 0
    var endHour = 1
    var endMin = 0
    var price = 0
    val header =  remember { mutableStateOf(TextFieldValue()) }
    val description =  remember { mutableStateOf(TextFieldValue()) }

    val showDialog = remember {mutableStateOf(false)}
    if (showDialog.value) {
        Toast.makeText(LocalContext.current, "Event oprettet", Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Save", color = Color.White) },
                onClick = {
                    showDialog.value = true
                    newEvent(
                        location = location,
                        month = month,
                        day = day,
                        startHour = startHour,
                        startMin = startMin,
                        endHour = endHour,
                        endMin = endMin,
                        header = header.value.text,
                        description = description.value.text,
                        price = price,
                        navController = navController
                    )
                },
                icon = { Icon(Icons.Filled.Check, "Back", tint = Color.White) },
                modifier = Modifier.padding(bottom = 60.dp),
                backgroundColor = colorResource(id = R.color.green)
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                item {
                    Box() {
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
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.bookingBackground))
                    ) {
                        Column(modifier = Modifier.padding(start = 15.dp)) {
                            Text(
                                text = "Overskrift",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            TextField(
                                value = header.value,
                                onValueChange = { header.value = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    cursorColor = colorResource(R.color.primary),
                                    focusedIndicatorColor = colorResource(R.color.primary)
                                )
                            )

                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            Text(
                                text = "Beskrivelse",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            TextField(
                                value = description.value,
                                onValueChange = { description.value = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    cursorColor = colorResource(R.color.primary),
                                    focusedIndicatorColor = colorResource(R.color.primary)
                                )
                            )

                            Spacer(modifier = Modifier.padding(top = 40.dp))

                            Text(
                                text = "Placering",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            location = dropDownMenu(items = fields as MutableList<String>, menuWidth = 110)

                            Spacer(modifier = Modifier.padding(top = 40.dp))


                            Text(
                                text = "Dato",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Row() {
                                Column() {
                                    Text(text = "Måned")
                                    month = dropDownMenu(items = months, menuWidth = 110)
                                }
                                Spacer(modifier = Modifier.padding(start = 20.dp))

                                Column() {
                                    Text(text = "Dag")
                                    day = dropDownMenu(items = days, menuWidth = 60).toInt()
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 40.dp))

                            Text(
                                text = "Tidspunkt",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Row() {
                                Column() {
                                    Text(text = "Start", fontWeight = FontWeight.Bold)
                                    Text(text = "Time")
                                    startHour = dropDownMenu(items = hours, menuWidth = 60).toInt()
                                }
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                Column() {
                                    Text(text = "T", fontWeight = FontWeight.Bold, color = colorResource(R.color.bookingBackground))
                                    Text(text = "Minut")
                                    startMin = dropDownMenu(items = minutes, menuWidth = 60).toInt()
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            Row() {
                                Column() {
                                    Text(text = "Slut", fontWeight = FontWeight.Bold)
                                    Text(text = "Time")
                                    endHour = dropDownMenu(items = hours, menuWidth = 60).toInt()
                                }
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                Column() {
                                    Text(text = "T", fontWeight = FontWeight.Bold, color = colorResource(R.color.bookingBackground))
                                    Text(text = "Minut")
                                    endMin = dropDownMenu(items = minutes, menuWidth = 60).toInt()
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            Text(
                                text = "Pris",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )

                            price = dropDownMenu(items = priceOptions as MutableList<String>, menuWidth = 60).toInt()

                            Spacer(modifier = Modifier.padding(top = 50.dp))


                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 100.dp))
                }
            }
        }
    )
}