package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.*

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview as p
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController


//resources and files
import com.example.bkskjold.R
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.ui.viewmodel.TrainingOverviewViewModel


//class TrainingOverviewPage {
@p
@Composable
fun trainingOverview(navController: NavController) {
    val shouldShowOverview = remember { mutableStateOf(true) }

    val viewModel = TrainingOverviewViewModel()

    Column (
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            val overViewButton = Button(
                onClick = { shouldShowOverview.value = true },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if(shouldShowOverview.value) colorResource(R.color.primary)
                    else colorResource(R.color.primary_light)),

                ){
                Text(text = "Alle Træninger", color = colorResource(id = R.color.main_background))
            }
            val signedUpButton = Button(
                onClick = { shouldShowOverview.value = false },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if(shouldShowOverview.value) colorResource(R.color.primary_light)
                    else colorResource(R.color.primary)),
            ) {
                Text(text = "Tilmeldte Træninger", color = colorResource(id = R.color.main_background))
            }
        }
        Column(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .padding(end = 20.dp)
            , verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.End

        ) {
            Button(
                onClick = {}
                , colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.main_background))
                , modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.icon_filter)
                    , contentDescription = null
                    , modifier = Modifier.height(30.dp).width(30.dp)
                )

            }
        }
        if(shouldShowOverview.value){
            viewModel.GetOverviewView(navController)
        }else {
            viewModel.GetSignedUpView(navController)
        }
    }
}

fun gotoTrainingDetails(training: Training, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("training", training)
    navController.navigate("trainingDetails")
}
