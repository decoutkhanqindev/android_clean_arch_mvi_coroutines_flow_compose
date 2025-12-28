package com.decoutkhanqindev.mvi_learning.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.decoutkhanqindev.mvi_learning.presentation.theme.Mvi_learningTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Mvi_learningTheme {
        MviLearningApp(modifier = Modifier.fillMaxSize())
      }
    }
  }
}