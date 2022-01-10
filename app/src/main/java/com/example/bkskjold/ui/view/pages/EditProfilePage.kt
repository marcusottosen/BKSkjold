package com.example.bkskjold.ui.view.pages

import android.widget.EditText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkskjold.data.model.CurrentUser
import com.example.bkskjold.data.model.users
import com.example.bkskjold.ui.view.reusables.DefaultEditProfileHeader

import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel


@Composable
fun editProfilePage(navController: NavController) {
    val currentUser = users[3]

    LazyColumn(
        //verticalArrangement = Arrangement.spacedBy(30.dp)
        modifier = Modifier.fillMaxSize()

    ) {
        item {
            DefaultEditProfileHeader(currentUser,navController)
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth())
            {

            val text = remember { mutableStateOf(CurrentUser.firstName)}
                Text(text = "Fornavn",
                Modifier.padding(start= 20.dp, top = 10.dp))
            OutlinedTextField(modifier = Modifier.padding(start = 20.dp),
                    value = text.value,
                    onValueChange = { input -> text.value = input},
                    maxLines = 1, )

            }}
        item {
            Column(modifier = Modifier
                .fillMaxWidth())
            {

                val text = remember { mutableStateOf(CurrentUser.lastName)}
                Text(text = "Efternavn",
                    Modifier.padding(start= 20.dp, top = 10.dp))
                OutlinedTextField(modifier = Modifier.padding(start = 20.dp),
                    value = text.value,
                    onValueChange = { input -> text.value = input},
                    maxLines = 1, )
            }}
        item {
            Column(modifier = Modifier
                .fillMaxWidth())
            {

                val text = remember { mutableStateOf(CurrentUser.email)}
                Text(text = "E-mail",
                    Modifier.padding(start= 20.dp, top = 10.dp))
                OutlinedTextField(modifier = Modifier.padding(start = 20.dp),
                    value = text.value,
                    onValueChange = { input -> text.value = input},
                    maxLines = 1, )
            }}

        }
    }
