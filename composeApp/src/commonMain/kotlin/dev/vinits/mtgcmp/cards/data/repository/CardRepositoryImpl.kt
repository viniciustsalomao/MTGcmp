package dev.vinits.mtgcmp.cards.data.repository

import dev.vinits.mtgcmp.cards.data.network.CardsApi
import dev.vinits.mtgcmp.cards.data.network.cardsApi
import dev.vinits.mtgcmp.cards.data.network.toDomain
import dev.vinits.mtgcmp.cards.domain.model.Card
import dev.vinits.mtgcmp.cards.domain.model.CardColorFilter
import dev.vinits.mtgcmp.cards.domain.model.CardDetails
import dev.vinits.mtgcmp.cards.domain.model.CardTypeFilter
import dev.vinits.mtgcmp.cards.domain.repository.CardRepository
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepositoryImpl(
    private val api: CardsApi
) : CardRepository {
    override fun getCards(
        colorFilter: CardColorFilter?,
        typeFilter: CardTypeFilter?,
    ): Flow<Resource<List<Card>>> = flow {
        emit(Resource.Loading)

        try {
            val response = api.getCards()

            val cards = response.cards.map { card -> card.toDomain()}

            emit(Resource.Success(data = cards))
        } catch (e: Exception) {
            emit(Resource.Error)
        }
    }

    override fun getCardDetails(id: String): Flow<Resource<CardDetails>> = flow {
        emit(Resource.Loading)

        try {
            val response = api.getCardDetails(id = id)

            val card = response.card.toDomain()

            emit(Resource.Success(data = card))
        } catch (e: Exception) {
            emit(Resource.Error)
        }
    }
}