package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight


//resources and files
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.TrainingCard


class TrainingOverviewPage {
    @Preview(showBackground = true)
    @Composable
    fun trainingOverview() {
        Column (verticalArrangement = Arrangement.spacedBy(30.dp)){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.top_header),
                        shape = AbsoluteRoundedCornerShape(0.dp, 0.dp, 100.dp, 100.dp)
                    )
                    .padding(5.dp, 30.dp, 5.dp, 30.dp)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    text = stringResource(R.string.TrainingOverviewPageHeader),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.main_background)
                )
            }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.top_header)),

                        ){
                        Text(text = "Alle Træninger", color = colorResource(id = R.color.main_background))
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.top_header_light)),
                        ) {
                        Text(text = "Tilmeldte Træninger", color = colorResource(id = R.color.main_background))
                    }
                }


            showOverview()
        }
    }

    @Composable
    fun showOverview(){
        LazyColumn {
            // Add 15 items
            items(15) { index ->
                TrainingCard()
            }
        }
    }

    @Composable
    fun showSignedUp(){
        LazyColumn {
            // Add 15 items
            items(5) { index ->
                TrainingCard()
            }
        }
    }
}