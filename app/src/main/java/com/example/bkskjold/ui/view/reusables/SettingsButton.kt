package com.example.bkskjold.ui.view.reusables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {onClick},
           // shape = RoundedCornerShape(18.dp),

        ) {
            Icon(
                painterResource(id = image),
                contentDescription = null,

            )
            Text( modifier = Modifier
                .align(Alignment.CenterVertically),
                text = description,
                color = colorResource(id =R.color.primary_light))


                    // Icon(painterResource(id = R.drawable.icon_forward_arrow_head),
                  //  contentDescription = null, )
        }

        }
    }

