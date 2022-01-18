package com.example.bkskjold.ui.viewmodel

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

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

fun logoutUser(navController: NavController) {
    FirebaseAuth.getInstance().signOut()
    navController.navigate("mainScreen")
}