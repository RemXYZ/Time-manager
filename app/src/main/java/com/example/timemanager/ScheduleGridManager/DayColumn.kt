package com.example.timemanager.ScheduleGridManager

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DayColumn(private val calendar: Calendar) {
    private val events = mutableListOf<String>() // Example list of events
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val date: String
        get() = dateFormat.format(calendar.time)


    init{

    }

    fun addEvent(event: String) {
        events.add(event)
    }

    fun getFormattedDate(): String {
        return date
    }

    // Other functionalities related to a day can be added here, such as getting the list of events
}
