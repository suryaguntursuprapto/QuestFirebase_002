package com.example.rdbms_20220140002.ui.home.viewmodel

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rdbms_20220140002.Repository.RepositoryMhs
import com.example.rdbms_20220140002.model.Mahasiswa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(private val mhsRepository: RepositoryMhs) : ViewModel() {
    var mhsDetailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMhsByNim(nim: String) {
        viewModelScope.launch {
            mhsDetailUiState = DetailUiState.Loading
            mhsDetailUiState = try {
                val mahasiswa = mhsRepository.getMhs(nim)
                DetailUiState.Success(Mahasiswa())
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}