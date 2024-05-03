package org.d3if3014.asesment_mobpro.ui.theme.Screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3014.asesment_mobpro.R
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme


val Purple_A = Color(0xFFFFDCB3)
@Composable
fun SplashScreen (navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple_A),
    ) {
        Image(painter = painterResource(id = R.drawable.zakat_peng),
            contentDescription = "Image",
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxWidth(),
        )
        Text(
            text = "Ayo Tunaikan Zakat!",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            color = Color.Black,
            modifier = Modifier
                .padding(top = 160.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.Center),
        )
        Text(
            text = "Mari Atur Zakat Penghasilanmu",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 240.dp)
                .padding(horizontal = 30.dp)
                .align(Alignment.Center)
        )
        Button(
            onClick = { navController.navigate("optionScreen") },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 400.dp)
                .align(Alignment.Center),
            contentPadding = PaddingValues(20.dp),
            colors = ButtonDefaults.buttonColors(
                Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Get Started")
        }
    }


}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true,
    widthDp = 390,
    heightDp = 800,
    )
@Composable
fun SplahScreenPreview() {
    Asesment_mobproTheme {
        SplashScreen(rememberNavController())
    }
}