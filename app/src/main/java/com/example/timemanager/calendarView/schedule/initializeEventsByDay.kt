package com.example.timemanager.calendarView.schedule

import com.example.timemanager.calendarView.event.Event
import java.time.LocalDate

fun initializeEventsByDay(events: List<Event>): MutableMap<LocalDate, MutableList<Event>> {
    // Create a mutable map and group events by their start date
    return events.groupBy { it.start.toLocalDate() }
        .mapValues { it.value.toMutableList() } // Convert the grouped lists to MutableList
        .toMutableMap()
}
