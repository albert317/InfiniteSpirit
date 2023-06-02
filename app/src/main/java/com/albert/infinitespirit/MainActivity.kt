package com.albert.infinitespirit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.albert.feature_home.presentation.DemoScreen
import com.albert.infinitespirit.addtasks.ui.TasksViewModel
import com.albert.infinitespirit.detail.ui.DetailScreen
import com.albert.infinitespirit.home.ui.HomeScreen
import com.albert.infinitespirit.nav.Screen
import com.albert.infinitespirit.ui.theme.InfiniteSpiritTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tasksViewModel: TasksViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    NavigateApp()
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

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
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