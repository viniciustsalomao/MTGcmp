package dev.vinits.mtgcmp.cards.ui.CardsScreen

import dev.vinits.mtgcmp.cards.domain.model.CardSimple
import dev.vinits.mtgcmp.foundation.model.Resource

data class CardsUiState(
    val data: Resource<List<CardSimple>>
)