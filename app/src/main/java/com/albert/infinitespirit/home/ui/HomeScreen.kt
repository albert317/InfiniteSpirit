package com.albert.infinitespirit.home.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.albert.infinitespirit.nav.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Button(onClick = {
        val args = mapOf(Screen.AGE to 200324, Screen.NAME to "ALBERT")
        navController.navigate(Screen.Detail.withArgs(args))
    }) {
        Text("Ir a detalles del producto")
    }
}