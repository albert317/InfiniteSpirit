package com.albert.infinitespirit.home.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.albert.infinitespirit.nav.Screen

@Composable
fun DemoScreenOne(navController: NavController) {
    Button(onClick = {
        val args = mapOf(Screen.AGE to 200324, Screen.NAME to "ALBERT")
        navController.navigate(Screen.Detail.withArgs(args))
    }) {
        Text("Ir a detalles del producto")
    }
}

@Composable
fun DemoScreenOne2(navController: NavController) {
    Button(onClick = {
        val args = mapOf("age" to 200324)
        navController.navigate("Feature/123456")
    }) {
        Text("Ir a detalles del producto")
    }
}