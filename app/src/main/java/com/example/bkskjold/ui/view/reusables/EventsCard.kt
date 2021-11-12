package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R


@Composable
fun EventsCard(header: String, description: String, time: String, location: String) {
    Card(
        modifier = Modifier
            .padding(15.dp),
            //.height(200.dp),
        backgroundColor = colorResource(R.color.default_button_background),
        shape = RoundedCornerShape(22.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp, 15.dp, 20.dp, 15.dp)
        ) {
            Text(
                text = header,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp,0.dp,0.dp, 15.dp)
            )
            //Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                modifier = Modifier.padding(0.dp,0.dp,0.dp, 5.dp),
                color = Color.DarkGray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 35.dp, 0.dp, 0.dp)
                ,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = time, fontWeight = FontWeight.Bold)
                Text(text = location, fontWeight = FontWeight.Bold)
            }
        }
    }
}