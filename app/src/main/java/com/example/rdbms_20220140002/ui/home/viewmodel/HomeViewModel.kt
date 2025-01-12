package com.example.rdbms_20220140002.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rdbms_20220140002.Repository.RepositoryMhs
import com.example.rdbms_20220140002.model.Mahasiswa
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repositoryMhs: RepositoryMhs
):ViewModel() {
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            repositoryMhs.getAllMhs()
                .onStart {
                    mhsUiState = HomeUiState.Loading
                }
                .catch {
                    mhsUiState = HomeUiState.Error(it)
                }
                .collect {
                    mhsUiState = if (it.isEmpty()) {
                        HomeUiState.Error(Exception("Belum ada data mahasiswa"))
                    } else {
                        HomeUiState.Success(it)
                    }
                }
        }
    }

    fun deleteMhs(mahasiswa: Mahasiswa){
        viewModelScope.launch {
            try {
                repositoryMhs.deleteMhs(mahasiswa)
            }catch (e:Exception){
                mhsUiState = HomeUiState.Error(e)
            }
        }
    }

}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<Mahasiswa>) : HomeUiState()
    data class Error(val message: Throwable) : HomeUiState()
}