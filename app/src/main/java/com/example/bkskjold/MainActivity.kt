package com.example.bkskjold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bkskjold.ui.theme.BKSkjoldTheme
import com.example.bkskjold.ui.view.reusables.TrainingCard
import com.example.bkskjold.ui.viewmodel.NavigationItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
            /*BKSkjoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DefaultPreview()
                }
            }*/
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Trainings,
        NavigationItem.Events,
        NavigationItem.Profile,
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_700),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}


@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) {
        /* Add code later */
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
    //NewsCard()
    //TrainingCard()
}