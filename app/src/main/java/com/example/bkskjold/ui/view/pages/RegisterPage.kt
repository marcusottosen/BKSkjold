package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

@Composable
fun RegisterPage() {

    val registerBgColor = Brush.verticalGradient(
        listOf(colorResource(R.color.primary), colorResource(R.color.light_green)),
        startY = 0.0f,
        endY = 1000.0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(registerBgColor)
            .padding(20.dp)

    ) {

    }
}