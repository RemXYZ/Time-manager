// Portions of this file are derived from code licensed under the MIT License.
// https://github.com/drampelt/WeekSchedule
// Copyright (c) 2021 Daniel Rampelt
// See `THIRD-PARTY-NOTICES.txt` for the full license text.
package com.example.timemanager.calendarView.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter

val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

@Composable
fun BasicEvent(
    event: Event,
    modifier: Modifier = Modifier,
    onSelect: (Event) -> Unit // Callback to notify when an event is selected
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 2.dp, bottom = 2.dp)
            .background(event.color, shape = RoundedCornerShape(4.dp))
            .padding(4.dp)
            .clickable { onSelect(event) } // Make the event clickable
    ) {
        Text(
            text = "${event.start.format(EventTimeFormatter)} - ${event.end.format(
                EventTimeFormatter
            )}",
            style = MaterialTheme.typography.caption,
        )

        Text(
            text = event.name,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
        )

        if (event.description != null) {
            Text(
                text = event.description,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}