package dev.vinits.mtgcmp.cards.data.repository.fake

import dev.vinits.mtgcmp.cards.data.network.CardDetailsBaseResponse
import dev.vinits.mtgcmp.cards.data.network.CardsApi
import dev.vinits.mtgcmp.cards.data.network.CardsResponse
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.delay

class FakeCardsApi(
    private val cardsResponse: CardsResponse? = null,
    private val cardDetailsResponse: CardDetailsBaseResponse? = null,
) : CardsApi {

    private var failNextCardsRequest = false
    private var failNextCardDetailsRequest = false

    override suspend fun getCards(): CardsResponse {
        delay(1000)

        if (failNextCardsRequest) {
            failNextCardsRequest = false
            throw Exception()
        }

        return cardsResponse!!
    }

    override suspend fun getCardDetails(id: String): CardDetailsBaseResponse {
        delay(1000)

        if (failNextCardDetailsRequest) {
            failNextCardDetailsRequest = false
            throw JsonConvertException(message = "")
        }

        return cardDetailsResponse!!
    }

    fun shouldFailNextCardsRequest() {
        failNextCardsRequest = true
    }

    fun shouldFailNextCardDetailsRequest() {
        failNextCardDetailsRequest = true
    }
}