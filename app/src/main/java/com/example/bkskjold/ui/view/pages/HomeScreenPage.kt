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
