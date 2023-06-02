package com.albert.infinitespirit.detail.ui

import android.os.Bundle
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.albert.infinitespirit.nav.Screen

@Composable
fun DetailScreen(navController: NavController, args: Bundle) {
    // Tu código aquí...
    Button(onClick = { }) {
        Text("Ver fotos del ${args.getString(Screen.NAME)} - ${args.getInt(Screen.AGE)}")
    }
}