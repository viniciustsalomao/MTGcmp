package dev.vinits.mtgcmp.cards.domain.model

data class Filter(
    val colorFilter: CardColorFilter?,
    val typeFilter: CardTypeFilter?,
)