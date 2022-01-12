package com.example.bkskjold.ui.view.pages

import android.content.ClipData
import android.util.Size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius.Companion.Zero
import androidx.compose.ui.input.key.Key.Companion.Zero
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bkskjold.data.model.CurrentUser
import com.example.bkskjold.data.model.users
import com.example.bkskjold.ui.view.reusables.DefaultEditProfileHeader
import com.example.bkskjold.ui.view.reusables.dropDownMenu

import com.example.bkskjold.ui.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.selects.select


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
            {
                val firstName = remember { mutableStateOf(CurrentUser.firstName) }

                Text(text = "Fornavn",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),

                    value = firstName.value,
                    onValueChange = { input ->
                        firstName.value = input; registerViewModel.updateFirstName(input)
                    },
                    singleLine = true,
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
                val lastName = remember { mutableStateOf(CurrentUser.lastName) }

                Text(text = "Efternavn",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),

                    value = lastName.value,
                    onValueChange = { input ->
                        lastName.value = input; registerViewModel.updateLastName(input)
                    },
                    singleLine = true,
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
                val eMail = remember { mutableStateOf(CurrentUser.email) }

                Text(text = "E-mail",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),

                    value = eMail.value,
                    onValueChange = { input ->
                        eMail.value = input; registerViewModel.updateEmail(input)
                    },
                    singleLine = true,
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

        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)) {
                var expanded by remember { mutableStateOf(false) }
                val suggestions = listOf("Senior", "U22", "U16")
                var selectedText by remember { mutableStateOf("") }
                val team = remember { mutableStateOf(CurrentUser.team) }
                var dropDownWidth by remember { mutableStateOf(0) }


                Text(text = "E-mail",
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged {
                            dropDownWidth = it.width
                        },


                    placeholder = { Text(CurrentUser.team) },

                    trailingIcon = {
                        Icon(Icons.Filled.ArrowDropDown, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(with(LocalDensity.current){dropDownWidth.toDp()})
                    // .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label; registerViewModel.updateTeam(selectedText)
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = 200.dp))

        }
    }
}
