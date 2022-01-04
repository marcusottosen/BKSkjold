package com.example.bkskjold.ui.view.pages


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.DefaultHeader
import com.example.bkskjold.ui.view.reusables.NewsCard
import com.example.bkskjold.ui.view.reusables.NextTrainingCard

@Preview
@Composable
fun HomeScreenPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.CenterStart)
    ) {
        DefaultHeader(header = stringResource(id = R.string.NemSportHeader))

        NextTrainingCard(header = "Træning for seniorer", description = "Holdtræning", date = "25. Oktober 2021", time = "17:45", location = "Bane C")
        Text(text = "Kategorier")
        Spacer(modifier = Modifier.height(200.dp))

        NewsCard()
        NewsCard()
        NewsCard()
    }
}
/*
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
                        .height(160.dp)
                        .background(colorResource(id = R.color.primary))
                    .background(
                    Brush.verticalGradient(
                        listOf(
                            colorResource(id = R.color.primary),
                            colorResource(id = R.color.home_header_secondary)
                        )
                    )
                )
                ) {
                    Column(Modifier.padding(start = 15.dp, top = 36.dp)) {
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
                date = "25. oktober",
                timeStart = "16:00",
                timeEnd = "17:30",
                day = "mandag",
                team = "U12",
                location = "Bane C",
                attending = 8,
                spots = 12,
                trainer = "Bjarne Andersen"
            )
        }



        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 35.dp, 15.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HomePageCategories(R.drawable.icon_field, "Book Bane")
                HomePageCategories(R.drawable.icon_calendarhome, "Kalender")
                HomePageCategories(R.drawable.icon_map, "Kort")
                HomePageCategories(R.drawable.icon_forum, "Diskussions \n -forum")
            }
        }

        item {
            Spacer(modifier = Modifier.height(35.dp))
            Text(//Card title
                text = "Nyheder",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 25.dp)
            )

        }
        val allNews = NewsData().getNews()
        items(allNews.size) { i ->
            NewsCard(
                allNews[i][0],
                allNews[i][1],
                allNews[i][2])
        }
        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

 */