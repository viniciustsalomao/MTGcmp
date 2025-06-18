package dev.vinits.mtgcmp.cards.ui

import dev.vinits.mtgcmp.cards.domain.model.Card
import dev.vinits.mtgcmp.foundation.model.Resource

data class CardsUiState(
    val data: Resource<List<Card>>
)