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
        verticalArrangement = Arrangement.spacedBy(30.dp)


    ) {
        item {
            DefaultProfileHeader()
        }
        item { ProfileOverviewViewModel().getProfileView() }
        // item {ProfileOverviewViewModel().getProfileInvitationView()}
        val inviInfo = InvitationData().getInvitations()

       item { Text(modifier = Modifier
           .fillMaxWidth(),
               text = "Invitationer",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        ) }

        //PaddingValues(50.dp)
        item {
            LazyColumn(
                modifier = Modifier.height(280.dp)
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
        }
        item {
            NextTrainingCard(
                header = "Træning for seniorer",
                description = "Holdtræning",
                date = "25. Oktober 2021",
                time = "17:45",
                location = "Bane C"
            )
        }

    }
}





