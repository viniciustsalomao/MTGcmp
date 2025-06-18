package dev.vinits.mtgcmp.cards.data.repository

import androidx.compose.material3.Card
import app.cash.turbine.test
import dev.vinits.mtgcmp.cards.data.network.CardsResponse
import dev.vinits.mtgcmp.cards.data.repository.fake.FakeCardsApi
import dev.vinits.mtgcmp.cards.domain.model.Card
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CardRepositoryImplTest {

    private val cardsResponse = CardsResponse(
        cards = listOf(
            CardsResponse.CardResponse(
                id = "",
                name = "",
                manaCost = "",
                type = "",
                rarity = "",
                set = "",
                text = "",
                number = "",
                power = "",
                toughness = "",
                imageUrl = ""
            )
        )
    )


    @Test
    fun getCards_emitsLoadingAndSuccess_whenApiCallSucceeds() {
        val api = FakeCardsApi(cardsResponse = cardsResponse)
        val repository = CardRepositoryImpl(api = api)

        val expectedCards = listOf(
            Card(
                id = "",
                name = "",
                manaCost = "",
                type = "",
                rarity = "",
                set = "",
                text = "",
                number = "",
                power = "",
                toughness = "",
                imageUrl = ""
            )
        )

        runTest {
            repository.getCards(
                colorFilter = null,
                typeFilter = null
            ).test {
                assertEquals(
                    expected = Resource.Loading,
                    actual = awaitItem()
                )

                assertEquals(
                    expected = Resource.Success(data = expectedCards),
                    actual = awaitItem()
                )

                awaitComplete()
            }
        }
    }

    @Test
    fun getCards_emitsLoadingAndError_whenApiFails() {
        val api = FakeCardsApi(cardsResponse = cardsResponse)
        val repository = CardRepositoryImpl(api = api)

        api.shouldFailNextCardsRequest()

        runTest {
            repository.getCards(
                colorFilter = null,
                typeFilter = null
            ).test {
                assertEquals(
                    expected = Resource.Loading,
                    actual = awaitItem()
                )

                assertEquals(
                    expected = Resource.Error,
                    actual = awaitItem()
                )

                awaitComplete()
            }
        }
    }
}