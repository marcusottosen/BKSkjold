package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.dataClass.CurrentUser
import com.example.bkskjold.ui.view.reusables.EditProfileHeader
import com.example.bkskjold.ui.viewmodel.DocumentCreationViewModel
import com.example.bkskjold.ui.viewmodel.RegisterViewModel

@Composable
fun EditProfilePage(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel(),
) {
    val viewModel = DocumentCreationViewModel()
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            EditProfileHeader(navController)
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp))
            {
                val firstName = remember { mutableStateOf(CurrentUser.firstName) }

                Text(text = stringResource(R.string.firstName),
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

                Text(text = stringResource(R.string.lastName),
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

                Text(text = stringResource(R.string.Email),
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
                val location = viewModel.getTeams()
                var newText by remember { mutableStateOf("") }
                var dropDownWidth by remember { mutableStateOf(0) }

                Text(text = stringResource(R.string.Team),
                    Modifier.padding(top = 10.dp))
                OutlinedTextField(
                    value = newText,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged {
                            dropDownWidth = it.width
                        },
                    placeholder = { Text(CurrentUser.team) },

                    trailingIcon = {
                        Icon(Icons.Filled.ArrowDropDown, "null",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(with(LocalDensity.current) { dropDownWidth.toDp() })
                ) {
                    location.forEach { team ->
                        DropdownMenuItem(onClick = {
                            newText = team; registerViewModel.updateTeam(newText)
                        }) {
                            Text(text = team)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 200.dp))
        }
    }
}