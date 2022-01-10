package com.example.bkskjold.ui.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.bkskjold.MainScreen
import com.example.bkskjold.R
import com.example.bkskjold.data.model.CurrentUser
import com.example.bkskjold.data.model.User
import com.example.bkskjold.ui.view.pages.gotoEditProfilePage
import com.example.bkskjold.ui.view.pages.gotoSettingsPage
import com.example.bkskjold.ui.viewmodel.logoutUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.internal.DiskLruCache

//val name = Firebase.firestore.collection("users-db").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get().result.getString("name")




@Composable
fun DefaultProfileHeader(user: User,navController: NavController){

    val iconSize = 40.dp

    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.img_profile_header_background, ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth())

        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(
                    onClick = { logoutUser(navController) },
                    modifier = Modifier.padding(0.dp, 5.dp, 15.dp, 0.dp)
                ) {
                    Text(
                        text = "Log ud",
                        color = androidx.compose.ui.graphics.Color.White)
                }
            }

            //Profile picture TODO Get picture from database
            Box(Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .border(
                            4.dp,
                            color = androidx.compose.ui.graphics.Color.Black,
                            shape = CircleShape,
                        )
                        .clip(CircleShape)
                        .align(Alignment.TopCenter)
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = (CurrentUser.firstName + " " + CurrentUser.lastName),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = androidx.compose.ui.graphics.Color.White
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 50.dp)
                        .clickable { gotoSettingsPage(user, navController)},
                ) {
                    Icon(
                        painterResource(id = R.drawable.icon_settings),
                        contentDescription = null,
                        Modifier.size(iconSize)
                    )
                    Text(
                        text = "Indstillinger",
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .clickable { gotoEditProfilePage(user, navController) }
                ) {
                    Icon(
                        painterResource(id = R.drawable.icon_edit_pencil),
                        contentDescription = null,
                        Modifier.size(iconSize)
                    )

                    Text(
                        text = "Rediger Profil",
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun DefaultEditProfileHeader(user: User,navController: NavController) {
    val iconSize = 40.dp



    Box(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.img_profile_header_background, ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth())

        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable { gotoSettingsPage(user,navController)},
                ) {
                TextButton(
                    onClick = {navController.navigateUp()},
                    modifier = Modifier.padding(0.dp, 5.dp, 15.dp, 0.dp)
                ) {
                    Text(
                        text = "Annuller",
                        color = androidx.compose.ui.graphics.Color.White)
                } }
                TextButton(
                    onClick = {navController.navigateUp()},
                    modifier = Modifier.padding(0.dp, 5.dp, 15.dp, 0.dp)
                ) {
                    Text(
                        text = "Gem ændringer",
                        color = androidx.compose.ui.graphics.Color.White)
                }
            }
            Box(Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .border(
                            4.dp,
                            color = androidx.compose.ui.graphics.Color.Black,
                            shape = CircleShape,
                        )
                        .clip(CircleShape)
                        .align(Alignment.TopCenter)
                )
            }
            TextButton(
                onClick = {/*TODO*/},
                modifier = Modifier
                    .align(CenterHorizontally)

            ) {
                Text(
                    text = "Skift profilbillede",
                    color = androidx.compose.ui.graphics.Color.White)
            }
        }
    }
}
