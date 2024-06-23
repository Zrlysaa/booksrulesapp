package org.d3if3014.asesment_mobpro.ui.theme.Screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3014.asesment_mobpro.R
import org.d3if3014.asesment_mobpro.database.KritikSaranDb
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme
import org.d3if3014.asesment_mobpro.util.ViewModelFactory


const val KEY_ID_KRITIK = "idKritik"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = KritikSaranDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var namamu by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var zakat by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(true){
        if (id == null) return@LaunchedEffect
        val data = viewModel.getKritikSaran(id) ?: return@LaunchedEffect
        namamu = data.namamu
        tanggal = data.tanggal
        zakat = data.zakat
        target = data.target

    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }, title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.rencana))
                    else
                        Text(text = stringResource(id = R.string.edit_data))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (namamu == "" || tanggal == ""|| zakat == "" || target == "" ) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null ) {
                            viewModel.insert(namamu, tanggal, zakat, target)
                        } else {
                            viewModel.update(id, namamu, tanggal, zakat, target)
                        }
                            navController.popBackStack() }) {
                        Icon(imageVector = Icons.Outlined.Check, contentDescription = stringResource(
                            R.string.simpankritik),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction { showDialog = true }
                        DisplayAlertDialog(
                                openDialog = showDialog,
                        onDismissRequest = { showDialog = false }) {
                            showDialog = false
                            viewModel.delete(id)
                            navController.popBackStack()

                        }
                    }

                }
            )
        }
    ) {
        padding ->
        FormKritikSaran(
            title = namamu,
            onTitleChange = {namamu= it},
            desc = tanggal,
            onDescChange = {tanggal = it},
            besc = zakat,
            onBescChange = {zakat = it},
            kesc = target,
            onKescChange = {target = it},
            modifier = Modifier.padding(padding))


    }
}

@Composable
fun FormKritikSaran(
    title: String, onTitleChange: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    besc: String, onBescChange: (String) -> Unit,
    kesc: String, onKescChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
     OutlinedTextField(
         value = title, 
         onValueChange = {onTitleChange(it)},
         label = { Text(text = stringResource(id = R.string.namamu))},
         singleLine = true,
         keyboardOptions = KeyboardOptions(
             capitalization = KeyboardCapitalization.Words,
             imeAction = ImeAction.Next
         ),
         modifier = Modifier.fillMaxWidth()

         )
        OutlinedTextField(
            value = desc,
            onValueChange = {onDescChange(it)},
            label = { Text(text = stringResource(id = R.string.tanggal))},
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = besc,
            onValueChange = {onBescChange(it)},
            label = { Text(text = stringResource(id = R.string.zakat))},
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = kesc,
            onValueChange = {onKescChange(it)},
            label = { Text(text = stringResource(id = R.string.target))},
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            ),
            modifier = Modifier.fillMaxSize()

        )
    }
}


@Composable
fun DeleteAction(delete: () -> Unit) {
    var expended by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expended = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false })
        {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expended = false
                    delete()
                })
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Asesment_mobproTheme {
        DetailScreen(rememberNavController())
    }
}