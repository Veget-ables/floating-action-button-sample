package com.example.floating_action_button_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.floating_action_button_sample.ui.CreateScreen
import com.example.floating_action_button_sample.ui.DetailScreen
import com.example.floating_action_button_sample.ui.TopScreen
import com.example.floating_action_button_sample.ui.theme.FloatingactionbuttonsampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloatingactionbuttonsampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "top") {
                        composable("top") {
                            TopScreen(
                                openCreate = { navController.navigate("create") },
                                openDetail = { navController.navigate("detail") }
                            )
                        }
                        composable("create") {
                            CreateScreen()
                        }
                        composable("detail") {
                            DetailScreen()
                        }
                    }
                }
            }
        }
    }
}
