package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun LoginPage() {

    val loginImage = painterResource(id = R.drawable.login_image2)

    var emailValue by remember {mutableStateOf("")}
    var passwordValue by remember { mutableStateOf("")}

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent), contentAlignment = Alignment.TopCenter
        ) {Image(painter = painterResource(id = R.drawable.login_bg_gradient),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {

            Image(
                loginImage,
                contentDescription = "login_image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(300.dp, 300.dp))

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .background(Color.Transparent)
        ) {
            Text(
                text = "Velkommen tilbage!",
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = "log ind",
                fontSize = 32.sp,
                color = Color.White
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = emailValue,
                onValueChange = {emailValue = it},
                label = null,
                placeholder = { Text(text = "Email")},
                shape = RoundedCornerShape(50)

            )
            OutlinedTextField(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = passwordValue,
                onValueChange = {passwordValue = it},
                label = null,
                placeholder = { Text(text = "Adgangskode")},
                shape = RoundedCornerShape(50)

            )

            Button(onClick = {},
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .border(2.dp, Color.White, RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent

                )


            ) {
               Text(text = "Fortsæt >")

            }

        }
    }
}