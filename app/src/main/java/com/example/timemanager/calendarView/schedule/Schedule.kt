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

//
@Composable
fun Schedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
    minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
    maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
) {

    var sidebarWidth by remember { mutableStateOf(0) }

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val numDays = 3
    val hourHeight = 64.dp
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    val dayWidth = remember(sidebarWidth) {
        ((screenWidth - sidebarWidth) / 3) // Divide remaining width by 3
    }
//    var dayWidthDp = ((screenWidth-sidebarWidth)/3).dp
    var dayWidthDp = with(LocalDensity.current) { ((LocalConfiguration.current.screenWidthDp.dp - sidebarWidth.toDp())/3) }
//    dayWidthDp = 265.dp
//    val dayWidth = 256.dp
    Column(modifier = modifier) {
        ScheduleHeader(
            minDate = minDate,
            maxDate = maxDate,
            dayWidth = dayWidthDp,
            dayHeader = dayHeader,
            modifier = Modifier
                .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
                .horizontalScroll(horizontalScrollState)
        )
        Row(modifier = Modifier.weight(1f)) {
            ScheduleSidebar(
                hourHeight = hourHeight,
                modifier = Modifier
                    .verticalScroll(verticalScrollState)
                    .onGloballyPositioned { sidebarWidth = it.size.width }
            )
            BasicSchedule(
                events = events,
                eventContent = eventContent,
                minDate = minDate,
                maxDate = maxDate,
                numDays = numDays,
                dayWidth = dayWidthDp,
                hourHeight = hourHeight,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(verticalScrollState)
                    .horizontalScroll(horizontalScrollState)
            )
        }
    }
}

//
//@Composable
//fun Schedule(
//    events: List<Event>,
//    modifier: Modifier = Modifier,
//    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
//    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
//    minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
//    maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
//) {
//    val sidebarWidth = 100.dp
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val dayWidth = (screenWidth - sidebarWidth) / 3
//    val hourHeight = 64.dp
//
//    // Shared vertical scroll state for all days
//    val verticalScrollState = rememberScrollState()
//
//    Column(modifier = modifier) {
//        ScheduleHeader(
//            minDate = minDate,
//            maxDate = maxDate,
//            dayWidth = dayWidth,
//            dayHeader = dayHeader,
//            modifier = Modifier
//                .padding(start = sidebarWidth)
//        )
//        Row(modifier = Modifier.weight(1f)) {
//            ScheduleSidebar(
//                hourHeight = hourHeight,
//                modifier = Modifier.verticalScroll(verticalScrollState)
//            )
//            LazyRow(modifier = Modifier.weight(1f)) {
//                items((0..ChronoUnit.DAYS.between(minDate, maxDate).toInt()).toList()) { dayIndex ->
//                    DaySchedule(
//                        day = minDate.plusDays(dayIndex.toLong()),
//                        events = events,
//                        hourHeight = hourHeight,
//                        dayWidth = dayWidth,
//                        eventContent = eventContent,
//                        verticalScrollState = verticalScrollState // Share the scroll state
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//
//@Composable
//fun DaySchedule(
//    day: LocalDate,
//    events: List<Event>,
//    hourHeight: Dp,
//    dayWidth: Dp,
//    verticalScrollState: ScrollState, // Accept the shared scroll state
//    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) }
//) {
//    val dayEvents = events.filter {
//        it.start.toLocalDate() == day
//    }
//
//    val sortedEvents = dayEvents.sortedBy { it.start }
//    val overlappedGroups = mutableListOf<List<Event>>()
//    var currentGroup = mutableListOf<Event>()
//
//    sortedEvents.forEach { event ->
//        if (currentGroup.isEmpty() || !isOverlapping(currentGroup.last(), event)) {
//            if (currentGroup.isNotEmpty()) overlappedGroups.add(currentGroup)
//            currentGroup = mutableListOf(event)
//        } else {
//            currentGroup.add(event)
//        }
//    }
//    if (currentGroup.isNotEmpty()) overlappedGroups.add(currentGroup)
//
//    Layout(
//        content = {
//            overlappedGroups.forEach { group ->
//                group.forEach { event ->
//                    Box(modifier = Modifier.eventData(event)) {
//                        eventContent(event)
//                    }
//                }
//            }
//        },
//        modifier = Modifier
//            .width(dayWidth)
//            .height(hourHeight * 24)
//            .verticalScroll(verticalScrollState) // Apply the shared scroll state here
//            .drawBehind {
//                repeat(23) {
//                    drawLine(
//                        Color.LightGray,
//                        start = Offset(0f, (it + 1) * hourHeight.toPx()),
//                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
//                        strokeWidth = 1.dp.toPx()
//                    )
//                }
//            }
//    ) { measurables, constraints ->
//        val placeablesWithEvents = measurables.map { measurable ->
//            val event = measurable.parentData as Event
//            val eventDurationMinutes = ChronoUnit.MINUTES.between(event.start, event.end)
//            val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
//
//            val overlapCount = overlappedGroups.first { it.contains(event) }.size
//            val eventWidth = (dayWidth.toPx() / overlapCount).roundToInt()
//
//            val placeable = measurable.measure(
//                constraints.copy(
//                    minWidth = eventWidth,
//                    maxWidth = eventWidth,
//                    minHeight = eventHeight,
//                    maxHeight = eventHeight
//                )
//            )
//            Pair(placeable, event)
//        }
//
//        layout(dayWidth.roundToPx(), hourHeight.roundToPx() * 24) {
//            placeablesWithEvents.forEach { (placeable, event) ->
//                val eventOffsetMinutes = ChronoUnit.MINUTES.between(LocalTime.MIN, event.start.toLocalTime())
//                val eventY = ((eventOffsetMinutes / 60f) * hourHeight.toPx()).roundToInt()
//                val group = overlappedGroups.first { it.contains(event) }
//                val overlapIndex = group.indexOf(event)
//                val eventX = overlapIndex * (dayWidth.toPx() / group.size)
//
//                placeable.place(eventX.roundToInt(), eventY)
//            }
//        }
//    }
//}




val sampleEvents = SampleEvents.sampleEvents

@Preview(showBackground = true)
@Composable
fun SchedulePreview() {

    // Create a mutable copy of the sample events
    val mutableEvents = SampleEvents.sampleEvents.toMutableList()

    // Add the new event to the mutable list
    mutableEvents.add(
        Event(
            name = "New Event",
            color = Color.Magenta,
            start = LocalDateTime.now().withHour(18).withMinute(0),
            end = LocalDateTime.now().withHour(19).withMinute(0),
            description = "This is a new event."
        )
    )

    WeekScheduleTheme {
        Schedule(mutableEvents)
    }
}