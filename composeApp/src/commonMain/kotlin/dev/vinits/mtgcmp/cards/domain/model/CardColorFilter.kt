package dev.vinits.mtgcmp.cards.domain.model

import androidx.compose.ui.graphics.Color

enum class CardColorFilter(
    val label: String, val color: Color
) {
    RED("Red", Color(0xFFDD2C00)),
    BLUE("Blue", Color(0xFF2962FF)),
    GREEN("Green", Color(0xFF00C853)),
    BLACK("Black", Color(0xFF212121)),
    WHITE("White", Color(0xFFFFF9C4)),
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