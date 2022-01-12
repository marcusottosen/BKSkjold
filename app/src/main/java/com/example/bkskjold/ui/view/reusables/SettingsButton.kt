package com.example.bkskjold.ui.view.reusables

import android.bluetooth.BluetoothHealthAppConfiguration
import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R


@Composable
fun SettingsButton (
    image: Int,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)

    {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {onClick},
           // shape = RoundedCornerShape(18.dp),

        ) {
            Box(modifier = Modifier.align(CenterVertically)){


                Icon(
                    painterResource(id = image),
                    tint = Black,
                    contentDescription = null
                )}

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = description,
                    color = colorResource(id = R.color.primary_light)
                )





        }
    }

