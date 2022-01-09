package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.Buttons
import com.example.bkskjold.ui.view.reusables.Title
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// Forsøg på at gøre så man ikke kan komme tilbage til login-skærm med tilbageknappen
class Action(navController: NavHostController) {
    val home: () -> Unit = {
        navController.navigate("mainScreen") {
            popUpTo("login") {
                inclusive = true
            }
            popUpTo("register") {
                inclusive = true
            }
        }
    }
    val login: () -> Unit = { navController.navigate("login") }
    val register: () -> Unit = { navController.navigate("register") }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}

@Composable
fun AuthenticationView(register: () -> Unit, login: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Title(title = "NemSport")
            Buttons(title = "Register", onClick = register, backgroundColor = colorResource(R.color.primary))
            Buttons(title = "Login", onClick = login, backgroundColor = colorResource(R.color.primary_light))
        }
    }
}

fun logoutUser(navController: NavController){
    FirebaseAuth.getInstance().signOut()
    navController.navigate("mainScreen")
}