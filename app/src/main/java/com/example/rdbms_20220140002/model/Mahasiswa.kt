package com.example.rdbms_20220140002.model

import kotlinx.coroutines.flow.Flow

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String,
    val judulskripsi: String,
    val DosenPembimbing1: String,
    val DosenPembimbing2: String,
){
    constructor(): this("","","","","","","","","")
}
data class MahasiswaDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Mahasiswa
)