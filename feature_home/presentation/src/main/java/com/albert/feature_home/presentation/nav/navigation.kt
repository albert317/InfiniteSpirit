package com.albert.feature_home.presentation.nav

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albert.feature_home.presentation.DemoScreen
import com.albert.feature_home.presentation.ui.init.HomeScreen

sealed class FeatureScreen(val route: String) {
    companion object {
        const val ID_PRODUCT = "idProduct"
    }

    object InitScreen : FeatureScreen("feature_home_init")

    object DetailScreen : FeatureScreen("feature_home_detail/{$ID_PRODUCT}}")

    fun withArgs(args: Map<String, Any>): String {
        var routeResult = route
        args.forEach { (key, value) ->
            val placeholder = "{$key}"
            routeResult = routeResult.replace(placeholder, value.toString())
        }
        return routeResult
    }
}

@Composable
fun NavigateFeature(args: Bundle) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController, startDestination = "DemoScreen") {
        composable("DemoScreen") {
            DemoScreen(navController, args)
        }
    }
}

@Composable
fun NavigateFeatureHome() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = FeatureScreen.InitScreen.route) {
        composable(FeatureScreen.InitScreen.route) {
            HomeScreen()
        }
    }
}