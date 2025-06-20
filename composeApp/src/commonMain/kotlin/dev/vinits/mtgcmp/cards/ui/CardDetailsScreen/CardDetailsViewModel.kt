package dev.vinits.mtgcmp.cards.ui.CardDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vinits.mtgcmp.cards.domain.repository.CardRepository
import dev.vinits.mtgcmp.foundation.model.Resource.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class CardDetailsViewModel(
    private val id: String,
    private val repository: CardRepository,
) : ViewModel() {

    val uiState = flow {
        emit(repository.getCardDetails(id))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Loading
    )

}