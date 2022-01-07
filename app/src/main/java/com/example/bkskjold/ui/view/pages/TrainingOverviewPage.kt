package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.unit.*

import androidx.compose.ui.tooling.preview.Preview as p
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
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
    val showFilterOptions = remember { mutableStateOf(true) }

    //main filter menu variables
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Dato", "Tidspunkt")
    var selectedIndex by remember { mutableStateOf(0) }

    //Tidspunkt filter menu variables
    var expandedTidspunkt by remember { mutableStateOf(false) }
    val times = listOf("17:00", "18:00")

    //passed filter values
    var tidspunkt = remember { "0" }

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

        //filter button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
            , verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.End

        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_filter)
                , contentDescription = null
                , modifier = Modifier
                    .width(60.dp)
                    .height(30.dp)
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.primary_light))
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                        , horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        items.forEachIndexed { index, t ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedIndex = index
                                    //expanded = false
                                    if(t == "Tidspunkt"){expandedTidspunkt = !expandedTidspunkt}
                            }
                            , modifier = Modifier
                                .width(150.dp)
                        ) {
                            Text(text = t)
                            if(t == "Tidspunkt"){
                                }
                            }
                        }
                    }
                    DropdownMenu(expanded = expandedTidspunkt, onDismissRequest = { expandedTidspunkt = false }) {
                        DropdownMenuItem(
                            onClick = { expandedTidspunkt = false }
                            , modifier = Modifier
                        ) {
                            times.forEachIndexed { index, time ->
                                Text(
                                    text = time
                                    , modifier = Modifier
                                        .clickable { tidspunkt = time }
                                )
                            }
                        }
                    }
                }
            }
        }

        //if(showFilterOptions.value){viewModel.filterMenu()} else{viewModel.filterMenu()}

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
