package dev.vinits.mtgcmp.cards.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dev.vinits.mtgcmp.cards.domain.model.CardColorFilter
import dev.vinits.mtgcmp.cards.domain.model.CardSimple
import dev.vinits.mtgcmp.cards.domain.model.ManaType
import dev.vinits.mtgcmp.cards.domain.model.toManaType
import dev.vinits.mtgcmp.foundation.model.Resource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(
    viewModel: CardsViewModel = koinViewModel(),
    onNavigateToDetails: (id: String) -> Unit  // TODO: Refatorar para poder receber o nome da carta
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var openBottomSheet by remember { mutableStateOf(false) }
    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                openBottomSheet = false
            },

            ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
            ) {
                Text(
                    text = "Colors (select 2 or more for multicolored):",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                    )
                )

                val colorFilter = remember { mutableStateListOf<ManaType>() }

                ManaFilterRow { newSelection ->
                    colorFilter.clear()
                    colorFilter.addAll(newSelection)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onFilterSubmit(
                            colorFilter = colorFilter,
                            typeFilter = null
                        )
                        openBottomSheet = false
                    }) {
                    Text("search")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "MTG Cards",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )

                var text by remember { mutableStateOf("") }

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("search") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        openBottomSheet = true
                    }) {
                        Text("filters")
                    }
                }

            }
        }
    ) { contentPadding ->
        AnimatedContent(
            targetState = uiState
        ) { targetState ->
            when (targetState) {
                Resource.Loading -> LoadingIndicator()

                Resource.Error -> ErrorIndicator()

                is Resource.Success<List<CardSimple>> -> {
                    LazyColumn(
                        modifier = Modifier.padding(contentPadding),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        items(targetState.data) { card ->
                            CardRow(
                                cardSimple = card,
                                onClick = {
                                    if (card.id != null) {
                                        // TODO: Refatorar para caso o id seja null, enviar o nome
                                        onNavigateToDetails(card.id)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun ManaFilterRow(
    onSelectionChanged: (List<ManaType>) -> Unit
) {
    val selectedEnums = remember { mutableStateListOf<CardColorFilter>() }

    FlowRow(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardColorFilter.entries.forEach { mana ->
            val isSelected = mana in selectedEnums

            FilterChip(
                modifier = Modifier,
                shape = RoundedCornerShape(12.dp),
                selected = isSelected,
                onClick = {
                    if (isSelected) {
                        selectedEnums.remove(mana)
                    } else {
                        selectedEnums.add(mana)
                    }

                    onSelectionChanged(selectedEnums.map { it.toManaType() })
                },
                label = {
                    Text(
                        text = mana.label,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = mana.color,
                    selectedLabelColor = Color.White,
                    containerColor = mana.color.copy(alpha = 0.3f),
                    labelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

@Composable
fun ErrorIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            Column {


                Text(
                    text = "Out of mana!",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    // TODO: criar evento de recarregar
                }) {
                    Text("try again")
                }
            }
        }
    }
}

@Composable
fun CardRow(
    cardSimple: CardSimple,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // TODO: Entender pq a imagem não aparece
            AsyncImage(
                model = cardSimple.imageUrl,
                contentDescription = cardSimple.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxHeight().width(34.dp).padding(16.dp),
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cardSimple.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier.weight(1f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (cardSimple.manaCost != null) {
                        ManaCostRow(manaList = cardSimple.getManaCostImages(cardSimple.manaCost))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cardSimple.type,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Normal,
                        ),
                        modifier = Modifier.weight(1f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (cardSimple.power != null || cardSimple.loyalty != null) {

                        Box(
                            modifier = Modifier
                                .background(Color.Black, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = cardSimple.loyalty
                                    ?: "${cardSimple.power} / ${cardSimple.toughness}",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ManaCostRow(
    manaList: List<ManaType>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        manaList.forEachIndexed { index, mana ->
            ManaCircle(mana)
            if (index < manaList.lastIndex) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun ManaCircle(mana: ManaType) {
    val manaColorMap = mapOf(
        "R" to Color(0xFFDD3B2D),
        "U" to Color(0xFF1E86C7),
        "G" to Color(0xFF2E8B57),
        "B" to Color(0xFF1C1C1C),
        "W" to Color(0xFFFFFFFF),
        "C" to Color.Gray,
        "P" to Color(0xFFCE00CE)
    )

    val (bgModifier, text) = when (mana) {
        is ManaType.Generic -> Modifier.background(Color.LightGray) to mana.value
        is ManaType.Hybrid -> {

            val start = manaColorMap[mana.left] ?: Color.Gray
            val end = manaColorMap[mana.right] ?: Color.Gray
            Modifier.background(
                brush = Brush.linearGradient(listOf(start, end))
            ) to "${mana.left}/${mana.right}"
        }

        ManaType.Colorless -> Modifier.background(Color.LightGray) to "C"
        ManaType.Phyrexian -> Modifier.background(Color.DarkGray) to "P"
        else -> Modifier.background(manaColorMap[mana.value] ?: Color.DarkGray) to null
    }

    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .then(bgModifier)
            .border(1.dp, Color.Black, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        text?.let {
            Text(
                text = it,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}