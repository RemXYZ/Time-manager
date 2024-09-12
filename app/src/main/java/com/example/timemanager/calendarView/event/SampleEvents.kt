// Portions of this file are derived from code licensed under the MIT License.
// https://github.com/drampelt/WeekSchedule
// Copyright (c) 2021 Daniel Rampelt
// See `THIRD-PARTY-NOTICES.txt` for the full license text.


package com.example.timemanager.calendarView.event

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SampleEvents {
    companion object {
        val sampleEventsByDay: Map<LocalDate, SnapshotStateList<Event>> = mapOf(
            // Each entry is a date mapped to a mutable list of events
            LocalDate.now().minusDays(1) to mutableStateListOf(
                Event(
                    id = 1,
                    name = "Google I/O Keynote",
                    color = Color(0xFFAFBBF2),
//                    start = LocalDateTime.parse("2024-05-10T04:00:00"),
//                    end = LocalDateTime.parse("2024-05-10T06:00:00"),
                    start = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(9, 0)), // Static date with dynamic time
                    end = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(10, 0)),   // Static date with dynamic time
                    description = "Tune in to find out about how we're furthering our mission to organize the world’s information."
                )
            ),
            LocalDate.now() to mutableStateListOf(
                Event(
                    id = 2,
                    name = "Developer Keynote",
                    color = Color(0xFFAFBBF2),
                    start = LocalDateTime.of(LocalDate.now(), LocalTime.of(5, 0)), // Static date with dynamic time
                    end = LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)),   // Static date with dynamic time
                    description = "Learn about the latest updates to our developer products and platforms from Google Developers."
                )
            ),
            LocalDate.now().plusDays(1) to mutableStateListOf(
                Event(
                    id = 3,
                    name = "What's new in Android",
                    color = Color(0xFF1B998B),
//                    start = LocalDateTime.parse("2024-05-12T07:01:00"),
//                    end = LocalDateTime.parse("2024-05-12T17:00:00"),
                    start = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 0)), // Static date with dynamic time
                    end = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(2, 0)),
                    description = "Discussion on the latest Android features and enhancements for developers."
                )
            )
        )
    }
}