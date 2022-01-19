package com.example.bkskjold.ui.view.pages.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bkskjold.R
import com.example.bkskjold.ui.viewmodel.RegisterViewModel

/**
 * The Register view which will be helpful for the user to register themselves into
 * our database and go to the home screen to see and send messages.
 */
@Composable
fun RegisterView(
    home: () -> Unit,
    back: () -> Unit,
    registerViewModel: RegisterViewModel = viewModel(),
) {

    val registerBgColor = Brush.verticalGradient(
        listOf(colorResource(R.color.primary), colorResource(R.color.light_green)),
        startY = 0.0f,
        endY = 1000.0f
    )
    rememberScrollState()
    val focusManager = LocalFocusManager.current

    val firstName: String by registerViewModel.firstName.observeAsState("")
    val lastName: String by registerViewModel.lastName.observeAsState("")
    val email: String by registerViewModel.email.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
    val passwordCheck: String by registerViewModel.passwordCheck.observeAsState("")
    val address: String by registerViewModel.address.observeAsState("")
    val phoneNumber: String by registerViewModel.phoneNumber.observeAsState("")

    val loading: Boolean by registerViewModel.loading.observeAsState(initial = false)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(registerBgColor)
            .padding(20.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (loading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1.0f)
                        .background(Color.Transparent)
                )
                {
                    item {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(start = 10.dp),
                                horizontalArrangement = Arrangement.Start) {
                                IconButton(onClick = back) {
                                    Icon(imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Back icon",
                                        tint = Color.White)
                                }
                            }
                        }
                        Text(
                            text = stringResource(R.string.Welcome),
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.CreateGuestUser),
                            fontSize = 28.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = firstName,
                            onValueChange = { registerViewModel.updateFirstName(it) },
                            label = null,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.Firstname),
                                    color = colorResource(R.color.primary),

                                    )
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = stringResource(R.string.Firstname)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = lastName,
                            onValueChange = { registerViewModel.updateLastName(it) },
                            label = null,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.Surname),
                                    color = colorResource(R.color.primary)
                                )
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = stringResource(R.string.Surname)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = email,
                            onValueChange = { registerViewModel.updateEmail(it) },
                            label = null,
                            placeholder = {
                                Text(text = stringResource(R.string.Email),
                                    color = colorResource(R.color.primary))
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = stringResource(R.string.Email)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = password,
                            onValueChange = { registerViewModel.updatePassword(it) },
                            label = null,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.Password),
                                    color = colorResource(R.color.primary)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Lock,
                                    contentDescription = stringResource(R.string.Password)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = passwordCheck,
                            onValueChange = { registerViewModel.updatePasswordCheck(it) },
                            label = null,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.RetypePassword),
                                    color = colorResource(R.color.primary)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Lock,
                                    contentDescription = stringResource(R.string.Password)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            )
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(Color.Transparent)
                                .padding(24.dp, 4.dp)

                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.BottomStart),
                                text = stringResource(R.string.Optional),
                                fontSize = 12.sp,
                                color = Color.White,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = phoneNumber,
                            onValueChange = { registerViewModel.updatePhone(it) },
                            label = null,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.PhoneNumber),
                                    color = colorResource(R.color.primary)
                                )
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Phone,
                                    contentDescription = stringResource(R.string.PhoneNumber)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = address,
                            onValueChange = { registerViewModel.updateAddress(it) },
                            label = null,
                            placeholder = {
                                Text(text = stringResource(R.string.Address),
                                    color = colorResource(R.color.primary))
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = stringResource(R.string.Address)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        Button(
                            onClick = { registerViewModel.registerUser(home = home) },
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .border(1.dp, Color.White, RoundedCornerShape(50)),
                            shape = RoundedCornerShape(50),
                            elevation = null,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.Register),
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(150.dp))
                    }
                }
            }
        }
    }
}