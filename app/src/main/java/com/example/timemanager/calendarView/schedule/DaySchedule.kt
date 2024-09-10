package com.example.timemanager.calendarView.schedule

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.timemanager.calendarView.event.BasicEvent
import com.example.timemanager.calendarView.event.Event
import com.example.timemanager.calendarView.event.SampleEvents
import com.example.timemanager.ui.theme.WeekScheduleTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@Composable
fun DaySchedule(
    day: LocalDate,
    events: List<Event>,
    hourHeight: Dp,
    dayWidth: Dp,
    verticalScrollState: ScrollState, // Accept the shared scroll state
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) }
) {
    val dayEvents = events.filter {
        it.start.toLocalDate() == day
    }

    val sortedEvents = dayEvents.sortedBy { it.start }
    val overlappedGroups = mutableListOf<List<Event>>()
    var currentGroup = mutableListOf<Event>()

    val dividerColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray

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
                group.forEach { event ->
                    Box(modifier = Modifier.eventData(event)) {
                        eventContent(event)
                    }
                }
            }
        },
        modifier = Modifier
            .width(dayWidth)
            .height(hourHeight * 24)
            .verticalScroll(verticalScrollState) // Apply the shared scroll state here
            .drawBehind {
                repeat(23) {
                    drawLine(
                        dividerColor,
                        start = Offset(0f, (it + 1) * hourHeight.toPx()),
                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                drawLine(
                    dividerColor,
                    start = Offset(dayWidth.toPx(), 0f),
                    end = Offset(dayWidth.toPx(), size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
    ) { measurables, constraints ->
        val placeablesWithEvents = measurables.map { measurable ->
            val event = measurable.parentData as Event
            val eventDurationMinutes = ChronoUnit.MINUTES.between(event.start, event.end)
            val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()

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

        layout(dayWidth.roundToPx(), hourHeight.roundToPx() * 24) {
            placeablesWithEvents.forEach { (placeable, event) ->
                val eventOffsetMinutes = ChronoUnit.MINUTES.between(LocalTime.MIN, event.start.toLocalTime())
                val eventY = ((eventOffsetMinutes / 60f) * hourHeight.toPx()).roundToInt()
                val group = overlappedGroups.first { it.contains(event) }
                val overlapIndex = group.indexOf(event)
                val eventX = overlapIndex * (dayWidth.toPx() / group.size)

                placeable.place(eventX.roundToInt(), eventY)
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun DayPreview() {
    val sampleEvents = listOf(
        Event(
            name = "Meeting with John",
            color = Color(0xFFAFBBF2),
            start = LocalDate.now().atTime(4, 0),
            end = LocalDate.now().atTime(5, 34),
            description = "Discuss the project"
        ),
        Event(
            name = "Lunch with Sarah",
            color = Color.Green,
            start = LocalDate.now().atTime(12, 0),
            end = LocalDate.now().atTime(13, 0),
            description = "At the new restaurant"
        ),
        Event(
            name = "Team meeting",
            color = Color.Red,
            start = LocalDate.now().atTime(14, 0),
            end = LocalDate.now().atTime(15, 0),
            description = "Discuss the new project"
        )
    )

    // Define the day for the preview
    val previewDay = LocalDate.now()

    // Example parameters for dayWidth and hourHeight
    val dayWidth = 256.dp
    val hourHeight = 64.dp

    // Create a shared scroll state for vertical scrolling
    val verticalScrollState = rememberScrollState()

    DaySchedule(
        day = previewDay,
        events = sampleEvents,
        hourHeight = hourHeight,
        dayWidth = dayWidth,
        verticalScrollState = verticalScrollState, // Pass the shared scroll state
        eventContent = { event -> BasicEvent(event = event) }
    )
}