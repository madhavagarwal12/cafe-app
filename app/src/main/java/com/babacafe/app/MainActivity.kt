package com.babacafe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.babacafe.app.ui.navigation.BabaCafeNavGraph
import com.babacafe.app.ui.theme.BloomBlue500
import com.babacafe.app.ui.theme.BloomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BloomBlue500
                ) {
                    val navController = rememberNavController()
                    BabaCafeNavGraph(navController = navController)
                }
            }
        }
    }
}
