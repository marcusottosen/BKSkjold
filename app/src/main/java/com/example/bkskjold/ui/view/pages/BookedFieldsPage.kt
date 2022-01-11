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

@Composable
fun BookedFieldsPage(navController: NavController){
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Book bane", color = Color.White) },
                onClick = { navController.navigate("bookFieldPage") },
                icon = { Icon(Icons.Filled.Add, "", tint = Color.White)},
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
                    Text(
                        text = "Du har følgende bookninger",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    )
                }
            }
        }
    )
}

/*
@Composable
fun HomeContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Title") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = Color.Red,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        },
        content = {
            Surface(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    fontSize = 16.sp,
                )
            }
        }
    )
}*/