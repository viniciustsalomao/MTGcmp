package dev.vinits.mtgcmp.cards.domain.model

// TODO: Pensar se vale a pena refatorar essa classe para criar herança e separar cada tipo de carta
data class CardSimple(
    val id: String?,
    val name: String,
    val manaCost: String?,
    val type: String,
    val rarity: String,
    val set: String,
    val text: String?,
    val number: String?,
    val power: String?,
    val toughness: String?,
    val imageUrl: String?,
    val loyalty: String?,

) {

    // TODO: Entender qual seria o lugar certo para colocar essa função
    fun getManaCostImages(string: String): List<ManaType> {
        // TODO: Refatorar essa função para retornar a imagem da mana já formatada

        print(string)

        return string
            .split("}")
            .filter { it.isNotBlank() }
            .map { it.removePrefix("{").trim() }
            .map { symbol ->
                when {
                    "/" in symbol -> {
                        val parts = symbol.split("/")
                        ManaType.Hybrid(parts[0], parts[1])
                    }
                    symbol == ManaType.Red.value -> ManaType.Red
                    symbol == ManaType.Blue.value -> ManaType.Blue
                    symbol == ManaType.Green.value -> ManaType.Green
                    symbol == ManaType.Black.value -> ManaType.Black
                    symbol == ManaType.White.value -> ManaType.White
                    symbol == ManaType.Colorless.value -> ManaType.Colorless
                    symbol == ManaType.Phyrexian.value -> ManaType.Phyrexian
                    else -> ManaType.Generic(symbol)
                }
            }
    }
}


