package dev.vinits.mtgcmp.cards.domain.repository

import dev.vinits.mtgcmp.cards.domain.model.CardSimple
import dev.vinits.mtgcmp.cards.domain.model.CardDetails
import dev.vinits.mtgcmp.cards.domain.model.CardType
import dev.vinits.mtgcmp.cards.domain.model.ManaType
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getCards(
        colorFilter: List<ManaType>?,
        typeFilter: CardType?,
    ): Flow<Resource<List<CardSimple>>>

    fun getCardDetails(id: String): Flow<Resource<CardDetails>>
}