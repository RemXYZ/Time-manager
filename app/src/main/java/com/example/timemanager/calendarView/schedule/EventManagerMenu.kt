package com.example.timemanager.calendarView.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timemanager.calendarView.event.Event
import com.example.timemanager.calendarView.event.EventTimeFormatter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.timemanager.calendarView.event.SampleEvents
import com.example.timemanager.calendarView.schedule.Schedule
import com.example.timemanager.ui.theme.WeekScheduleTheme
import com.example.timemanager.calendarView.schedule.EventManagerMenu

@Composable
fun EventManagerMenu(
    selectedEvent: Event?, // The event selected by the user
    onEventSave: (Event) -> Unit, // Callback to handle saving changes
    modifier: Modifier = Modifier
) {
    // Mutable states to store the new values for the event details
    var eventStart by remember { mutableStateOf(selectedEvent?.start?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) ?: "") }
    var eventEnd by remember { mutableStateOf(selectedEvent?.end?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) ?: "") }
    var description by remember { mutableStateOf(selectedEvent?.description ?: "") }
    var eventName by remember { mutableStateOf(selectedEvent?.name) }
    val scrollState = rememberScrollState()

    // Update state when selectedEvent changes
    LaunchedEffect(selectedEvent) {
        selectedEvent?.let {
            eventName = it.name
            description = it.description ?: ""
            eventStart = it.start.toString()
            eventEnd = it.end.toString()
        }
    }

    Column(modifier = modifier
        .padding(16.dp)
        .verticalScroll(scrollState)
    ) {
        Text(text = "Event Manager")

        if (selectedEvent != null) {
            Text(
                text = "Selected Event: ${eventName} (${selectedEvent.start.format(EventTimeFormatter)} - ${selectedEvent.end.format(EventTimeFormatter)})",
                modifier = Modifier.padding(16.dp),
            )

            // Input field for event description
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Event Description") },
                modifier = Modifier.fillMaxWidth()
            )

            // Input field for event start time
            TextField(
                value = eventStart,
                onValueChange = { eventStart = it },
                modifier = Modifier.fillMaxWidth()
            )

            // Input field for event end time
            TextField(
                value = eventEnd,
                onValueChange = { eventEnd = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Button to save the changes
            // Save button to apply changes
            Button(
                onClick = {
                    // Handle saving the modified event
                    val updatedEvent = eventName?.let {
                        selectedEvent.copy(
                            name = it,
                            description = description,
                            start = LocalDateTime.parse(eventStart),
                            end = LocalDateTime.parse(eventEnd)
                        )
                    }
                    if (updatedEvent != null) {
                        onEventSave(updatedEvent)
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Save Changes")
            }
        } else {
            Text(text = "No event selected")
        }
    }
}


