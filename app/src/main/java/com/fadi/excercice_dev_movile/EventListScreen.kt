package com.fadi.excercice_dev_movile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadi.excercice_dev_movile.ui.theme.ExcercicedevmovileTheme

private const val TAG = "Campus"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(events: List<Event>, darkTheme: Boolean, onToggleTheme: () -> Unit) {
    var selectedCategory by rememberSaveable { mutableStateOf<Int?>(null) }
    val categories = events.map { it.category }.distinct()
    val visibleEvents = selectedCategory?.let { category ->
        events.filter { it.category == category }
    } ?: events

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.campus_title)) },
                actions = {
                    TextButton(onClick = onToggleTheme) {
                        Text(
                            stringResource(
                                if (darkTheme) R.string.switch_to_light_theme
                                else R.string.switch_to_dark_theme
                            )
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = selectedCategory == null,
                            onClick = { selectedCategory = null },
                            label = { Text(stringResource(R.string.all_categories)) }
                        )
                    }
                    items(categories, key = { it }) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(stringResource(category)) }
                        )
                    }
                }
            }
            items(visibleEvents, key = { event -> event.id }) { event ->
                EventCard(
                    event = event,
                    onClick = { Log.d(TAG, "Clic sur la carte de l’événement ${event.id}") },
                    onBook = { Log.d(TAG, "Réservation de l’événement ${event.id}") },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    onBook: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(stringResource(event.title), style = MaterialTheme.typography.titleMedium)
            Text(
                text = stringResource(event.category),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                stringResource(event.location ?: R.string.location_pending),
                style = MaterialTheme.typography.bodyMedium
            )
            EventAvailability(event.capacity)
            Button(
                onClick = onBook,
                enabled = event.capacity > 0,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(R.string.book_event))
            }
        }
    }
}

@Composable
internal fun EventAvailability(capacity: Int) {
    val availability = if (capacity > 0) {
        pluralStringResource(R.plurals.event_places, capacity, capacity)
    } else {
        stringResource(R.string.event_sold_out)
    }
    Text(availability, style = MaterialTheme.typography.bodyMedium)
}

@Preview(name = "Liste claire", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Liste sombre", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun EventListScreenPreview() {
    ExcercicedevmovileTheme {
        EventListScreen(
            events = campusEvents,
            darkTheme = isSystemInDarkTheme(),
            onToggleTheme = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoldOutEventCardPreview() {
    ExcercicedevmovileTheme {
        EventCard(
            event = campusEvents.first { it.capacity == 0 },
            onClick = {},
            onBook = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
