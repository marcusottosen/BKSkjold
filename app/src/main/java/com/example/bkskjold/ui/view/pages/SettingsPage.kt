package com.example.bkskjold.ui.view.pages

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.DefaultButton
import com.example.bkskjold.ui.view.reusables.SettingsButton

@Preview
@Composable
fun SettingsPage() {
    Column (
        modifier = Modifier
            .background(color = colorResource(R.color.main_background)),
    ) {
        Box(modifier = Modifier
            .background(colorResource(id = R.color.primary))
            .height(80.dp)
            .fillMaxWidth())
        {
            Row(){

                IconButton( modifier = Modifier.fillMaxHeight(),
                    onClick = { /*TODO*/ }

                ){
                    Icon(
                        painterResource(id = R.drawable.icon_back_arrow_head),
                        contentDescription = null,
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

        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
            onClick = { /*TODO*/ }) {
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = "Abonnement",
                textAlign = TextAlign.Start ,
            color = colorResource(id =R.color.primary_light))
        }
        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
            onClick = { /*TODO*/ }) {
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = "Notifikationer",
                textAlign = TextAlign.Start ,
            color = colorResource(id =R.color.primary_light))
        }

        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
            onClick = { /*TODO*/ }) {
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = "Privatliv og Sikkerhed",
                textAlign = TextAlign.Start ,
            color = colorResource(id =R.color.primary_light))
        }

        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
            onClick = { /*TODO*/ }) {
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = "Omkring BKSkjold",
                textAlign = TextAlign.Start ,
                color = colorResource(id =R.color.primary_light))
        }

        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
            onClick = { /*TODO*/ }) {
            Text(
                text = "Kontakt Os",
                textAlign = TextAlign.Start ,
                color = colorResource(id =R.color.primary_light))
            Icon(painterResource(id = R.drawable.icon_edit_pencil),
                  contentDescription = null, )
        }
        SettingsButton(description = "boingboing",
            image = R.drawable.icon_notification_bell,
            onClick = { /*TODO*/},


        )
    }
}