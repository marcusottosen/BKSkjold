package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.Buttons
import com.example.bkskjold.ui.view.reusables.Title

@Composable
fun OldAuthenticationView(register: () -> Unit, login: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Title(title = "NemSport")
            Buttons(title = "Register", onClick = register, backgroundColor = colorResource(R.color.primary))
            Buttons(title = "Login", onClick = login, backgroundColor = colorResource(R.color.primary_light))
        }
    }
}

@Composable
fun AuthenticationView(register: () -> Unit, login: () -> Unit) {
    val frontLoginImage = painterResource(id = R.drawable.login_image)
    val frontBgColor = Brush.verticalGradient(
        listOf(colorResource(R.color.light_green), colorResource(R.color.primary)),
        startY = 300.0f,
        endY = 1500.0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(frontBgColor)
            .padding(20.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.0f)
                .background(Color.Transparent)
        )
        {

            Image(
                frontLoginImage,
                contentDescription = "login_image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(250.dp, 250.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "NemSport",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Den nemmeste måde at holde styr",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "på dine træninger",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(42.dp))

            Button(
                onClick = {/*TODO: Link to website*/},
                modifier = Modifier
                    .wrapContentWidth()
                    .height(48.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                )) {
                Text(
                    text = "Tilmeld dig klubben her",
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center

                )
            }

            Button(
                onClick = register,
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(50),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )


            ) {
                Text(
                    text = "Opret bruger",
                    color = colorResource(R.color.primary),
                    fontSize = 14.sp
                )


            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = login,
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
                    text = "Log ind",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

