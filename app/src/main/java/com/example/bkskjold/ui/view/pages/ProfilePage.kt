package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.data.model.InvitationData
import com.example.bkskjold.data.model.User
import com.example.bkskjold.data.model.users
import com.example.bkskjold.ui.view.reusables.DefaultProfileHeader
import com.example.bkskjold.ui.view.reusables.InvitationCard
import com.example.bkskjold.ui.view.reusables.NextTrainingCard
import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel

//class ProfilePage { }
@Composable
fun profileOverview(navController: NavController) {

    val currentUser = users[3] //TODO Skal bruge den user der er logget ind! Lav function i UserModel.kt

    LazyColumn(
        //verticalArrangement = Arrangement.spacedBy(30.dp)
    modifier = Modifier.fillMaxSize()

    ) {
        item {
            DefaultProfileHeader(currentUser, navController)
        }
        item { ProfileOverviewViewModel().GetProfileView(currentUser)
            Spacer(modifier = Modifier.height(30.dp))}
        // item {ProfileOverviewViewModel().getProfileInvitationView()}
        val inviInfo = InvitationData().getInvitations()

       item { Text(modifier = Modifier
           .fillMaxWidth(),
               text = "Invitationer",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
           fontWeight = FontWeight.Bold
        ) }

        item {
            LazyColumn(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                ) {

                items(inviInfo.size)  { i ->
                    InvitationCard(
                        inviInfo[i][0],
                        inviInfo[i][1],
                        inviInfo[i][2]
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }

        //TODO Skift til at bruge info fra nuværende bruger. Bør bare ændre i NextTrainingCard og overføre nuværende user
        item {
            NextTrainingCard(
                header = "Træning for seniorer",
                date = "25. oktober",
                timeStart = "16:00",
                timeEnd = "17:30",
                day = "mandag",
                team = "U12",
                location = "Bane C",
                attending = 8,
                spots = 12,
                trainer = "Bjarne Andersen"
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        item() {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

fun gotoSettingsPage(user: User, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("user", user)
    navController.navigate("settingsInfo")
}





