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


val Purple_A = Color(0xFF6A2441)
@Composable
fun SplashScreen (navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple_A),
    ) {
        Image(painter = painterResource(id = R.drawable.library),
            contentDescription = "Image",
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxWidth(),
        )
        Text(
            text = "Cari Tahu kondisi Bukumu? ",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 22.sp,
            fontWeight = FontWeight(800),
            color = Color.White,
            modifier = Modifier
                .padding(top = 150.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.Center),
        )
        Text(
            text = "Perkirakan dan kelola keterlambatanmu ",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 250.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.Center),
        )
        Button(
            onClick = { navController.navigate("optionScreen") },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 450.dp)
                .align(Alignment.Center),
            contentPadding = PaddingValues(16.dp),
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