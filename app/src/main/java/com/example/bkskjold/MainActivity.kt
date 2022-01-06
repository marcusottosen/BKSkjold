package com.example.bkskjold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.data.model.loadTrainingsFromDB
import com.example.bkskjold.ui.viewmodel.BottomNavigationBar
import com.example.bkskjold.ui.viewmodel.Navigation

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loadTrainingsFromDB()
            MainScreen()
        }
    }
}




@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController = navController)
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
    //NewsCard()
    //TrainingCard()
}


//TODO fjern når siderne er blevet oprettet!
@Composable
fun DefaultScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.notParticipating))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Todo",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun DefaultDetails(training: Training, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.notParticipating))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = training.description,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}