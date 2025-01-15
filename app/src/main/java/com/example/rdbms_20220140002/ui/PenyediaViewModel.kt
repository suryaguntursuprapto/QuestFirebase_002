package com.example.rdbms_20220140002.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rdbms_20220140002.MahasiswaApplications
import com.example.rdbms_20220140002.ui.home.viewmodel.DetailViewModel
import com.example.rdbms_20220140002.ui.home.viewmodel.HomeViewModel
import com.example.rdbms_20220140002.ui.home.viewmodel.InsertViewModel


object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                mahasiswaApp().container.repositoryMhs
            )
        }
        initializer {
            InsertViewModel(
                mahasiswaApp().container.repositoryMhs
            )
        }
        initializer {
            DetailViewModel(
                mahasiswaApp().container.repositoryMhs
            )
        }
    }
}

fun CreationExtras.mahasiswaApp():MahasiswaApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)