package com.fadi.excercice_dev_movile

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadi.excercice_dev_movile.ui.theme.ExcercicedevmovileTheme

private const val TAG = "Campus"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(events: List<Event>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.campus_title)) }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events, key = { event -> event.id }) { event ->
                EventCard(
                    event = event,
                    onClick = { Log.d(TAG, "Clic sur la carte de l’événement ${event.id}") },
                    onBook = { Log.d(TAG, "Réservation de l’événement ${event.id}") },
                    modifier = Modifier.fillMaxWidth()
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
            Text(event.title, style = MaterialTheme.typography.titleMedium)
            Text(event.category, style = MaterialTheme.typography.labelLarge)
            Text(
                event.location ?: stringResource(R.string.location_pending),
                style = MaterialTheme.typography.bodyMedium
            )
            if (event.capacity == 0) {
                Text(
                    stringResource(R.string.event_sold_out),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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
