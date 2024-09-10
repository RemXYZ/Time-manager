package com.example.timemanager.calendarView.schedule

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.timemanager.calendarView.event.BasicEvent
import com.example.timemanager.calendarView.event.Event
import com.example.timemanager.calendarView.event.SampleEvents
import com.example.timemanager.ui.theme.WeekScheduleTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


@Composable
fun MultiDaySchedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { event -> BasicEvent(event = event) },
    numDays: Int, // Number of days to display
    dayWidth: Dp,
    hourHeight: Dp,
    verticalScrollState: ScrollState
) {
    val minDate = events.minByOrNull(Event::start)!!.start.toLocalDate()

    // Sort events by start time
    val sortedEvents = events.sortedBy { it.start }

    // Detect overlapping events
    val overlappedGroups = mutableListOf<List<Event>>()
    var currentGroup = mutableListOf<Event>()

    sortedEvents.forEach { event ->
        if (currentGroup.isEmpty() || !isOverlapping(currentGroup.last(), event)) {
            if (currentGroup.isNotEmpty()) overlappedGroups.add(currentGroup)
            currentGroup = mutableListOf(event)
        } else {
            currentGroup.add(event)
        }
    }
    if (currentGroup.isNotEmpty()) overlappedGroups.add(currentGroup)

    Box(modifier = modifier.fillMaxWidth()) {
        LazyRow {
            items((0 until numDays).toList()) { dayIndex ->
                DaySchedule(
                    day = minDate.plusDays(dayIndex.toLong()),
                    events = events,
                    hourHeight = hourHeight,
                    dayWidth = dayWidth,
                    eventContent = eventContent,
                    verticalScrollState = verticalScrollState // Share the scroll state
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiDaySchedulePreview() {

    // Create a mutable copy of the sample events
    val sEvents = SampleEvents.sampleEvents.toMutableList()
    WeekScheduleTheme {
        MultiDaySchedule(sEvents, numDays = 3, dayWidth = 100.dp, hourHeight = 64.dp, verticalScrollState = ScrollState(0))
    }
}