package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.*

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource


//resources and files
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.DefaultHeader
import com.example.bkskjold.ui.viewmodel.TrainingOverviewViewModel


//class TrainingOverviewPage {
    @Preview(showBackground = true)
    @Composable
    fun trainingOverview() {
        val shouldShowOverview = remember { mutableStateOf(true) }

        val viewModel = TrainingOverviewViewModel()

        Column (verticalArrangement = Arrangement.spacedBy(30.dp)){
            DefaultHeader(header = stringResource(id = R.string.TrainingOverviewPageHeader))
            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    val overViewButton = Button(
                        onClick = {
                            shouldShowOverview.value = true
                                  },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                                if(shouldShowOverview.value) colorResource(R.color.top_header)
                                else colorResource(R.color.top_header_light)),

                        ){
                        Text(text = "Alle Træninger", color = colorResource(id = R.color.main_background))
                    }
                    val signedUpButton = Button(
                        onClick = {
                            shouldShowOverview.value = false
                                  },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                                if(shouldShowOverview.value) colorResource(R.color.top_header_light)
                                else colorResource(R.color.top_header)),
                        ) {
                        Text(text = "Tilmeldte Træninger", color = colorResource(id = R.color.main_background))
                    }
                }
            if(shouldShowOverview.value){
                viewModel.getOverviewView()
            }else {
                viewModel.getSignedUpView()
            }
        }
    }
//}