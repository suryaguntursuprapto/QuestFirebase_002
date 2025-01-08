package com.example.rdbms_20220140002

import android.app.Application
import com.example.rdbms_20220140002.DI.AppContainer
import com.example.rdbms_20220140002.DI.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: MahasiswaContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}