package com.example.composeexample

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeexample.ui.MainScreen
import com.example.composeexample.ui.MainViewModel
import com.example.composeexample.ui.theme.ComposeExampleTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExampleTheme {
                val viewModel: MainViewModel = viewModel()
                MainScreen(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ComposeExampleTheme {
        val viewModel: MainViewModel = viewModel()
        MainScreen(viewModel)
    }
}

