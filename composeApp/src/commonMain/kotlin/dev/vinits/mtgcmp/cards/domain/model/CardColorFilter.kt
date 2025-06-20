package dev.vinits.mtgcmp.cards.domain.model

import androidx.compose.ui.graphics.Color

enum class CardColorFilter(
    val label: String, val color: Color
) {
    RED("Red", Color(0xFFeba083)),
    BLUE("Blue", Color(0xFFb0cbe6)),
    GREEN("Green", Color(0xFFbcd8c3)),
    BLACK("Black", Color(0xFFa89f9e)),
    WHITE("White", Color(0xFFf5e6b7)),
}

fun CardColorFilter.toManaType(genericValue: String = "1"): ManaType {
    return when (this) {
        CardColorFilter.RED -> ManaType.Red
        CardColorFilter.BLUE -> ManaType.Blue
        CardColorFilter.GREEN -> ManaType.Green
        CardColorFilter.BLACK -> ManaType.Black
        CardColorFilter.WHITE -> ManaType.White
    }
}