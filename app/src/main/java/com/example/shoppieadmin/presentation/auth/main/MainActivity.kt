package com.example.shoppieadmin.presentation.auth.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.shoppieadmin.R
import com.example.shoppieadmin.core.navigation.ShoppieNavGraph
import com.example.shoppieadmin.ui.theme.PrimaryColor
import com.example.shoppieadmin.ui.theme.ShoppieAdminTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = ContextCompat.getColor(this, R.color.primary),
                darkScrim = ContextCompat.getColor(this, R.color.primary)
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = ContextCompat.getColor(this, R.color.primary),
                darkScrim = ContextCompat.getColor(this, R.color.primary)
            )
        )
        setContent {
            ShoppieAdminTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValue ->

                    val startDestination = viewModel.startDestination
                    ShoppieNavGraph(
//                        startDestination = Routes.AuthNavigation.route,
                        startDestination = startDestination,
                        modifier = Modifier
                            .padding(paddingValue)
                            .background(PrimaryColor)
                    )
                }
            }
        }
    }
}
