package com.example.bkskjold.ui.viewmodel;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R
import com.example.bkskjold.data.model.ProfileInfo
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.data.model.firebaseAdapter.events
import com.example.bkskjold.data.util.getDayMonth
import com.example.bkskjold.data.util.getYear
import com.example.bkskjold.ui.view.reusables.InvitationCard

class ProfileOverviewViewModel {

    @Composable
    fun GetProfileView() {
        Column(modifier = Modifier.padding(start = 45.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "${stringResource(R.string.Membership)} ${translateMemberType(CurrentUser.userType)}")
            Text(text = "${stringResource(R.string.FinshedTrainings)} ${CurrentUser.finishedTrainings}")
            Text(text = "${stringResource(R.string.MemberSince)} ${getDayMonth(CurrentUser.memberSince)} - ${
                getYear(CurrentUser.memberSince)}")
            Text(text = "${stringResource(R.string.TeamJoined)} ${CurrentUser.team}")
        }
    }

    private fun translateMemberType(type: Int): String {
        val translated = when (type) {
            1 -> "Gæstebruger"
            2 -> "Standard medlem - 99 kr./md."
            3 -> "Træner"
            else -> {
                "Brugertype ikke fundet"
            }
        }
        return translated
    }
}