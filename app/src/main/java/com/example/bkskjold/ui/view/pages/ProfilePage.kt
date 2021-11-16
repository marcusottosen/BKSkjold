package com.example.bkskjold.ui.view.pages
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.*

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.DefaultHeader
import com.example.bkskjold.ui.view.reusables.DefaultProfileHeader
import com.example.bkskjold.ui.view.reusables.NextTrainingCard
import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel

//class ProfilePage { }
@Preview(showBackground = true)
@Composable
fun profileOverview() {
    Column (verticalArrangement = Arrangement.spacedBy(30.dp)) {
        DefaultProfileHeader()
       ProfileOverviewViewModel().getProfileView()
        NextTrainingCard(header = "Træning for seniorer", description = "Holdtræning", date = "25. Oktober 2021", time = "17:45", location = "Bane C")


    }
}





