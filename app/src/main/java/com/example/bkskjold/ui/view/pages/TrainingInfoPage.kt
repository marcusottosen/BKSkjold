package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bkskjold.data.model.PeopleSource
import com.example.bkskjold.data.model.Training


@Composable
fun trainingInfoPage(training: Training, navController: NavController) {

    Column(verticalArrangement = Arrangement.spacedBy(30.dp), modifier = Modifier.padding(horizontal = 30.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)) {
            Text(text = stringResource(id = R.string.TrainingInfoPageHeader) + " d. " + training.date)
        }

        Column() {
            Row(Modifier.padding(bottom = 10.dp)) {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = "hold",
                    Modifier.padding(end = 10.dp)
                )
                Text(text = "U13" + "-" + stringResource(R.string.TeamTraining))
            }
            Row(Modifier.padding(bottom = 10.dp)) {
                Icon(
                    Icons.Outlined.Place,
                    contentDescription = "lokation",
                    Modifier.padding(end = 10.dp)
                )
                Text(text = training.location)
            }
            Row(Modifier.padding(bottom = 10.dp)) {
                Icon(
                    Icons.Outlined.DateRange,
                    contentDescription = "tidspunkt",
                    Modifier.padding(end = 10.dp)
                )
                Text(text = training.timeStart + " - " + training.date)
            }
            Row() {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = "pris",
                    Modifier.padding(end = 10.dp)
                )
                Text(text = stringResource(R.string.TrainingPrice, "20", "0"))
            }
        }



        Column() {
            // TODO data should be fetched from a database
            Text(text = "*En lang og fyldig beskrivelse af træningen med lækre kampinformationer og sans for detaljen*")
        }



        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Tilmeldte deltagere")
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Navn")
                Text(text = "Tlfnr.")
            }

            LazyColumn(Modifier.height(200.dp)){
                items(PeopleSource.peopleSource.size) { i ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.5.dp).padding(horizontal = 30.dp), Arrangement.SpaceBetween){
                        if (PeopleSource.peopleSource[i].team != "guest"){
                            Text(
                                text = PeopleSource.peopleSource[i].name,
                                Modifier.padding(vertical = 2.5.dp)
                            )
                        } else {
                            Text(
                                text = PeopleSource.peopleSource[i].name + " (Gæst)",
                                Modifier.padding(vertical = 2.5.dp)
                            )
                        }
                        Text(
                            text = PeopleSource.peopleSource[i].phoneNumber.toString(),
                            Modifier.padding(vertical = 2.5.dp)
                        )


                    }
                }
            }



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth().padding(vertical = 30.dp)
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(R.color.red)
                    ),
                ) {
                    Row() {
                        Icon(
                            Icons.Outlined.Clear,
                            contentDescription = "afmeld træning",
                            Modifier.padding(end = 10.dp),
                            colorResource(id = R.color.main_background)
                        )
                        Text(
                            text = "Afmeld Deltagelse",
                            color = colorResource(id = R.color.main_background)
                        )
                    }
                }
            }
        }
    }
}