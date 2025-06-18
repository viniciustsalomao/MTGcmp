package dev.vinits.mtgcmp.cards.domain.model

data class Card(
    val id: String?,
    val name: String,
    val manaCost: String,
    val type: String,
    val rarity: String,
    val set: String,
    val text: String?,
    val number: String?,
    val power: String?,
    val toughness: String?,
    val imageUrl: String?,
)
