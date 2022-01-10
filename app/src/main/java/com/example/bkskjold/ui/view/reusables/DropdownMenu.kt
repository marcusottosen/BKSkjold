package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun dropDownMenu(items: MutableList<String>, menuWidth: Int): String {
    var itemList: String by remember { mutableStateOf(items[0]) }
    var expanded by remember { mutableStateOf(false) }


    Box(Modifier
        .width(menuWidth.dp)
        .clip(RoundedCornerShape(12.dp))
        .border(BorderStroke(1.dp, colorResource(id = R.color.border)),
            shape = RoundedCornerShape(12.dp))
        .background(Color.White)
        ,contentAlignment = Alignment.Center
    ){
        Row(
            Modifier
                .padding(0.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = itemList,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 10.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, tint = colorResource(
                id = R.color.primary),
                contentDescription = "dropdown")


            DropdownMenu(expanded = expanded,modifier = Modifier.height(200.dp), onDismissRequest = {
                expanded = false
            }) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        itemList = item
                    }) {
                        Text(text = item)
                    }
                }
            }
        }
    }
    return itemList
}