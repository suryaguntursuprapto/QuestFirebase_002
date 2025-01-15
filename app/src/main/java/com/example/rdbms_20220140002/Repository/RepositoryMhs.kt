package com.example.rdbms_20220140002.Repository

import com.example.rdbms_20220140002.model.Mahasiswa
import com.example.rdbms_20220140002.model.MahasiswaDetailResponse
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMhs(): Flow<List<Mahasiswa>>
    fun getMhs(nim: String): Flow<Mahasiswa>
    suspend fun deleteMhs(mahasiswa: Mahasiswa)
    suspend fun updateMhs(mahasiswa: Mahasiswa)
}