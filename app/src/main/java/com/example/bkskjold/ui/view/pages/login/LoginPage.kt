package com.example.bkskjold.ui.view.pages.login

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bkskjold.R
import com.example.bkskjold.ui.viewmodel.LoginViewModel

/**
 * The login view which will help the user to authenticate themselves and go to the
 * home screen to show and send messages to others.
 */

@Composable
fun LoginView(
    home: () -> Unit,
    back: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(),
) {
    val loginImage = painterResource(id = R.drawable.login_image2)
    val bgColor = Brush.verticalGradient(listOf(colorResource(R.color.primary),
        colorResource(R.color.light_green)),
        startY = 0.0f,
        endY = 1000.0f)

    rememberScrollState()
    val focusManager = LocalFocusManager.current

    val email: String by loginViewModel.email.observeAsState("")
    val password: String by loginViewModel.password.observeAsState("")
    val loading: Boolean by loginViewModel.loading.observeAsState(initial = false)

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back icon",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (loading) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1.0f)
                        .background(Color.Transparent)) {

                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1.0f)
                                .background(Color.Transparent)
                        )
                        {
                            Image(
                                loginImage,
                                contentDescription = "login_image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(300.dp, 250.dp)
                            )

                            Spacer(modifier = Modifier.height(36.dp))

                            Text(
                                text = stringResource(R.string.WelcomeBack),
                                fontSize = 18.sp,
                                color = Color.White
                            )

                            Text(
                                text = stringResource(R.string.LogIn),
                                fontSize = 32.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            OutlinedTextField(
                                modifier = Modifier
                                    .width(340.dp)
                                    .height(60.dp)
                                    .background(Color.White, RoundedCornerShape(50)),
                                value = email,
                                onValueChange = { loginViewModel.updateEmail(it) },
                                label = null,
                                placeholder = { Text(text = stringResource(R.string.Email)) },
                                shape = RoundedCornerShape(50),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Email,
                                        contentDescription = stringResource(R.string.Email))
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
                                onValueChange = { loginViewModel.updatePassword(it) },
                                label = null,
                                placeholder = { Text(text = stringResource(R.string.Password)) },
                                visualTransformation = PasswordVisualTransformation(),
                                shape = RoundedCornerShape(50),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Lock,
                                        contentDescription = stringResource(R.string.Password))
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
                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { loginViewModel.loginUser(home = home) },
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
                                    text = stringResource(R.string.Continue),
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    loginViewModel.resetPassword()
                                    Toast.makeText(
                                        context,
                                        "Nulstillelse sendt til valgte email",
                                        Toast.LENGTH_LONG
                                    ).show()
                                },
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
                                    text = stringResource(R.string.ResetPassword),
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
}