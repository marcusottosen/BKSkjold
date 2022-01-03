package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun HomePageCategories(iconLink: Int, title: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.border)),
        modifier = Modifier.size(width = 77.dp, height = 93.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconLink),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.padding(top = 10.dp).height(35.dp),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontWeight =  FontWeight.SemiBold
            )
        }
    }
}