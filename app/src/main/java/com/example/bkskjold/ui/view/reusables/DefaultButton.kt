package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R

@Composable
fun DefaultButton (
    text: String,
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            onClick = {onClick},
            shape = RoundedCornerShape(18.dp),
            colors= ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
        ) {
            Text(
                text = text,
                color = colorResource(id =R.color.primary_light))

        }
    }
}