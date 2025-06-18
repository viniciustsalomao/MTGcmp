package dev.vinits.mtgcmp.foundation.network

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import dev.vinits.mtgcmp.cards.data.network.CardsApi
import dev.vinits.mtgcmp.cards.data.network.createCardsApi
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkModule = module { 
    single<Ktorfit> {
        ktorfit {
            baseUrl(url = "https://api.magicthegathering.io/v1/")

            httpClient {
                install(ContentNegotiation) {
                    json(
                        kotlinx.serialization.json.Json {
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                }
            }
        }
    }

    single<CardsApi> {
        val ktorfit: Ktorfit = get()
        ktorfit.createCardsApi()
    }
}
