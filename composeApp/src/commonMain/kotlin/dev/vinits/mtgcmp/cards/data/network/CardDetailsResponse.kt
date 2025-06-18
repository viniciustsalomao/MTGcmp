package dev.vinits.mtgcmp.cards.data.network

import dev.vinits.mtgcmp.cards.domain.model.CardDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardDetailsBaseResponse(
    @SerialName("card")
    val card: CardDetailsResponse
) {
    @Serializable
    data class CardDetailsResponse(
        @SerialName("id")
        val id: String? = null,

        @SerialName("name")
        val name: String,

        @SerialName("names")
        val names: List<String>? = null,

        @SerialName("manaCost")
        val manaCost: String,

        @SerialName("cmc")
        val cmc: Float? = null,

        @SerialName("colors")
        val colors: List<String>? = null,

        @SerialName("colorIdentity")
        val colorIdentity: List<String>? = null,

        @SerialName("type")
        val type: String,

        @SerialName("supertypes")
        val superTypes: List<String>? = null,

        @SerialName("types")
        val types: List<String>,

        @SerialName("subtypes")
        val subTypes: List<String>? = null,

        @SerialName("rarity")
        val rarity: String,

        @SerialName("set")
        val set: String,

        @SerialName("setName")
        val setName: String,

        @SerialName("text")
        val text: String? = null,

        @SerialName("artist")
        val artist: String,

        @SerialName("number")
        val number: String? = null,

        @SerialName("power")
        val power: String? = null,

        @SerialName("toughness")
        val toughness: String? = null,

        @SerialName("imageUrl")
        val imageUrl: String? = null,

        @SerialName("layout")
        val layout: String,

        @SerialName("rulings")
        val rulings: List<RulingResponse>? = null
    ) {

        @Serializable
        data class RulingResponse(
            @SerialName("date")
            val date: String,

            @SerialName("text")
            val text: String
        )
    }
}

fun CardDetailsBaseResponse.CardDetailsResponse.toDomain() = CardDetails(
    id = id,
    name = name,
    names = names ?: emptyList(),
    manaCost = manaCost ?: "",
    cmc = cmc,
    colors = colors,
    colorIdentity = colorIdentity,
    type = type,
    types = types,
    rarity = rarity,
    set = set,
    text = text ?: "",
    artist = artist ?: "",
    number = number ?: "",
    power = power ?: "",
    toughness = toughness ?: "",
    layout = layout,
    imageUrl = imageUrl ?: "",
    rulings = rulings?.map { rulingResponse -> rulingResponse.toDomain() }
)

fun CardDetailsBaseResponse.CardDetailsResponse.RulingResponse.toDomain() = CardDetails.Ruling(
    date = date,
    text = text
)
