package com.example.bkskjold.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun NewsCard() { //TODO: headerText:String, spoilerText: String
    Card(
        modifier = Modifier.padding(15.dp).height(150.dp),
        backgroundColor = Color(212, 242, 191),
        shape = RoundedCornerShape(22.dp),
    ) {
        Column {
            Text(
                text = "Something Something",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp,10.dp,5.dp)
            )
            //Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Something mod something vinder something efter something!",
                modifier = Modifier.padding(10.dp,0.dp,10.dp),
                color = Color.DarkGray
            )
        }
    }
}