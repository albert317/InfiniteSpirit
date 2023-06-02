package com.albert.feature_home.presentation

import android.os.Bundle
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun DemoScreen(navController: NavController,args:Bundle) {
    // Tu código aquí...
    Button(onClick = { }) {
        Text("Holaaaaaa ${args.getInt("age")}")
    }
}