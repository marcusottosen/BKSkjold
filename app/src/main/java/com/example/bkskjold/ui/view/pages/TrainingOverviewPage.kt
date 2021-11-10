package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        val shouldShowDialog = remember { mutableStateOf(true) }
        val selected = remember { mutableStateOf( true) }

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
                    val overViewButton = Button(
                        onClick = {
                            shouldShowDialog.value = true
                            selected.value = !selected.value
                                  },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                                if(selected.value) colorResource(R.color.top_header)
                                else colorResource(R.color.top_header_light)),

                        ){
                        Text(text = "Alle Træninger", color = colorResource(id = R.color.main_background))
                    }
                    val signedUpButton = Button(
                        onClick = { 
                            shouldShowDialog.value = false 
                            selected.value = !selected.value
                                  },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                                if(selected.value) colorResource(R.color.top_header_light)
                                else colorResource(R.color.top_header)),
                        ) {
                        Text(text = "Tilmeldte Træninger", color = colorResource(id = R.color.main_background))
                    }
                }
            if(shouldShowDialog.value){
                showOverview()
            }else{
                showSignedUp()
            }
             /*
            TODO : This is a simplistic way of displaying an example view
                   Eventually this shoould get the viewmodel from TrainingOverviewViewModel
                   which in turn fetches data from BookingData in model.

             */
        }
    }


/* TODO These clases should eventually be either removed or altered,
    to fetch the ViewModel with the correct data */
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
            items(3) { index ->
                TrainingCard()
            }
        }
    }
}