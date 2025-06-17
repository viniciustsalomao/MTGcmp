package dev.vinits.mtgcmp.foundation.network

import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val ktorfit = ktorfit {
    baseUrl(url = "https://api.magicthegathering.io/v1/")

    httpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}