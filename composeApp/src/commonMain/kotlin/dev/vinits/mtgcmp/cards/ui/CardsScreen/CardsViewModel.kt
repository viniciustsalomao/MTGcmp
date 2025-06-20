package dev.vinits.mtgcmp.cards.ui.CardsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vinits.mtgcmp.cards.domain.model.CardType
import dev.vinits.mtgcmp.cards.domain.model.Filter
import dev.vinits.mtgcmp.cards.domain.model.ManaType
import dev.vinits.mtgcmp.cards.domain.repository.CardRepository
import dev.vinits.mtgcmp.foundation.model.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CardsViewModel(
    private val repository: CardRepository,
) : ViewModel() {

    private val filter = MutableStateFlow(
        Filter(
            nameFilter = null,
            colorFilter = null,
            typeFilter = null,
        ),
    )

    val uiState = filter.flatMapLatest { filter ->
        repository.getCards(
            nameFilter = filter.nameFilter,
            colorFilter = filter.colorFilter,
            typeFilter = filter.typeFilter,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )

    fun onFilterSubmit(
        nameFilter: String?,
        colorFilter: List<ManaType>?,
        typeFilter: CardType?,
    ) {
        filter.update {
            it.copy(
                nameFilter,
                colorFilter,
                typeFilter
            )
        }
    }
}