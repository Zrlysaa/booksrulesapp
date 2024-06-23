package org.d3if3014.asesment_mobpro.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3014.asesment_mobpro.R
import org.d3if3014.asesment_mobpro.navigation.Screen
import org.d3if3014.asesment_mobpro.ui.theme.Asesment_mobproTheme
import java.text.NumberFormat
import java.util.Locale

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
                        Text(text = stringResource(id = R.string.page_hitung),  fontFamily = FontFamily(Font(R.font.poppins)))
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
    var gaji by remember { mutableStateOf("") }
    var gajiError by remember { mutableStateOf(false) }
    var gajiTambahan by remember { mutableStateOf("") } // Defaultnya adalah "0"
    var gajiTambahanError by remember { mutableStateOf(false) }
    var hutang by remember { mutableStateOf("") }
    var hutangError by remember { mutableStateOf(false) }
    var zakat by remember { mutableStateOf(0f) }
    var isCalculated by remember { mutableStateOf(false) } // State untuk menandai apakah perhitungan telah dilakukan

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
            style = MaterialTheme.typography.bodyMedium,  fontFamily = FontFamily(Font(R.font.poppins)),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = gaji ,  onValueChange = {gaji = it},
            label = { Text(text = stringResource(id = R.string.nama),  fontFamily = FontFamily(Font(R.font.poppins))) },
            isError = gajiError,
            leadingIcon = { IconPicker(gajiError, "Rp") },
            supportingText = { ErrorHint(gajiError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )

        OutlinedTextField(
            value = gajiTambahan,
            onValueChange = { gajiTambahan = it },
            label = { Text(text = stringResource(id = R.string.nim), fontFamily = FontFamily(Font(R.font.poppins))) },
            isError = gajiTambahanError,
            leadingIcon = { IconPicker(gajiTambahanError, "Rp") },
            supportingText = { ErrorHint(gajiTambahanError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(value = hutang, onValueChange = {hutang = it},
            label = { Text(text = stringResource(id = R.string.terlambat),  fontFamily = FontFamily(Font(R.font.poppins))) },
            isError = hutangError,
            leadingIcon = { IconPicker(hutangError, "Rp") },
            supportingText = { ErrorHint(hutangError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    gajiError = (gaji.isBlank() || gaji == "0")
                    hutangError = (hutang.isBlank() || hutang == "0")
                    if (gajiTambahan.isBlank()) {
                        gajiTambahan = "0"
                    }
                    if (!gajiError && !hutangError) {
                        val gajiInt = gaji.toIntOrNull() ?: 0
                        val gajiTambahanInt = gajiTambahan.toIntOrNull() ?: 0
                        val hutangInt = hutang.toIntOrNull() ?: 0
                        Log.d("Button_Clicked", "Gaji: $gajiInt, Gaji Tambahan: $gajiTambahanInt, Hutang: $hutangInt")
                        zakat = hitungZakat(gajiInt, gajiTambahanInt, hutangInt)
                        isCalculated = true
                    }
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }

            // Tombol reset
            if (isCalculated) { // Tampilkan tombol reset jika perhitungan telah dilakukan
                Button(
                    onClick = {
                        // Reset semua nilai dan state
                        gaji = ""
                        gajiTambahan = "" // Set ulang ke "0" saat di-reset
                        hutang = ""
                        zakat = 0f
                        isCalculated = false
                        // Atur pesan kesalahan ke false untuk menghilangkan pesan kesalahan yang mungkin muncul
                        gajiError = false
                        gajiTambahanError = false
                        hutangError = false
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.reset),  fontFamily = FontFamily(Font(R.font.poppins)))
                }
            }
        }

        // Tampilkan hasil perhitungan jika sudah dihitung
        if (isCalculated) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )

            // Cek apakah gaji lebih besar dari hutang
            if (gaji.toInt() > hutang.toInt()) {
                // Menampilkan hasil zakat jika gaji lebih besar dari hutang
                Text(
                    text = "Zakat yang disalurkan: Rp ${formatZakatToIDR(zakat)}" ,
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(onClick = {
                    shareData(
                        context,
                        context.getString(R.string.bagikan_template, hutang, zakat)
                    )
                },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.bagikan),  fontFamily = FontFamily(Font(R.font.poppins)))
                }
            } else {
                // Menampilkan pesan bahwa pengguna belum wajib membayar zakat
                Text(
                    text = "Anda belum wajib membayar zakat. Lunasi hutang Anda terlebih dahulu." ,
                    style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily(Font(R.font.poppins))
                )
            }
        }

    }
}


private fun hitungZakat(gaji: Int, gajiTambahan: Int, hutang: Int): Float {
    val totalGaji = if (gajiTambahan == 0) gaji else gaji + gajiTambahan // Jika gajiTambahan == 0, maka gunakan gaji pokok saja, jika tidak, tambahkan gajiTambahan
    val totalSetelahHutang = totalGaji - hutang // Hitung total gaji setelah dikurangi hutang

    val zakat: Float = if (totalSetelahHutang > 0) {
        totalSetelahHutang * 0.025f // Hitung zakat jika totalSetelahHutang > 0
    } else {
        0f // Jika totalSetelahHutang <= 0, maka zakat adalah 0
    }
    return zakat
}

private fun formatZakatToIDR(zakat: Float): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Buat formatter untuk mata uang Rupiah (IDR)
    return formatter.format(zakat.toDouble()) // Kembalikan nilai zakat yang diformat sebagai IDR
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
        Text(text = stringResource(id = R.string.input_invalid),  fontFamily = FontFamily(Font(R.font.poppins)))
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
