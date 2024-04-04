package com.example.timemanager.ScheduleGridManager

import java.time.LocalDateTime

class Event(
    private var title: String,
    private var startTime: LocalDateTime,
    private var endTime: LocalDateTime,
    private var description: String
) {
    // Getters are not explicitly needed as Kotlin generates them automatically
    // However, if you need custom getter logic, you can define them here




    fun getTitle() = title
    fun getStartTime() = startTime
    fun getEndTime() = endTime
    fun getDescription() = description

    // Setters
    fun setTitle(value: String) { title = value }
    fun setStartTime(value: LocalDateTime) { startTime = value }
    fun setEndTime(value: LocalDateTime) { endTime = value }
    fun setDescription(value: String) { description = value }

    // Additional functionalities related to an event can be added here
}
