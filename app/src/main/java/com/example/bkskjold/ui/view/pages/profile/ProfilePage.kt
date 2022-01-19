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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.InvitationData
import com.example.bkskjold.data.model.NavigationRoute
import com.example.bkskjold.ui.view.reusables.InvitationCard
import com.example.bkskjold.ui.view.reusables.ProfileHeader
import com.example.bkskjold.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileOverview(navController: NavController) {
    val inviInfo = InvitationData().getInvitations()
    val viewModel = ProfileViewModel()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            ProfileHeader(viewModel.currentUser, navController)
        }
        item {
            viewModel.GetProfileView()
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
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
                        text = stringResource(R.string.FAQs),
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        item {
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = stringResource(R.string.Invites),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Column(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                for (i in inviInfo.indices) {
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

fun gotoSettingsPage(navController: NavController) {
    navController.navigate(NavigationRoute.SettingsPage.route)
}

fun gotoEditProfilePage(navController: NavController) {
    navController.navigate(NavigationRoute.EditProfile.route)
}

fun gotoFaqPage(navController: NavController) {
    navController.navigate(NavigationRoute.FaqPage.route)
}