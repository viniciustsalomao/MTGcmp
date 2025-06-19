package dev.vinits.mtgcmp.cards.data.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import dev.vinits.mtgcmp.cards.domain.model.CardType
import dev.vinits.mtgcmp.cards.domain.model.ManaType

interface CardsApi {

    @GET("cards")
    suspend fun getCards(
        @Query("colors") colorFilter: String?,
        @Query("type") typeFilter: String?,
    ): CardsResponse

    @GET("cards/{cardId}/")
    suspend fun getCardDetails(@Path("cardId") id: String): CardDetailsBaseResponse
}