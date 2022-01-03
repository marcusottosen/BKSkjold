package com.example.bkskjold.ui.view.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.getColor
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.DefaultHeader
import com.example.bkskjold.ui.view.reusables.HomePageCategories
import com.example.bkskjold.ui.view.reusables.NewsCard
import com.example.bkskjold.ui.view.reusables.NextTrainingCard

@Preview
@Composable
fun HomeScreenPage() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.CenterStart)
    ) {
        item {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(180.dp)
                        //.background(color = Color(R.color.primary))
                        .background(Color(0xFF448C8B))
                        .background(colorResource(id = R.color.primary))
                    /*.background(
                    Brush.linearGradient(
                        listOf(
                            Color(R.color.primary),
                            Color(R.color.primary)
                        )
                    )
                )*/
                ) {
                    Column(Modifier.padding(start = 21.dp, top = 36.dp)) {
                        Text(//Card title
                            text = "NemSport",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(//Card title
                            text = "BKSkjold",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .fillMaxWidth()
                        .height(75.dp)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(colorResource(id = R.color.main_background))
                ) {}
            }
        }

        item {
            NextTrainingCard(
                header = "Træning for seniorer",
                description = "Holdtræning",
                date = "25. Oktober 2021",
                time = "17:45",
                location = "Bane C"
            )
        }


            //Spacer(modifier = Modifier.height(200.dp))


        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(21.dp, 36.dp, 21.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HomePageCategories(R.drawable.icon_field, "Book Bane")
                HomePageCategories(R.drawable.icon_calendarhome, "Kalender")
                HomePageCategories(R.drawable.icon_map, "Kort")
                HomePageCategories(R.drawable.icon_forum, "Diskussions \n -forum")
            }
        }

        item {
            NewsCard()
            NewsCard()
            NewsCard()
        }
    }
}
