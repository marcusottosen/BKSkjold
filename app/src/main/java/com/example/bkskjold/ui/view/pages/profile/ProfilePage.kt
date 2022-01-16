package com.example.bkskjold.ui.view.pages.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.data.model.InvitationData
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.data.model.dataClass.User
import com.example.bkskjold.data.model.firebaseAdapter.users
import com.example.bkskjold.ui.view.reusables.DefaultProfileHeader
import com.example.bkskjold.ui.view.reusables.InvitationCard
import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel

@Composable
fun profileOverview(navController: NavController) {

    val currentUser = users[1] //TODO Få CurrentUser igennem en ViewModel!

    LazyColumn(
        //verticalArrangement = Arrangement.spacedBy(30.dp)
        modifier = Modifier.fillMaxSize()

    ) {
        item {
            DefaultProfileHeader(currentUser,navController)
        }
        item { ProfileOverviewViewModel().GetProfileView()
            Spacer(modifier = Modifier.height(30.dp))}
        // item {ProfileOverviewViewModel().getProfileInvitationView()}
        val inviInfo = InvitationData().getInvitations()

        item {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedButton(
                    onClick = { gotoFaqPage(navController) },
                    modifier = Modifier
                        .width(340.dp)
                        .height(60.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(50)),
                    shape = RoundedCornerShape(50),
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )

                ) {
                    Text(
                        text = "FAQs",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }

            }
        }

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
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

fun gotoSettingsPage(user: User, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("user", user)
    navController.navigate(NavigationRoute.SettingsPage.route)
}
fun gotoEditProfilePage(user: User, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("user", user)
    navController.navigate(NavigationRoute.EditProfile.route)
}
fun gotoFaqPage(navController: NavController){
    navController.navigate(NavigationRoute.FaqPage.route)
}