package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.newNews

@Composable
fun NewNewsPage(navController: NavController){
    val header =  remember { mutableStateOf(TextFieldValue()) }
    val description =  remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Save", color = Color.White) },
                onClick = {
                    newNews(header.value.text,description.value.text,navController)
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

                            Spacer(modifier = Modifier.padding(top = 40.dp))

                            Text(
                                text = "Beskrivelse",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            TextField(
                                value = description.value,
                                modifier = Modifier.height(300.dp),
                                onValueChange = { description.value = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    cursorColor = colorResource(R.color.primary),
                                    focusedIndicatorColor = colorResource(R.color.primary)
                                )
                            )
                            Spacer(modifier = Modifier.padding(top = 50.dp))
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 200.dp))
                }
            }
        }
    )
}