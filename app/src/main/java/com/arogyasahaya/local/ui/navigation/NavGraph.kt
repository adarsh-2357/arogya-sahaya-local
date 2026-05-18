package com.arogyasahaya.local.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arogyasahaya.local.ui.HomeScreen
import com.arogyasahaya.local.ui.asha.AshaConnectScreen
import com.arogyasahaya.local.ui.emergency.EmergencyScreen
import com.arogyasahaya.local.ui.medicine.AddMedicineScreen
import com.arogyasahaya.local.ui.medicine.MedicineScreen
import com.arogyasahaya.local.ui.profile.MedicalProfileScreen
import com.arogyasahaya.local.ui.vitals.VitalLogScreen
import com.arogyasahaya.local.ui.vitals.VitalsChartScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Medicines : Screen("medicines")
    data object AddMedicine : Screen("add_medicine")
    data object Vitals : Screen("vitals")
    data object VitalsChart : Screen("vitals_chart")
    data object AshaConnect : Screen("asha_connect")
    data object Emergency : Screen("emergency")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToMedicines = {
                    navController.navigate(Screen.Medicines.route)
                },
                onNavigateToVitals = {
                    navController.navigate(Screen.Vitals.route)
                },
                onNavigateToAsha = {
                    navController.navigate(Screen.AshaConnect.route)
                },
                onNavigateToEmergency = {
                    navController.navigate(Screen.Emergency.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            MedicalProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Medicines.route) {
            MedicineScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToAddMedicine = {
                    navController.navigate(Screen.AddMedicine.route)
                }
            )
        }

        composable(Screen.AddMedicine.route) {
            AddMedicineScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Vitals.route) {
            VitalLogScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToChart = {
                    navController.navigate(Screen.VitalsChart.route)
                }
            )
        }

        composable(Screen.VitalsChart.route) {
            VitalsChartScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.AshaConnect.route) {
            AshaConnectScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Emergency.route) {
            EmergencyScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
