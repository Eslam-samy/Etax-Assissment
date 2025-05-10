package com.example.etax.presentation.screens.movies.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorComponent(message: String) {
    // Show the error message if there's an issue
    Text(text = "Error: $message")
}
