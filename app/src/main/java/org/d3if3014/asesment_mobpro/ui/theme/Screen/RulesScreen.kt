package org.d3if3014.asesment_mobpro.ui.theme.Screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3014.asesment_mobpro.R
import org.d3if3014.asesment_mobpro.database.KritikSaranDb
import org.d3if3014.asesment_mobpro.model.KritikSaran
import org.d3if3014.asesment_mobpro.navigation.Screen
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme
import org.d3if3014.asesment_mobpro.util.SettingsDataStore
import org.d3if3014.asesment_mobpro.util.ViewModelFactory



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesScreen(navController: NavHostController) {
    val dataStore = SettingsDataStore(LocalContext.current)
    val showList by dataStore.layoutFlow.collectAsState(true)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.kembali
                            ),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = stringResource(id = R.string.page_name), fontFamily = FontFamily(
                            Font(R.font.poppins)
                        )
                        )
                    }
                }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ), actions = {
                    IconButton(onClick = { CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveLayout(!showList)
                    } }) {
                        Icon(
                            painter = painterResource(
                                if (showList) R.drawable.baseline_grid_view_24
                                else R.drawable.baseline_view_list_24
                            ),
                            contentDescription = stringResource(
                                if (showList) R.string.grid
                                else R.string.list
                            ),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.FormBaru.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.rencana),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { padding ->
        ScreenModel(showList,Modifier.padding(padding), navController)
    }
}

@Composable
fun GridItem(kritikSaran: KritikSaran, onClik: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClik() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = kritikSaran.namamu,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = kritikSaran.tanggal,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = kritikSaran.zakat,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = kritikSaran.target,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ScreenModel(showList: Boolean, modifier: Modifier, navController: NavHostController){
    val context = LocalContext.current
    val db = KritikSaranDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    if (data.isEmpty()){
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.pena),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 10.dp)
            )

            Text(text = stringResource(id = R.string.list_kosong),
                fontFamily = FontFamily(Font(R.font.poppins))
            )
        }
    } else {
        if (showList) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 84.dp)
            ) {
                items(data) {
                    ListItem(kritikSaran = it) {
                        navController.navigate(Screen.FormUbah.withId(it.id))
                    }
                    Divider()
                }
            }
        } else {
            LazyVerticalStaggeredGrid(
                modifier = modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 84.dp)
            ) {
                items(data) {
                    GridItem(kritikSaran = it) {
                        navController.navigate(Screen.FormUbah.withId(it.id))
                    }
                }
            }
        }

    }

}

@Composable
fun ListItem(kritikSaran: KritikSaran, onClik:() -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClik() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = kritikSaran.namamu,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold )
        Text(text = kritikSaran.tanggal,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis)
        Text(text = kritikSaran.zakat,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis)
        Text(text = kritikSaran.target,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis)
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RulesScreenPreview() {
    Asesment_mobproTheme {
        RulesScreen(rememberNavController())
    }
}
