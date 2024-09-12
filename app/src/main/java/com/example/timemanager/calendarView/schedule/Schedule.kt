// Portions of this file are derived from code licensed under the MIT License.
// https://github.com/drampelt/WeekSchedule
// Copyright (c) 2021 Daniel Rampelt
// See `THIRD-PARTY-NOTICES.txt` for the full license text.

package com.example.timemanager.calendarView.schedule

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timemanager.ui.theme.WeekScheduleTheme
import java.time.LocalDateTime
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.example.timemanager.calendarView.event.BasicEvent
import com.example.timemanager.calendarView.event.Event
import com.example.timemanager.calendarView.event.SampleEvents
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.snapshots.SnapshotStateList


@Composable
fun Schedule(
    events: Map<LocalDate, SnapshotStateList<Event>>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
    minDate: LocalDate = events.values.flatten().minByOrNull { it.start }?.start?.toLocalDate() ?: LocalDate.now(),
    maxDate: LocalDate = events.values.flatten().maxByOrNull { it.end }?.end?.toLocalDate() ?: LocalDate.now(),
) {

    val numDate = ChronoUnit.DAYS.between(maxDate, minDate)
    var sidebarWidth by remember { mutableStateOf(0) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val dayWidth = (screenWidth - with(LocalDensity.current) { sidebarWidth.toDp() }) / 3
    val hourHeight = 64.dp

    // Shared vertical scroll state for all days
    val verticalScrollState = rememberScrollState()

    Column(modifier = modifier) {
        ScheduleHeader(
            minDate = minDate,
            maxDate = maxDate,
            dayWidth = dayWidth,
            dayHeader = dayHeader,
            modifier = Modifier
                .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
        )
        Row(modifier = Modifier.weight(1f)) {
            ScheduleSidebar(
                hourHeight = hourHeight,
                modifier = Modifier
                    .verticalScroll(verticalScrollState)
                    .onGloballyPositioned { sidebarWidth = it.size.width }
            )
            MultiDaySchedule(
                events = events,
                minDate = minDate,
                maxDate = maxDate,
                dayWidth = dayWidth,
                hourHeight = hourHeight,
                verticalScrollState = verticalScrollState
            )
        }
    }
}







@Preview(showBackground = true)
@Composable
fun SchedulePreview() {

    // Create a mutable copy of the sample events
    val mutableEvents = SampleEvents.sampleEventsByDay

    // Add the new event to the mutable list
    mutableEvents[LocalDate.of(2024, 5, 11)]?.add(
        Event(
            id = 1,
            name = "New Event",
            color = Color.Magenta,
            start = LocalDateTime.of(2024, 5, 11, 4, 0,0),
            end = LocalDateTime.of(2024, 5, 11, 6,0,0),
            description = "This is a new event."
        )
    )

    WeekScheduleTheme {
//        Schedule(mutableEvents)
    }
}


