package org.d3if3014.asesment_mobpro

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3014.asesment_mobpro.navigation.SetUpnavGraph
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asesment_mobproTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetUpnavGraph()
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Asesment_mobproTheme {
       SetUpnavGraph()
    }
}