package dev.vinits.mtgcmp.cards.domain.model

enum class CardTypeFilter(
    private val value: String,
) {
    ARTIFACT("Artifact"),
    CONSPIRACY("Conspiracy"),
    CREATURE("Creature"),
    ENCHANTMENT("Enchantment"),
    INSTANT("Instant"),
    LAND("Land"),
    PHENOMENON("Phenomenon"),
    PLANE("Plane"),
    PLANESWALKER("Planeswalker"),
    SCHEME("Scheme"),
    SORCERY("Sorcery"),
    TRIBAL("Tribal"),
    VANGUARD("Vanguard")
}