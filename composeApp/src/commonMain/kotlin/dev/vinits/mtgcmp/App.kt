package dev.vinits.mtgcmp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.vinits.mtgcmp.cards.domain.model.CardDetails
import dev.vinits.mtgcmp.cards.ui.CardDetailsScreen.CardDetailsScreen
import dev.vinits.mtgcmp.cards.ui.CardDetailsScreen.CardDetailsViewModel
import dev.vinits.mtgcmp.cards.ui.CardsScreen.CardsScreen
import dev.vinits.mtgcmp.foundation.designSystem.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    AppTheme {
        NavHost(
            navController = navController,
            startDestination = Routes.Cards,
        ) {
            composable<Routes.Cards> {
                CardsScreen(
                    onNavigateToDetails = { id ->
                        navController.navigate(Routes.CardDetails(id))
                    }
                )
            }

            composable<Routes.CardDetails> { backStack ->
                val id = backStack.toRoute<Routes.CardDetails>().id

                CardDetailsScreen(
                    cardId = id,
                    onGoBack = { ->
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}