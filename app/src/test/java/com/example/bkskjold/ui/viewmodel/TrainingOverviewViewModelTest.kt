package com.example.bkskjold.ui.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import junit.framework.TestCase


class TrainingOverviewViewModelTest : TestCase() {

    @Composable
    fun testGetOverviewView() {
        val navController = rememberNavController()
        val date = ""
        val timeStart = ""
        val team = ""

        val viewModel = TrainingOverviewViewModel()


        //viewModel.GetOverviewView(navController = navController, date = date, timeStart = timeStart, team = team)
        print("")
    }

    fun testGetSignedUpView() {

    }

    fun testFilterAndSort() {

    }
}