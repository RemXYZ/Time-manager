// Portions of this file are derived from code licensed under the MIT License.
// https://github.com/drampelt/WeekSchedule
// Copyright (c) 2021 Daniel Rampelt
// See `THIRD-PARTY-NOTICES.txt` for the full license text.

package com.example.timemanager.calendarView.event

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class Event(
    val id:Int,
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null,
)

