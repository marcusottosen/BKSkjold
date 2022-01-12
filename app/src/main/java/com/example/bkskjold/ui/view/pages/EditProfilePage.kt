package com.example.bkskjold.ui.view.pages

import android.widget.EditText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bkskjold.data.model.CurrentUser
import com.example.bkskjold.data.model.users
import com.example.bkskjold.ui.view.reusables.DefaultEditProfileHeader

import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel
import com.example.bkskjold.ui.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun editProfilePage(navController: NavController,registerViewModel: RegisterViewModel = viewModel()) {
    val currentUser = users[3]
    val focusManager = LocalFocusManager.current
    val auth: FirebaseAuth = Firebase.auth
    val firstName: String by registerViewModel.firstName.observeAsState("")




    LazyColumn(
        //verticalArrangement = Arrangement.spacedBy(30.dp)
        modifier = Modifier.fillMaxSize()

    ) {
        item {
            DefaultEditProfileHeader(currentUser, navController)
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp))
            { val fornavn = remember { mutableStateOf(CurrentUser.firstName) }

                Text(text = "Fornavn",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),

                    value = fornavn.value,
                    onValueChange = { input -> fornavn.value = input ; registerViewModel.updateFirstName(input)},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() })
                )
            }
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp))
            {
                val text = remember { mutableStateOf(CurrentUser.lastName) }

                Text(text = "Efternavn",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),

                    value = text.value,
                    onValueChange = { input -> text.value = input },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp))
            {
                val text = remember { mutableStateOf(CurrentUser.email) }

                Text(text = "E-mail",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),

                    value = text.value,
                    onValueChange = { input -> text.value = input },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
        }
    }
}
