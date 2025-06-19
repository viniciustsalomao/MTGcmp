package dev.vinits.mtgcmp.cards.domain.model

data class Filter(
    val colorFilter: List<ManaType>?,
    val typeFilter: CardType?,
)