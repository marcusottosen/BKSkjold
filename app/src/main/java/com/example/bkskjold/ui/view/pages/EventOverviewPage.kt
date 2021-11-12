package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R
import com.example.bkskjold.ui.viewmodel.EventOverviewViewModel


class EventOverviewPage {
    val viewModel = EventOverviewViewModel()


    @Preview
    @Composable
    fun eventOverview(){
        Column (
            modifier = Modifier
                .background(color = colorResource(R.color.main_background)),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.top_header),
                        shape = AbsoluteRoundedCornerShape(0.dp, 0.dp, 100.dp, 100.dp)
                    )
                    .padding(5.dp, 30.dp, 5.dp, 30.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = stringResource(R.string.EventOverviewPageHeader),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.main_background)
                )
            }
            val getEventList = viewModel.createEventsList()
            getEventList
        }
    }
}