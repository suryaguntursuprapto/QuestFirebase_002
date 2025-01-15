package com.example.rdbms_20220140002.ui.home.pages

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rdbms_20220140002.model.Mahasiswa
import com.example.rdbms_20220140002.ui.PenyediaViewModel
import com.example.rdbms_20220140002.ui.home.viewmodel.DetailUiState
import com.example.rdbms_20220140002.ui.home.viewmodel.DetailViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    navigateToEdit: (String) -> Unit,
    modifier: Modifier = Modifier,
    nim: String,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(nim) {
        viewModel.getMhsByNim(nim)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("Detail Mhs") }) },

    ) { innerPadding ->
        DetailStatus(
            detailUiState = viewModel.mhsDetailUiState,
            retryAction = { viewModel.getMhsByNim(nim) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun DetailStatus(
    detailUiState: DetailUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoadingDetail(modifier = modifier.fillMaxSize())
        is DetailUiState.Success -> DetailLayout(
            mahasiswa = detailUiState.mahasiswa,
            modifier = modifier.fillMaxWidth()
        )
        is DetailUiState.Error -> OnErrorDetail(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
private fun OnErrorDetail(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading........")
    }
}

@Composable
private fun OnLoadingDetail(modifier: Modifier = Modifier) {
   Text("Error")
}

@Composable
fun DetailLayout(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "NIM: ${mahasiswa.nim}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Nama: ${mahasiswa.nama}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Alamat: ${mahasiswa.alamat}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Kelas: ${mahasiswa.kelas}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Angkatan: ${mahasiswa.angkatan}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Judul Skripsi: ${mahasiswa.judulskripsi}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Dosen Pembimbing 1: ${mahasiswa.DosenPembimbing1}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Dosen Pembimbing 2: ${mahasiswa.DosenPembimbing2}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}