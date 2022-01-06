package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun LoginFrontPage() {
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

            Text(
                text = "NemSport",
                fontSize = 32.sp,
                color = Color.White
            )

            Text(
                text = "Den nemmeste måde at holde styr på dine træninger!",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }
    }
}