package com.example.bkskjold.ui.view.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.R
import com.example.bkskjold.data.model.NavigationItem

@Composable
fun LoginPage(navController: NavController) {

    val loginImage = painterResource(id = R.drawable.login_image2)
    val bgColor = Brush.verticalGradient(listOf(colorResource(R.color.primary), colorResource(R.color.light_green)),
    startY = 0.0f,
    endY = 1000.0f)

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(20.dp)

    ) {
        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back icon",
                    tint = Color.White
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.0f)
                .background(Color.Transparent)
                //.verticalScroll(state = scrollState)
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
                text = "Velkommen tilbage!",
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = "log ind",
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
                value = emailValue,
                onValueChange = { emailValue = it },
                label = null,
                placeholder = { Text(text = "Email") },
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Email icon")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)
                    }
                )


            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = null,
                placeholder = { Text(text = "Adgangskode") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Lock icon")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {focusManager.clearFocus()
                    }
                )


            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {navController.navigate(NavigationItem.Home.route)},
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
                    text = "Fortsæt >",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(175.dp))

        }
    }
}