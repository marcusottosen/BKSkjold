package com.example.bkskjold.ui.view.pages

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.bkskjold.data.model.NavigationItem
import com.example.bkskjold.ui.viewmodel.TrainingOverviewViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CalendarPage(navController: NavController) {
    val viewModel = TrainingOverviewViewModel()

    val sdf = SimpleDateFormat("dd/M")
    val currentDate = sdf.format(Date())
    val date = remember { mutableStateOf(currentDate.toString()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(start = 25.dp, top = 35.dp)
        ) {

            IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.0f)
        )
        {
            AndroidView(
                { CalendarView(it) },
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.DarkGray),
                update = { views ->
                    views.setOnDateChangeListener { _, _, month, day ->
                        val monthShifted = month + 1
                        date.value = "$day/$monthShifted"
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            viewModel.GetOverviewView(navController,
                date = date.value,
                timeStart = "",
                team = "")
        }
    }
}