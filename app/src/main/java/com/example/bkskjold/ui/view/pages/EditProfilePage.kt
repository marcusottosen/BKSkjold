package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bkskjold.data.model.users
import com.example.bkskjold.ui.view.reusables.DefaultEditProfileHeader
@Composable
fun editProfilePage(navController: NavController) {
    val currentUser = users[3]
    Column {
        DefaultEditProfileHeader(currentUser, navController)
    }
}