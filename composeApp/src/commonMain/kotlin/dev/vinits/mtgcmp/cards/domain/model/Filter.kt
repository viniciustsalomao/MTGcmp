package dev.vinits.mtgcmp.cards.domain.model

data class Filter(
    var nameFilter: String?,
    val colorFilter: List<ManaType>?,
    val typeFilter: CardType?,
)