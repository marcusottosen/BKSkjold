package com.example.bkskjold.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.bkskjold.MainActivity
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test

class TrainingOverviewViewModelTest : TestCase() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSignupButtonExists() {
        composeTestRule.onNodeWithText("Deltag").assertExists()
    }

    @Test
    fun testSignupButtonClick() {
        composeTestRule.onNodeWithText("Deltag").performClick()

        composeTestRule.onNodeWithText("Afmeld Deltagelse").assertExists()
    }
}