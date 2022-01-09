package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bkskjold.R

@Preview
@Composable
fun AppStartPage() {

    val image = painterResource(id = R.drawable.login_image)

    var emailValue by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {

            Image(image, contentDescription = "login_image")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .background(Color.White)
        ) {
            Text(
                text = "Velkommen til NemSport",
            )
            Text(
                text = "log ind",
            )

            OutlinedTextField(
                value = emailValue,
                onValueChange = {emailValue = it},
                label = { Text("Email adresse") },
            )
        }
    }
}