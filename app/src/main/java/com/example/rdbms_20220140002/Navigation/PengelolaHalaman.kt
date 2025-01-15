package com.example.rdbms_20220140002.Navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rdbms_20220140002.ui.home.pages.DetailScreen
import com.example.rdbms_20220140002.ui.home.pages.HomeScreen
import com.example.rdbms_20220140002.ui.home.pages.InsertMhsView

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                }, onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route.replace("{nim}", nim)}")
                }
            )
        }

        composable(
            route = DestinasiDetail.route,
            arguments = listOf(navArgument(DestinasiDetail.nimArg) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val nimArg = backStackEntry.arguments?.getString(DestinasiDetail.nimArg)
            DetailScreen(
                nim = nimArg ?: "",
                navigateBack = { navController.navigateUp() },
            )
        }

        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = {navController.popBackStack()},
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
    }
}