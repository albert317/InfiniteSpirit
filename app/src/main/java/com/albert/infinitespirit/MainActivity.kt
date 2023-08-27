package com.albert.infinitespirit

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.albert.feature_home.presentation.nav.NavigateFeature
import com.albert.feature_home.presentation.nav.NavigateFeatureHome
import com.albert.infinitespirit.detail.ui.DetailScreen
import com.albert.infinitespirit.home.ui.DemoScreenOne
import com.albert.infinitespirit.home.ui.DemoScreenOne2
import com.albert.infinitespirit.nav.Screen
import com.albert.infinitespirit.ui.theme.InfiniteSpiritTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.navigationBarColor = Color.BLACK

        setContent {
            InfiniteSpiritTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //TasksScreen(tasksViewModel)
                    //TasksScreen()
                    //Greeting("Android")
                    NavigatePrincipal()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InfiniteSpiritTheme {
        Greeting("Android")
    }
}

@Composable
fun NavigateApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.NavFeatureHome.route) {
        composable(Screen.NavFeatureHome.route) { DemoScreenOne(navController) }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(Screen.AGE) { type = NavType.IntType },
                navArgument(Screen.NAME) { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.let { DetailScreen(navController, args = it) }
        }
    }
}

@Composable
fun NavigateGeneral() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.NavFeatureHome.route) {
        composable(Screen.NavFeatureHome.route) { DemoScreenOne2(navController) }
        composable(
            route = "Feature/{age}",
            arguments = listOf(navArgument("age") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.let { NavigateFeature(args = it) }
        }
    }
}


@Composable
fun NavigatePrincipal() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.NavFeatureHome.route) {
        composable(Screen.NavFeatureHome.route) { NavigateFeatureHome() }
    }
}