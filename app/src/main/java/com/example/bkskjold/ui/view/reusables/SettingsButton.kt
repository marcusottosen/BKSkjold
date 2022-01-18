package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

@Composable
fun SettingsButton(
    image: Int,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = { onClick },
    ) {
        Box(modifier = Modifier.align(CenterVertically)) {
            Icon(
                painterResource(id = image),
                tint = Black,
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = description,
            color = colorResource(id = R.color.primary_light)
        )
    }
}