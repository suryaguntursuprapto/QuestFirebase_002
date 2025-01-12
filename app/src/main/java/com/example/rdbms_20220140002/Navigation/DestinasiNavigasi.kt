package com.example.rdbms_20220140002.Navigation

interface DestinasiNavigasi{
    val route: String
    val titleRes: String
}

object DestinasiHome: DestinasiNavigasi{
    override val route: String = "home"
    override val titleRes: String = "Home"
}