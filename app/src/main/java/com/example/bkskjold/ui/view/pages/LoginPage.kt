package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

@Composable
fun LoginPage() {

    val image = painterResource(id = R.drawable.login_image2)

    var emailValue by remember {mutableStateOf("")}
    var passwordValue by remember { mutableStateOf("")}

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
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
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.13f),
                value = emailValue,
                onValueChange = {emailValue = it},
                label = null,
                placeholder = { Text(text = "Email adresse")},
                shape = RoundedCornerShape(50)

            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.13f),
                value = passwordValue,
                onValueChange = {passwordValue = it},
                label = null,
                placeholder = { Text(text = "Adgangskode")},
                shape = RoundedCornerShape(50)

            )

            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.13f),
                shape = RoundedCornerShape(50)

            ) {
               Text(text = "Fortsæt >")

            }

        }
    }
}