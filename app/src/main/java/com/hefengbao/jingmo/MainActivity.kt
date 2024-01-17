package com.hefengbao.jingmo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.hefengbao.jingmo.route.AppNavHost
import com.hefengbao.jingmo.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainActivityViewModel>()

        setContent {
            val appNavController = rememberNavController()

            val showLanding by viewModel.showLanding.collectAsState(initial = true)

            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (showLanding) {
                        LandingScreen()
                        viewModel.closeLanding()
                    } else {
                        AppNavHost(navController = appNavController)
                    }
                }
            }
        }
    }
}

@Composable
private fun LandingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "献给\n\n喜欢中华文字、文学、文化的人",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        )
    }
}