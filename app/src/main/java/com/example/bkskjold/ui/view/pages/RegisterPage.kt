package com.example.bkskjold.ui.view.pages

import android.view.RoundedCorner
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bkskjold.R

@Composable
fun RegisterPage() {

    val backButton = painterResource(id = R.drawable.back_white)
    val registerBgColor = Brush.verticalGradient(
        listOf(colorResource(R.color.primary), colorResource(R.color.light_green)),
        startY = 0.0f,
        endY = 1000.0f
    )

    var firstNameValue by remember { mutableStateOf("") }
    var lastNameValue by remember { mutableStateOf("")}
    var emailValue by remember { mutableStateOf("")}
    var passwordValue by remember { mutableStateOf("") }
    var passwordValueCheck by remember { mutableStateOf("")}
    var phoneValue by remember { mutableStateOf("") }
    var dateOfBirthValue by remember { mutableStateOf("") }
    var addressValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(registerBgColor)
            .padding(20.dp)

    ) {

        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(75.dp)
                    .height(50.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                )


            ) {
                Image(
                    backButton,
                    contentDescription = "backButton",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                )
            }
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.0f)
                .background(Color.Transparent)

        )
        {
            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Velkommen!",
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = "Opret gæstebruger",
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
                value = firstNameValue,
                onValueChange = {firstNameValue = it},
                label = null,
                placeholder = { Text(text = "  Fornavn", color = colorResource(R.color.primary)) },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Email icon")
                },

            )
            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = lastNameValue,
                onValueChange = {lastNameValue = it},
                label = null,
                placeholder = { Text(text = "  Efternavn", color = colorResource(R.color.primary)) },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Email icon")

                },

            )

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = emailValue,
                onValueChange = { emailValue = it },
                label = null,
                placeholder = { Text(text = "  Email", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Email icon")
                },

            )

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = null,
                placeholder = { Text(text = "  Adgangskode", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Lock icon")
                },

                )

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = passwordValueCheck,
                onValueChange = { passwordValueCheck = it },
                label = null,
                placeholder = { Text(text = "  Gentag adgangskode", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Lock icon")
                },

                )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(24.dp)
            ){
                Text(
                    modifier = Modifier.align(Alignment.BottomStart),
                    text = "Valgfrit",
                    fontSize = 12.sp,
                    color = Color.White,

                    )
            }

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = phoneValue,
                onValueChange = { phoneValue = it },
                label = null,
                placeholder = { Text(text = "  Telefonnummer", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Phone icon")
                },

                )

            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = dateOfBirthValue,
                onValueChange = { dateOfBirthValue = it },
                label = null,
                placeholder = { Text(text = "  Fødselsdag", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "Phone icon")
                },

                )
            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = phoneValue,
                onValueChange = { phoneValue = it },
                label = null,
                placeholder = { Text(text = "  Telefonnummer", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Phone icon")
                },

                )
            OutlinedTextField(
                modifier = Modifier
                    .width(340.dp)
                    .height(60.dp)
                    .background(Color.White, RoundedCornerShape(50)),
                value = addressValue,
                onValueChange = { addressValue = it },
                label = null,
                placeholder = { Text(text = "  Adresse", color = colorResource(R.color.primary))  },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Location icon")
                },

                )


        }
    }
}