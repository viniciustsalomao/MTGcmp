package dev.vinits.mtgcmp.cards.data.network

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class CardsApiTest {

    @Test
    fun testCardsApi() {
        runTest {
            val response = cardsApi.getCards()

            println(response)
        }
    }

    @Test
    fun testCardDetailsApi() {
        runTest {
            val response = cardsApi.getCardDetails(id = "386616")

            println(response)
        }
    }
}