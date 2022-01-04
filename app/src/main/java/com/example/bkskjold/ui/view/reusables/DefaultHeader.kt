package com.example.bkskjold.ui.view.reusables

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Layout
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color as Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bkskjold.DefaultScreen
import com.example.bkskjold.R
import com.example.bkskjold.data.model.ProfileInfo
import com.example.bkskjold.ui.theme.Shapes
import com.example.bkskjold.ui.theme.Typography
import com.example.bkskjold.ui.viewmodel.ProfileOverviewViewModel

@Composable
fun DefaultHeader(header: String){
    //var color = colorResource(id = R.color.main_background)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            /*.background(
                brush = Brush.horizontalGradient(
                    listOf(color, color, color),
                    startX = 10.0f,
                    endX = 20.0f),
                alpha = 0F
            )*/
    ) {
        Image(painter = painterResource(id = R.drawable.header_background), contentDescription = null)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 60.dp, 0.dp, 0.dp),
            text = header,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.main_background)
        )
    }
}


@Composable
fun DefaultProfileHeader(){
    //var color = colorResource(id = R.color.main_background)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
        /*.background(
            brush = Brush.horizontalGradient(
                listOf(color, color, color),
                startX = 10.0f,
                endX = 20.0f),
            alpha = 0F
        )*/
    ) {
        Image(painter = painterResource(id = R.drawable.header_background), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 2.dp, 0.dp, 0.dp)
                .size(100.dp)
                .border(
                    4.dp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    shape = CircleShape,
                )
                .align(Alignment.TopCenter)
                .clip(CircleShape)




        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 100.dp, 0.dp, 0.dp),
            text = ( ProfileInfo().testProfile[5] + ", " +
            ProfileInfo().testProfile[4] + "\n " +
            ProfileInfo().testProfile[6] ),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.cardview_dark_background)
        )

TextButton(
    onClick = {/* TODO */ },
    modifier = Modifier
        .padding(0.dp, 15.dp, 10.dp, 0.dp)
        .align(Alignment.TopEnd)) {
            Text(
                text = "Log ud",
                color = androidx.compose.ui.graphics.Color.Black)

        }
        
IconButton(onClick = { /*TODO*/ },
modifier = Modifier
    .align(Alignment.BottomStart)
    .padding(50.dp,0.dp,0.dp,20.dp)) {
    Icon(painterResource(id = R.drawable.icon_settings),
        contentDescription = null,
        )}

    IconButton(onClick = { /*TODO*/ },
modifier = Modifier
    .align(Alignment.BottomEnd)
    .padding(0.dp,0.dp,50.dp,20.dp)) {
    Icon(painterResource(id = R.drawable.icon_edit_pencil),
        contentDescription = null,
        )
}
    
         
    
        


           /* text = "Log ud",
    fontSize = 15.sp,
    fontWeight = FontWeight.Black,
    textAlign = TextAlign.Right,
    color = colorResource(R.color.cardview_dark_background),
)
       Text(
            modifier = Modifier
                .padding(0.dp, 25.dp, 20.dp, 0.dp)
                .height(25.dp),
                text = "Log ud",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Right,
                color = colorResource(R.color.cardview_dark_background),



                )*/



    }}


         
    
        


           /* text = "Log ud",
    fontSize = 15.sp,
    fontWeight = FontWeight.Black,
    textAlign = TextAlign.Right,
    color = colorResource(R.color.cardview_dark_background),
)
       Text(
            modifier = Modifier
                .padding(0.dp, 25.dp, 20.dp, 0.dp)
                .height(25.dp),
                text = "Log ud",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Right,
                color = colorResource(R.color.cardview_dark_background),



                )*/




