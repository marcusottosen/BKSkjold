package com.example.bkskjold.ui.view.pages

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bkskjold.R
import com.example.bkskjold.ui.view.reusables.Appbar
import com.example.bkskjold.ui.view.reusables.Buttons
import com.example.bkskjold.ui.view.reusables.TextFormField
import com.example.bkskjold.ui.viewmodel.RegisterViewModel

/**
 * The Register view which will be helpful for the user to register themselves into
 * our database and go to the home screen to see and send messages.
 */

// Inspiration fundet ved:
// https://pradyotprksh4.medium.com/%EF%B8%8Fflashchat-jetpack-compose-firebase-bd16014b025d

@Composable
fun OldRegisterView(
    home: () -> Unit,
    back: () -> Unit,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val firstName: String by registerViewModel.firstName.observeAsState("")
    val lastName: String by registerViewModel.lastName.observeAsState("")
    val email: String by registerViewModel.email.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
    val address: String by registerViewModel.address.observeAsState("")
    val phoneNumber: String by registerViewModel.phoneNumber.observeAsState("")

    val loading: Boolean by registerViewModel.loading.observeAsState(initial = false)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (loading) {
            CircularProgressIndicator()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Appbar(
                title = "Register",
                action = back
            )
            TextFormField(
                value = firstName,
                onValueChange = { registerViewModel.updateFirstName(it) },
                label = "First Name",
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None
            )
            TextFormField(
                value = lastName,
                onValueChange = { registerViewModel.updateLastName(it) },
                label = "Last Name",
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None
            )
            TextFormField(
                value = email,
                onValueChange = { registerViewModel.updateEmail(it) },
                label = "Email",
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None
            )
            TextFormField(
                value = password,
                onValueChange = { registerViewModel.updatePassword(it) },
                label = "Password",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            TextFormField(
                value = phoneNumber,
                onValueChange = { registerViewModel.updatePhone(it) },
                label = "Phone",
                keyboardType = KeyboardType.Number,
                visualTransformation = VisualTransformation.None
            )
            TextFormField(
                value = address,
                onValueChange = { registerViewModel.updateAddress(it) },
                label = "Address",
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None
            )
            Spacer(modifier = Modifier.height(20.dp))
            Buttons(
                title = "Register",
                onClick = { registerViewModel.registerUser(home = home) },
                backgroundColor = colorResource(R.color.primary_light)
            )
        }
    }
}

@Composable
fun RegisterView(
    home: () -> Unit,
    back: () -> Unit,
    registerViewModel: RegisterViewModel = viewModel()
)  {

    val registerBgColor = Brush.verticalGradient(
        listOf(colorResource(R.color.primary), colorResource(R.color.light_green)),
        startY = 0.0f,
        endY = 1000.0f
    )
    val scrollState = rememberScrollState()
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
    ){

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
                    //.verticalScroll(state = scrollState)
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
                            text = "Velkommen!",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Opret en gæstebruger",
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
                                    text = "Fornavn",
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
                                    contentDescription = "Email icon"
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
                                    text = "Efternavn",
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
                                    contentDescription = "Email icon"
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
                            placeholder = { Text(text = "Email", color = colorResource(R.color.primary)) },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = "Email icon"
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
                                    text = "Adgangskode",
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
                                    contentDescription = "Lock icon"
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
                                    text = "Gentag adgangskode",
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
                                    contentDescription = "Lock icon"
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
                                text = "Valgfrit",
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
                                    text = "Telefonnummer",
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
                                    contentDescription = "Phone icon"
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
                                Text(text = "Adresse",
                                    color = colorResource(R.color.primary))
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = "Location icon"
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
                                text = "Register",
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