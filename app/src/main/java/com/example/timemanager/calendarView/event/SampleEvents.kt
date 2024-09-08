// Portions of this file are derived from code licensed under the MIT License.
// https://github.com/drampelt/WeekSchedule
// Copyright (c) 2021 Daniel Rampelt
// See `THIRD-PARTY-NOTICES.txt` for the full license text.


package com.example.timemanager.calendarView.event

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

class SampleEvents {
    companion object {
        val sampleEvents = listOf(
            Event(
                name = "Google I/O Keynote",
                color = Color(0xFFAFBBF2),
                start = LocalDateTime.parse("2021-05-18T10:00:00"),
                end = LocalDateTime.parse("2021-05-18T15:00:00"),
                description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
            ),
            Event(
                name = "Developer Keynote",
                color = Color(0xFFAFBBF2),
                start = LocalDateTime.parse("2021-05-19T15:15:00"),
                end = LocalDateTime.parse("2021-05-19T16:00:00"),
                description = "Learn about the latest updates to our developer products and platforms from Google Developers.",
            ),
            Event(
                name = "What's new in Android",
                color = Color(0xFF1B998B),
                start = LocalDateTime.parse("2021-05-20T16:01:00"),
                end = LocalDateTime.parse("2021-05-20T17:00:00"),
                description = "In this Keynote, Chet Haase, Dan Sandler, and Romain Guy discuss the latest Android features and enhancements for developers.",
            ),
        )
    }
}