package dev.vinits.mtgcmp.cards.data.network

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class CardsApiTest {

    @Test
    fun testApi() {
        runTest {
            val response = cardsApi.getCards()

            println(response)
        }
    }
}