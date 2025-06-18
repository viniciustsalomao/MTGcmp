package dev.vinits.mtgcmp.cards.domain.model

enum class CardColorFilter(
    private val value: String,
) {
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    BLACK("black"),
    WHITE("white")
}