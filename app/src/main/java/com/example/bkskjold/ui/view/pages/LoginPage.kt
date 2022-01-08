package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun LoginPage() {

    val loginImage = painterResource(id = R.drawable.login_image2)
    val backButton = painterResource(id = R.drawable.back_white)
    val bgColor = Brush.verticalGradient(listOf(colorResource(R.color.primary), colorResource(R.color.light_green)),
    startY = 0.0f,
    endY = 1000.0f)

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(20.dp)

    ) {

        Row(modifier = Modifier.padding(24.dp), horizontalArrangement = Arrangement.Start) {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(75.dp)
                    .height(50.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                )


            ) {
                Image(
                    backButton,
                    contentDescription = "backButton",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                )
        }
    }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.0f)
                .background(Color.Transparent)
        )
        {

            Image(
                loginImage,
                contentDescription = "login_image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(300.dp, 250.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Velkommen tilbage!",
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = "log ind",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = emailValue,
                onValueChange = { emailValue = it },
                label = null,
                placeholder = { Text(text = "Email") },
                shape = RoundedCornerShape(50)

            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = null,
                placeholder = { Text(text = "Adgangskode") },
                shape = RoundedCornerShape(50)

            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(50)),
                shape = RoundedCornerShape(50),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                )


            ) {
                Text(
                    text = "Fortsæt >",
                    color = Color.White,
                    fontSize = 14.sp
                )


            }


        }

    }

}