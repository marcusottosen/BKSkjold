package com.example.bkskjold.ui.view.pages
import android.graphics.Paint
import android.text.Layout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.*

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.bkskjold.R
import com.example.bkskjold.data.model.InvitationData
import com.example.bkskjold.ui.view.reusables.DefaultHeader
import com.example.bkskjold.ui.view.reusables.DefaultProfileHeader
import com.example.bkskjold.ui.view.reusables.InvitationCard
import com.example.bkskjold.ui.view.reusables.NextTrainingCard
import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel

//class ProfilePage { }
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun ProfileOverview() {
    LazyColumn(
        //verticalArrangement = Arrangement.spacedBy(30.dp)

    ) {
        item {
            DefaultProfileHeader()
            Spacer(modifier = Modifier.height(30.dp))
        }
        item { ProfileOverviewViewModel().getProfileView()
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

    }
}





