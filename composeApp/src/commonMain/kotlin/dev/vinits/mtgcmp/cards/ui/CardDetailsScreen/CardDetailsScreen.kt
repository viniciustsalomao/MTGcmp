package dev.vinits.mtgcmp.cards.ui.CardDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack
import dev.vinits.mtgcmp.cards.ui.CardsScreen.CardsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailsScreen(
    cardId: String,
    viewModel: CardsViewModel = koinViewModel(parameters = { parametersOf(cardId) }),
    onGoBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

//region Body
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "MTG Cards",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(onClick = { onGoBack()}) {
                            Icon(
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = TablerIcons.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                )
            }
        }
    ) {
        // TODO: criar o body
    }
    //endregion

}