package dev.vinits.mtgcmp.cards.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.vinits.mtgcmp.foundation.model.Resource

@Composable
fun CardsScreen(
    viewModel: CardsViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is Resource.Success<*> -> {

        }

        Resource.Error -> {
            Text(text = "An unexpected error occurred")
        }

        Resource.Loading -> {
            CircularProgressIndicator()
        }
    }
}