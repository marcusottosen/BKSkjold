package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.SettingsButton


@Composable
fun settingsPage(navController: NavController) {
    Column (
        modifier = Modifier
            .background(color = colorResource(R.color.main_background))
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .background(colorResource(id = R.color.primary))
            .height(80.dp)
            .fillMaxWidth())
        {
            Row(){

                IconButton( modifier = Modifier.fillMaxHeight(),
                    onClick = { navController.navigateUp() }

                ){
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        tint = Color.White,
                        contentDescription = "return arrow",
                    )
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp),
                    text = "Indstillinger",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp,
                    color = Color.White,
                )
            }}

        SettingsButton(description = "Abonnement",
            image = R.drawable.icon_subscription,
            onClick = { /*TODO*/},


            )
        SettingsButton(description = "Notifikationer",
            image = R.drawable.icon_notification_bell,
            onClick = { /*TODO*/},


            )

        SettingsButton(description = "Privatliv og Sikkerhed",
            image = R.drawable.icon_privacy,
            onClick = { /*TODO*/},


            )

        SettingsButton(description = "Omkring BKSkjold",
            image = R.drawable.icon_about_us,
            onClick = { /*TODO*/},


            )

        SettingsButton(description = "Kontakt Os",
            image = R.drawable.icon_contact_us,
            onClick = { /*TODO*/},


            )

    }
}