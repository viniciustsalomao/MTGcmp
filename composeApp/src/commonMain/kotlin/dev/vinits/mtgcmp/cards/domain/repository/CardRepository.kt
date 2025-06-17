package dev.vinits.mtgcmp.cards.domain.repository

import dev.vinits.mtgcmp.cards.domain.model.Card
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getCards(): Flow<Resource<List<Card>>>
}