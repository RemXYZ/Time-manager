package com.example.timemanager.calendarView.schedule

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.timemanager.calendarView.event.BasicEvent
import com.example.timemanager.calendarView.event.Event
import java.time.LocalDate


@Composable
fun MultiDaySchedule(
    events: Map<LocalDate, SnapshotStateList<Event>>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event, onSelect: (Event) -> Unit) -> Unit = { event, onSelect -> BasicEvent(event = event, onSelect = onSelect) },
    minDate: LocalDate = events.values.flatten().minByOrNull(Event::start)!!.start.toLocalDate(),
    maxDate: LocalDate = events.values.flatten().maxByOrNull(Event::end)!!.end.toLocalDate(),
    dayWidth: Dp,
    hourHeight: Dp,
    verticalScrollState: ScrollState,
    onSelectEvent: (Event) -> Unit
) {

    val days = generateSequence(minDate) { it.plusDays(1) }
        .takeWhile { it <= maxDate }
        .toList()

    Box(modifier = modifier.fillMaxWidth()) {
        LazyRow {
            items(days, key = { it }) { day ->
                val dayEvents = events[day] ?: SnapshotStateList()
                DaySchedule(
                    day = day,
                    events = dayEvents,
                    hourHeight = hourHeight,
                    dayWidth = dayWidth,
                    verticalScrollState = verticalScrollState, // Pass the onSelect callback
                    onSelectEvent = onSelectEvent
                ) { event, onSelectEvent -> eventContent(event, onSelectEvent) }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MultiDaySchedulePreview() {
//    val eventsByDay = SampleEvents.sampleEventsByDay
//
//
////    // Create a mutable copy of the sample events
////    val sEvents = SampleEvents.sampleEvents.toMutableList()
//    WeekScheduleTheme {
//        MultiDaySchedule(eventsByDay, dayWidth = 100.dp, hourHeight = 64.dp, verticalScrollState = ScrollState(0))
//    }
//}