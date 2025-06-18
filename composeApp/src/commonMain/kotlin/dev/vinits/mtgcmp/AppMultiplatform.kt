package dev.vinits.mtgcmp

import dev.vinits.mtgcmp.cards.di.cardsModule
import dev.vinits.mtgcmp.foundation.network.networkModule
import org.koin.core.context.startKoin

object AppMultiplatform {
    fun initialize() {
        startKoin {
            modules(
                networkModule,
                cardsModule
            )
        }
    }
}