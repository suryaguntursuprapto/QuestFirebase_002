package com.example.rdbms_20220140002.DI

import com.example.rdbms_20220140002.Repository.NetworkRepositoryMhs
import com.example.rdbms_20220140002.Repository.RepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val repositoryMhs:RepositoryMhs
}

class MahasiswaContainer:AppContainer{
    private val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepositoryMhs(firestore)
    }


}