package org.d3if3014.asesment_mobpro.ui.theme.Screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3014.asesment_mobpro.R
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme
import org.d3if3014.mobpro.ui.screen.ScreenContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionScreen (navController: NavHostController) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Purple_A)
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Image(
                painter = painterResource(id = R.drawable.denda),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 100.dp)
                    .clip(RoundedCornerShape(CornerSize(30.dp)))
                    .clickable {
                        navController.navigate("mainScreen")
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.note),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 100.dp)
                    .clip(RoundedCornerShape(CornerSize(30.dp)))
                    .clickable {
                        navController.navigate("rulesScreen")
                    }
            )

        }

    }






@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OptionScreenPreview() {
    Asesment_mobproTheme {
        OptionScreen(rememberNavController())
    }
}