package dev.vinits.mtgcmp.cards.data.network

import dev.vinits.mtgcmp.cards.domain.model.CardSimple
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardsResponse(
    @SerialName("cards")
    val cards: List<CardResponse>
) {
    @Serializable
    data class CardResponse(
        @SerialName("id")
        val id: String? = null,

        @SerialName("name")
        val name: String,

        @SerialName("manaCost")
        val manaCost: String?,

        @SerialName("type")
        val type: String,

        @SerialName("rarity")
        val rarity: String,

        @SerialName("set")
        val set: String,

        @SerialName("text")
        val text: String? = null,

        @SerialName("number")
        val number: String? = null,

        @SerialName("power")
        val power: String? = null,

        @SerialName("toughness")
        val toughness: String? = null,

        @SerialName("imageUrl")
        val imageUrl: String? = null,

        @SerialName("loyalty")
        val loyalty: String? = null,
    )
}

fun CardsResponse.CardResponse.toDomain() = CardSimple(
    id = id,
    name = name,
    manaCost = manaCost,
    type = type,
    rarity = rarity,
    set = set,
    text = text,
    number = number,
    power = power,
    toughness = toughness,
    imageUrl = imageUrl,
    loyalty = loyalty
)
