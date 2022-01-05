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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun LoginPage() {

    val loginImage = painterResource(id = R.drawable.login_image2)
    val bgColor = Brush.verticalGradient(listOf(colorResource(R.color.primary), colorResource(R.color.login_green)),
    startY = 0.0f,
    endY = 1000.0f)

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)

    ) {

        Row(modifier = Modifier.padding(40.dp), horizontalArrangement = Arrangement.Start){
            Text(
                text = "<",
                fontSize =28.sp,
                color = Color.Black)
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
                color = Color.White
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                modifier = Modifier
                    .width(300.dp)
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
                    .width(300.dp)
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
                    .width(300.dp)
                    .height(60.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(50)),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                )


            ) {
                Text(
                    text = "Fortsæt >",
                    color = Color.White
                )


            }


        }

    }

}