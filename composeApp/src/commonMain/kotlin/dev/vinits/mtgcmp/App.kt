package dev.vinits.mtgcmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.vinits.mtgcmp.cards.ui.CardsScreen
import dev.vinits.mtgcmp.foundation.designSystem.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mtg_cmp_tutorial.composeapp.generated.resources.Res
import mtg_cmp_tutorial.composeapp.generated.resources.compose_multiplatform

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

                Column() {
                    Text(text = "Detalhes $id")
                }
            }
        }
    }
}