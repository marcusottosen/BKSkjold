package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

@Composable
fun InvitationCard(name: String, date: String, location: String) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .width(300.dp),
        backgroundColor = colorResource(R.color.green),
        shape = RoundedCornerShape(22.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp, 15.dp, 20.dp, 15.dp)
        ) {
            Text(// event header
                text = name,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
            )
            Text( // event description
                text = date,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                color = Color.White
            )

            Text( // event description
                text = location,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                color = Color.White
            )
        }
    }
}