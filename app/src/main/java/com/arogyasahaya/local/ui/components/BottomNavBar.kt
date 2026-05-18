package com.arogyasahaya.local.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arogyasahaya.local.ui.navigation.Screen

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainScreens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Medicines,
        BottomNavItem.Vitals,
        BottomNavItem.AshaConnect,
        BottomNavItem.Emergency
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            mainScreens.forEach { screen ->
                BottomNavItem(
                    screen = screen,
                    isSelected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavItem(
    screen: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tintColor = if (screen == BottomNavItem.Emergency) {
        if (isSelected) Color.White else Color(0xFFD32F2F)
    } else {
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
    }

    val backgroundColor = if (screen == BottomNavItem.Emergency && isSelected) {
        Color(0xFFD32F2F)
    } else {
        Color.Transparent
    }

    Box(
        modifier = Modifier
            .weight(1f)
            .height(80.dp)
            .background(backgroundColor)
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.Tab
            ),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.layout.Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.label,
                tint = tintColor,
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = screen.label,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = tintColor,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    data object Home : BottomNavItem(
        route = Screen.Home.route,
        label = "Home",
        icon = Icons.Default.Home
    )

    data object Profile : BottomNavItem(
        route = Screen.Profile.route,
        label = "Profile",
        icon = Icons.Default.Person
    )

    data object Medicines : BottomNavItem(
        route = Screen.Medicines.route,
        label = "Medicines",
        icon = Icons.Default.LocalHospital
    )

    data object Vitals : BottomNavItem(
        route = Screen.Vitals.route,
        label = "Vitals",
        icon = Icons.Default.Favorite
    )

    data object AshaConnect : BottomNavItem(
        route = Screen.AshaConnect.route,
        label = "ASHA",
        icon = Icons.Default.CalendarMonth
    )

    data object Emergency : BottomNavItem(
        route = Screen.Emergency.route,
        label = "SOS",
        icon = Icons.Default.Warning
    )
}
