package dev.vinits.mtgcmp.cards.domain.model

sealed class ManaType(open val value: String) {
    object Red : ManaType("R")
    object Blue : ManaType("U")
    object Green : ManaType("G")
    object Black : ManaType("B")
    object White : ManaType("W")
    object Colorless : ManaType("C")
    object Phyrexian : ManaType("P")
    data class Generic(override val value: String) : ManaType(value)
    data class Hybrid(val left: String, val right: String) : ManaType("{$left/$right}")
}

/* manaCost guide:
    - {G} = green
    - {R} = red
    - {U} = blue
    - {W} = white
    - {B} = black
    - {C} = colorless
    - {0} = generic
    - {B/R} = hybrid
    - {P} = phyrexian
    - {G/P} = colored phyrexian
    - {R/G/P} = colored hybrid phyrexian

    example:
    manaCost = "{2}{R}{B}
    means the card costs 2 colorless mana, 1 red and 1 blue
 */

