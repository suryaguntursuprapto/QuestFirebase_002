package com.example.rdbms_20220140002.Navigation

interface DestinasiNavigasi{
    val route: String
    val titleRes: String
}

object DestinasiHome: DestinasiNavigasi{
    override val route: String = "home"
    override val titleRes: String = "Home"
}

object DestinasiInsert: DestinasiNavigasi{
    override val route: String = "insert"
    override val titleRes: String = "Insert"
}

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail/{nim}"
    override val titleRes = "Detail Mahasiswa"
    const val nimArg = "nim"
}