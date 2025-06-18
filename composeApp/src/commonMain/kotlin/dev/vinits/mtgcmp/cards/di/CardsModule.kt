package dev.vinits.mtgcmp.cards.di

import dev.vinits.mtgcmp.cards.data.network.CardsResponse
import dev.vinits.mtgcmp.cards.data.repository.CardRepositoryImpl
import dev.vinits.mtgcmp.cards.domain.repository.CardRepository
import dev.vinits.mtgcmp.cards.ui.CardsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cardsModule = module {
    single<CardRepository> {
        CardRepositoryImpl(
            api = get()
        )
    }

    viewModel {
        CardsViewModel(
            repository = get()
        )
    }
}