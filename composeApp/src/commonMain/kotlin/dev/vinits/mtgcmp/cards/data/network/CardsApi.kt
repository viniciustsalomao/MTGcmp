package dev.vinits.mtgcmp.cards.data.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface CardsApi {

    @GET("cards")
    suspend fun getCards(): CardsResponse

    @GET("cards/{cardId}/")
    suspend fun  getCardDetails(@Path("cardId") id: String): CardDetailsBaseResponse
}