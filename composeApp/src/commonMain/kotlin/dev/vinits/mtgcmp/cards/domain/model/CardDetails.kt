package dev.vinits.mtgcmp.cards.domain.model

data class CardDetails(
    val id: String?,
    val name: String,
    val names: List<String>?,
    val manaCost: String,
    val cmc: Float?,
    val colors: List<String>?,
    val colorIdentity: List<String>?,
    val type: String,
    val types: List<String>,
    val rarity: String,
    val set: String,
    val text: String?,
    val artist: String,
    val number: String?,
    val power: String?,
    val toughness: String?,
    val imageUrl: String?,
    val layout: String,
    val rulings: List<Ruling>?,
) {
    data class Ruling(
        val date: String,
        val text: String
    )
}
