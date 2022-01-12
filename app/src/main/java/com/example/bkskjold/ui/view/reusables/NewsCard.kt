package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R
import com.example.bkskjold.data.model.News
import com.example.bkskjold.data.util.getDay
import com.example.bkskjold.data.util.getDayMonth
import com.example.bkskjold.data.util.getMonthString
import com.example.bkskjold.data.util.getYear


@Composable
fun NewsCard(news: News) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .height(150.dp),
        //backgroundColor = Color(212, 242, 191),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.border)),
        elevation = 3.dp
    ) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "${getDay(news.date)}. ${getMonthString(news.date)} ${getYear(news.date)}",
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(0.dp, 5.dp, 20.dp, 0.dp).fillMaxWidth(),
                color = Color.Gray,
                fontSize = 10.sp
            )

                Text(
                    text = news.header,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 0.dp, 5.dp, 0.dp)
                )
                //Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = news.description,
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp),
                    color = Color.DarkGray
                )
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.background(colorResource(id = R.color.primary))
                        .height(5.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)

                ) {}
            }
        }
    }
}