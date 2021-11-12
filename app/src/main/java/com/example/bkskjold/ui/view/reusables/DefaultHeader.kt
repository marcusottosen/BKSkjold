package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color as Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun DefaultHeader(header: String){
    //var color = colorResource(id = R.color.main_background)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            /*.background(
                brush = Brush.horizontalGradient(
                    listOf(color, color, color),
                    startX = 10.0f,
                    endX = 20.0f),
                alpha = 0F
            )*/
    ) {
        Image(painter = painterResource(id = R.drawable.header_background), contentDescription = null)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 60.dp, 0.dp, 0.dp),
            text = header,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.main_background)
        )
    }
}