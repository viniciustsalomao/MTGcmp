package dev.vinits.mtgcmp.cards.data.repository

import dev.vinits.mtgcmp.cards.data.network.cardsApi
import dev.vinits.mtgcmp.cards.data.network.toDomain
import dev.vinits.mtgcmp.cards.domain.model.Card
import dev.vinits.mtgcmp.cards.domain.repository.CardRepository
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepositoryImpl : CardRepository {
    override fun getCards(): Flow<Resource<List<Card>>> = flow {
        emit(Resource.Loading)

        try {
            val response = cardsApi.getCards()

            val cards = response.cards.map { card -> card.toDomain()}

            emit(Resource.Success(data = cards))
        } catch (e: Exception) {
            emit(Resource.Error)
        }
    }
}