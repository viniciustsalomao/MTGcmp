package dev.vinits.mtgcmp.cards.ui.CardDetailsScreen

import dev.vinits.mtgcmp.cards.domain.model.CardDetails
import dev.vinits.mtgcmp.cards.domain.model.CardSimple
import dev.vinits.mtgcmp.foundation.model.Resource

data class CardDatailsUiState(
    val data: Resource<CardDetails>
)