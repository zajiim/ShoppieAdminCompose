package com.example.shoppieadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.shoppieadmin.core.navigation.Routes
import com.example.shoppieadmin.core.navigation.ShoppieNavGraph
import com.example.shoppieadmin.ui.theme.PrimaryColor
import com.example.shoppieadmin.ui.theme.ShoppieAdminTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValue ->
                    ShoppieNavGraph(
                        startDestination = Routes.AuthNavigation.route,
                        modifier = Modifier
                            .padding(paddingValue).background(PrimaryColor)
                    )
                }
            }
        }
    }
}
