// Portions of this file are derived from code licensed under the MIT License.
// https://github.com/drampelt/WeekSchedule
// Copyright (c) 2021 Daniel Rampelt
// See `THIRD-PARTY-NOTICES.txt` for the full license text.

package com.example.timemanager.calendarView.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.timemanager.calendarView.event.BasicEvent
import com.example.timemanager.calendarView.event.Event
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt




private class EventDataModifier(
    val event: Event,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = event
}

fun Modifier.eventData(event: Event) = this.then(EventDataModifier(event))


@Composable
fun BasicSchedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
    minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
    maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
    numDays: Int,
    dayWidth: Dp,
    hourHeight: Dp,
) {

    // Th number of days that will be displayed on the screen
    val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
    val dividerColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray

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

    Layout(
        content = {
            overlappedGroups.forEach { group ->
                val groupSize = group.size
                group.forEachIndexed { index, event ->
                    Box(modifier = Modifier.eventData(event)) {
                        eventContent(event)
                    }
                }
            }
        },
        modifier = modifier
            .drawBehind {
                repeat(23) {
                    drawLine(
                        dividerColor,
                        start = Offset(0f, (it + 1) * hourHeight.toPx()),
                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                repeat(numDays - 1) {
                    drawLine(
                        dividerColor,
                        start = Offset((it + 1) * dayWidth.toPx(), 0f),
                        end = Offset((it + 1) * dayWidth.toPx(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
    ) { measureables, constraints ->
        val height = hourHeight.roundToPx() * 24
        val width = dayWidth.roundToPx() * numDays
        val placeablesWithEvents = measureables.map { measurable ->
            val event = measurable.parentData as Event
            // This part calculates the height of the event
            val eventDurationMinutes = ChronoUnit.MINUTES.between(event.start, event.end)
            val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()

            // Calculate width for overlapping groups
            val overlapCount = overlappedGroups.first { it.contains(event) }.size
            val eventWidth = (dayWidth.toPx() / overlapCount).roundToInt()

            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = eventWidth,
                    maxWidth = eventWidth,
                    minHeight = eventHeight,
                    maxHeight = eventHeight
                )
            )
            Pair(placeable, event)
        }
        layout(width, height) {
            placeablesWithEvents.forEachIndexed { index, (placeable, event) ->
                val eventOffsetMinutes = ChronoUnit.MINUTES.between(LocalTime.MIN, event.start.toLocalTime())
                val eventY = ((eventOffsetMinutes / 60f) * hourHeight.toPx()).roundToInt()
                val eventOffsetDays = ChronoUnit.DAYS.between(minDate, event.start.toLocalDate()).toInt()

                // Calculate X position for overlapping events
                val group = overlappedGroups.first { it.contains(event) }
                val overlapIndex = group.indexOf(event)
                val eventX = eventOffsetDays * dayWidth.roundToPx() + (overlapIndex * (dayWidth.roundToPx() / group.size))

                placeable.place(eventX, eventY)
            }
        }
    }
}

// Helper function to detect overlapping events
fun isOverlapping(event1: Event, event2: Event): Boolean {
    return event1.end > event2.start && event1.start < event2.end
}