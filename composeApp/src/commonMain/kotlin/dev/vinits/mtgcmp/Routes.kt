package dev.vinits.mtgcmp

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Cards : Routes


    @Serializable
    data class CardDetails(val id: String): Routes
}