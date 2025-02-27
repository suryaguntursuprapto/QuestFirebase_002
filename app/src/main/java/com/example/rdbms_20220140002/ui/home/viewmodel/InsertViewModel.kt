package com.example.rdbms_20220140002.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rdbms_20220140002.Repository.RepositoryMhs
import com.example.rdbms_20220140002.model.Mahasiswa
import kotlinx.coroutines.launch

class InsertViewModel (
    private val mhs: RepositoryMhs
): ViewModel(){
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set

    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    // Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }

    //validasi data input pengguna
    fun validateFields(): Boolean{
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isEmpty()) "NIM Tidak Boleh Kosong" else null,
            nama = if (event.nama.isEmpty()) "Nama Tidak Boleh Kosong" else null,
            alamat = if (event.alamat.isEmpty()) "Alamat Tidak Boleh Kosong" else null,
            jenisKelamin = if (event.jenisKelamin.isEmpty()) "Jenis Kelamin Tidak Boleh Kosong" else null,
            kelas = if (event.kelas.isEmpty()) "Kelas Tidak Boleh Kosong" else null,
            angkatan = if (event.angkatan.isEmpty()) "Angkatan Tidak Boleh Kosong" else null,
            judulskripsi = if (event.judulskripsi.isEmpty()) "Judul Skripsi Tidak Boleh Kosong" else null,
            DosenPembimbing1 = if (event.DosenPembimbing1.isEmpty()) "Dosen Pembimbing 1 Tidak Boleh Kosong" else null,
            DosenPembimbing2 = if (event.DosenPembimbing2.isEmpty()) "Dosen Pembimbing 2 Tidak Boleh Kosong" else null
        )

        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun insertMhs() {
        if (validateFields()) {
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    mhs.insertMhs(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data Berhasil Disimpan")
                } catch (e: Exception) {
                    uiState = FormState.Error("Data Gagal Disimpan")
                }
            }
        } else {
            uiState = FormState.Error("Data Tidak Valid")
        }
    }

    fun resetForm() {
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }

    fun resetSnackBarMessage() {
        uiState = FormState.Idle
    }
}

sealed class FormState {
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}

data class InsertUiState (
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState()
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val alamat: String? = null,
    val jenisKelamin: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
    val judulskripsi: String? = null,
    val DosenPembimbing1: String? = null,
    val DosenPembimbing2: String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && alamat == null &&
                jenisKelamin == null && kelas == null && angkatan == null
                && judulskripsi == null && DosenPembimbing1 == null && DosenPembimbing2 == null
    }
}

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = "",
    val judulskripsi: String = "",
    val DosenPembimbing1: String = "",
    val DosenPembimbing2: String = ""
)

// Menyimpan Input Form Ke Dalam Entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan,
    judulskripsi = judulskripsi,
    DosenPembimbing1 = DosenPembimbing1,
    DosenPembimbing2 = DosenPembimbing2
)