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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.bkskjold.data.model.PeopleSource
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.data.model.*
import com.example.bkskjold.data.util.Util


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
                Text(text = training.league + "-" + stringResource(R.string.TeamTraining)
                    + " (" + stringResource(R.string.Trainer) + " : " + training.trainer+ ")"
                )
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
                Text(text = training.timeStart + " - " + training.timeEnd + " d. " + training.date)
            }
            Row(Modifier.padding(bottom = 10.dp)) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "deltagere",
                    Modifier.padding(end = 10.dp)
                )
                Text(text = (training.team1 + training.team2).toString() + "/" + training.maxParticipants + " " + stringResource(R.string.participating))
            }
            Row() {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = "pris",
                    Modifier.padding(end = 10.dp)
                )
                Text(text = stringResource(R.string.TrainingPrice, training.price, "0"))
            }
        }


        Column() {
            Text(text = stringResource(R.string.TraningDescription))
            // TODO data should be fetched from a database
            Text(text = training.description)
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Tilmeldte deltagere")
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Navn")
                Text(text = "Tlfnr.")
            }
            LazyColumn(Modifier.height(200.dp)){
                val participants = getUsersFromId(training.participants)
                items(participants.size) { i ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.5.dp)
                        .padding(horizontal = 30.dp), Arrangement.SpaceBetween){
                        if (participants[i].team != "guest"){
                            Text(
                                text = participants[i].surname,
                                Modifier.padding(vertical = 2.5.dp)
                            )
                        } else {
                            Text(
                                text = participants[i].surname + " (Gæst)",
                                Modifier.padding(vertical = 2.5.dp)
                            )
                        }
                        Text(
                            text = participants[i].phoneNumber.toString()
                                .chunked(2).joinToString(separator = " "),
                            Modifier.padding(vertical = 2.5.dp)
                        )
                    }
                }
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp)
            ) {

                val buttonColor: Color
                val buttonText: String
                val buttonIcon: ImageVector
                if (training.attending) {
                    buttonColor = colorResource(R.color.red)
                    buttonText = stringResource(R.string.Unattend)
                    buttonIcon = Icons.Outlined.Clear
                } else {
                    buttonColor = colorResource(R.color.green)
                    buttonText = stringResource(R.string.Attend)
                    buttonIcon = Icons.Outlined.Check
                }

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor
                    ),
                ) {
                    Row() {
                        Icon(
                            buttonIcon,
                            contentDescription = "tilmeld/afmeld træning",
                            Modifier.padding(end = 10.dp),
                            colorResource(id = R.color.main_background)
                        )
                        Text(
                            text = buttonText,
                            color = colorResource(id = R.color.main_background)
                        )
                    }
                }
            }
        }
    }
}