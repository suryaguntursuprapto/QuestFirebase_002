package com.example.rdbms_20220140002.model

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val alamat: String,
    val jeniskelamin: String,
    val kelas: String,
    val angkatan: String,
){
    constructor(): this("","","","","","")
}