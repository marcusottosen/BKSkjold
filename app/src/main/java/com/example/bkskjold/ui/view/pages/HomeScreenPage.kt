package com.example.bkskjold.ui.view.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.bkskjold.R
import com.example.bkskjold.data.model.Training
import com.example.bkskjold.data.model.news
import com.example.bkskjold.data.model.trainings
import com.example.bkskjold.ui.view.reusables.HomePageCategories
import com.example.bkskjold.ui.view.reusables.NewsCard
import com.example.bkskjold.ui.view.reusables.NextTrainingCard

//TODO Lav metode til at finde den næste tilmeldte træning
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
                            text = stringResource(R.string.app_name),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(//Card title
                            text = stringResource(R.string.clubName),
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
                        .height(50.dp)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(colorResource(id = R.color.main_background))
                )
            }
        }

        item {
            NextTrainingCard(training = trainings[1])
        }

        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 35.dp, 15.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HomePageCategories(R.drawable.icon_field, stringResource(R.string.bookField))
                HomePageCategories(R.drawable.icon_calendarhome, stringResource(R.string.calender))
                HomePageCategories(R.drawable.icon_map, stringResource(R.string.map))
                HomePageCategories(R.drawable.icon_forum, stringResource(R.string.discussionForum))
            }
        }

        item {
            Spacer(modifier = Modifier.height(35.dp))
            Text(//Card title
                text = stringResource(R.string.news),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 25.dp)
            )

        }
        items(news.size) { i ->
            NewsCard(news = news[i])
        }
        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}