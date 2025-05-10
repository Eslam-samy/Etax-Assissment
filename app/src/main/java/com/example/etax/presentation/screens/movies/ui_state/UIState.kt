package com.example.etax.presentation.screens.movies.ui_state

// UIState sealed class to handle different UI states (Loading, Success, Error)
sealed class UIState {
    data object Loading : UIState()
    data object Success : UIState()
    data class Error(val message: String) : UIState()
}