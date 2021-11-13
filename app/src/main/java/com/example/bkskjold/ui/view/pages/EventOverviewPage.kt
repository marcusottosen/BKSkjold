package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.DefaultHeader
import com.example.bkskjold.ui.viewmodel.EventOverviewViewModel


class EventOverviewPage {
    @Preview
    @Composable
    fun eventOverview(){
        Column (
            modifier = Modifier
                .background(color = colorResource(R.color.main_background)),
        )
        {
            DefaultHeader(header = stringResource(id = R.string.EventOverviewPageHeader))
            EventOverviewViewModel().getEventsView()

        }

        }
    }
