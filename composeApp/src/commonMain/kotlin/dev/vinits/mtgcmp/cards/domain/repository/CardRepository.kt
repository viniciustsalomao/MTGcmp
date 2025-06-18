package dev.vinits.mtgcmp.cards.domain.repository

import dev.vinits.mtgcmp.cards.domain.model.Card
import dev.vinits.mtgcmp.cards.domain.model.CardColorFilter
import dev.vinits.mtgcmp.cards.domain.model.CardDetails
import dev.vinits.mtgcmp.cards.domain.model.CardTypeFilter
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getCards(
        colorFilter: CardColorFilter?,
        typeFilter: CardTypeFilter?,
    ): Flow<Resource<List<Card>>>

    fun getCardDetails(id: String): Flow<Resource<CardDetails>>
}