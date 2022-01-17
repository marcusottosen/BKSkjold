package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun MapPage() {

    val map = painterResource(id = R.drawable.banekort)

    Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )

        }
    }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1.0f)
            .background(Color.Transparent)
    )
    {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(24.dp)
            ){
                Text(
                text = "FS Banekort: Efterår 2021 - tfr",
                color = Color.Black,
                fontSize = 18.sp
            )

            }

        }
    }
}