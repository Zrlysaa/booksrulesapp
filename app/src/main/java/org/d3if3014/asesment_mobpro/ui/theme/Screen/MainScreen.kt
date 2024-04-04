package org.d3if3014.mobpro.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3014.asesment_mobpro.R
import org.d3if3014.asesment_mobpro.navigation.Screen
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar= {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = stringResource(id = R.string.page_hitung))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.app_name),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@SuppressLint("StringFormatInvalid", "StringFormatMatches")
@Composable
fun ScreenContent(modifier: Modifier){
    var nama by remember { mutableStateOf("") }
    var namaError by remember { mutableStateOf(false) }
    var nim by remember { mutableStateOf("") }
    var nimError by remember { mutableStateOf(false) }
    var terlambat by remember { mutableStateOf("") }
    var terlambatError by remember { mutableStateOf(false) }
    var denda by remember { mutableFloatStateOf(0f) }

    val radioOptions = listOf(
        stringResource(id = R.string.karyawan),
        stringResource(id = R.string.mahasiswa)
    )
    var selectedStatus by remember { mutableStateOf(radioOptions[0]) }

    // Definisi variabel kategori di luar blok Button
    var kategori by remember { mutableStateOf("") }

    val context = LocalContext.current
    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.plash),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = nama, onValueChange = {nama = it},
            label = { Text(text = stringResource(id = R.string.nama)) },
            isError = namaError,
            trailingIcon = { IconPicker(namaError, "")},
            supportingText = { ErrorHint(namaError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )

        OutlinedTextField(value = nim, onValueChange = {nim = it},
            label = { Text(text = stringResource(id = R.string.nim)) },
            isError = nimError,
            trailingIcon = { IconPicker(nimError, "")},
            supportingText = { ErrorHint(nimError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )

        OutlinedTextField(value = terlambat, onValueChange = {terlambat = it},
            label = { Text(text = stringResource(id = R.string.terlambat)) },
            isError = terlambatError,
            trailingIcon = { IconPicker(terlambatError, "hari")},
            supportingText = { ErrorHint(terlambatError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )

        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOptions.forEach { text ->
                StatusOption(
                    label = text,
                    isSelected = selectedStatus == text,
                    modifier = Modifier
                        .selectable(
                            selected = selectedStatus == text,
                            onClick = {
                                selectedStatus = text
                                denda = hitungDenda(terlambat.toInt(), selectedStatus)
                                      },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Button(onClick = {
            namaError = (nama == "" || nama == "0")
            nimError = (nim == "" || nim == "0")
            terlambatError = (terlambat == "" || terlambat == "0")

            if (namaError || nimError ||  terlambatError)
                return@Button
            kategori = if (selectedStatus == "karyawan") "karyawan" else "mahasiswa"
            denda = hitungDenda(terlambat.toInt(), selectedStatus)
        },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.hitung))
        }

        if (denda.toInt() != 0) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            val statusText = if (selectedStatus == stringResource(id = R.string.karyawan)) "Karyawan" else "Mahasiswa"
            Text(
                text = "Status: $statusText",
                style = MaterialTheme.typography.titleLarge
            )

            // Menampilkan hasil denda
            Text(
                text = "Total Denda: ${denda.toInt()}",
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = {
                shareData(
                    context,
                    context.getString(R.string.bagikan_template, terlambat.toString(), selectedStatus, denda.toString())
                )
            },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))
            }
        }
    }
}

private fun hitungDenda(hariTerlambat: Int, selectedStatus: String): Float {
    val kategori = if (selectedStatus.lowercase() == "karyawan") "karyawan" else "mahasiswa"
    return if (kategori == "karyawan") {
        hariTerlambat * 5000.toFloat()
    } else {
        hariTerlambat * 2000.toFloat()
    }
}

private fun shareData(context: Context, message:String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}



@Composable
fun StatusOption(label: String, isSelected: Boolean, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start= 8.dp)
        )
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String){
    if (isError) {
        Icon(imageVector =Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}



@Composable
fun ErrorHint(isError: Boolean){
    if (isError) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Asesment_mobproTheme {
        MainScreen(rememberNavController())
    }
}