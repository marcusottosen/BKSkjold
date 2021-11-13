package com.example.bkskjold.data.model
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("Hjem", R.drawable.icon_home, "Hjem")
    object Trainings : NavigationItem("Træninger", R.drawable.icon_trainings, "Træninger")
    object Events : NavigationItem("Events", R.drawable.icon_events, "Events")
    object Profile : NavigationItem("Profil", R.drawable.icon_profile, "Profil")
}


