package com.example.timemanager


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timemanager.ui.theme.WeekScheduleTheme
import java.time.LocalDate
import java.time.LocalDateTime


import com.example.timemanager.calendarView.event.Event
import com.example.timemanager.calendarView.event.EventTimeFormatter
import com.example.timemanager.calendarView.event.SampleEvents
import com.example.timemanager.calendarView.schedule.EventManagerMenu
import com.example.timemanager.calendarView.schedule.Schedule


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeekScheduleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}


//val sampleEvents = SampleEvents.sampleEvents

//val sampleEvents: Map<LocalDate, MutableList<Event>>
//    @Composable
//    get() = remember {SampleEvents.sampleEventsByDay}


@Composable
fun App() {
    val sampleEvents = remember { SampleEvents.sampleEventsByDay }
    // Create a mutable state for the list of events
//    val events = remember { mutableStateListOf(*SampleEvents.sampleEvents.toTypedArray()) }
        // Button to add a new event
//        Button(onClick = {
//            // Create a new event
//            val newEvent = Event(
//                name = "Dynamic Event",
//                color = Color.Magenta,
//                start = LocalDateTime.now().withHour(18).withMinute(0),
//                end = LocalDateTime.now().withHour(19).withMinute(0),
//                description = "This event was added dynamically."
//            )
//
//            // Add the new event to the mutable state list
//            events.add(newEvent)
//        }) {
//            Text("Add Event")
//        }

        // Pass the updated list to the Schedule composable
        ScheduleScreen(sampleEvents)
}








@Composable
fun AddEventComposable(
    eventsByDay: Map<LocalDate, MutableList<Event>>, // Mutable map to store mutable lists of events
    modifier: Modifier = Modifier,
    onEventAdded: (Event) -> Unit // Callback to notify when an event is added
) {
    var description by remember { mutableStateOf("") } // State for the event description

    Column(modifier = modifier.padding(16.dp)) {
        // TextField for event description
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Event Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Button to add the event
        // Button to add the event
        Button(
            onClick = {
                val newEvent = Event(
                    id = 1,
                    name = "New Event",
                    color = Color.Blue, // Set default color
                    start = LocalDateTime.now(), // Use current time for the start
                    end = LocalDateTime.now().plusHours(1), // Set end time 1 hour later
                    description = description
                )

                val today = LocalDate.now()

                // Check if today's date is already in the map
                val eventList = eventsByDay[today]
                if (eventList != null) {
                    // If today's date exists, add the new event to the mutable list
                    eventList.add(newEvent)
                } else {
                    // If today's date doesn't exist, create a new mutable list and add the new event
                    val newEventList = mutableListOf(newEvent)
                    // Create a new map with the existing entries and the new key-value pair
                    val updatedEventsByDay = eventsByDay.toMutableMap()
                    updatedEventsByDay[today] = newEventList
                    // Call the callback function to notify the parent composable
                    onEventAdded(newEvent)
                }

                // Clear the description field after adding the event
                description = ""
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Event")
        }
    }
}


@Composable
fun ScheduleScreen(eventsByDay: Map<LocalDate, SnapshotStateList<Event>>) {
    var selectedEvent by remember { mutableStateOf<Event?>(null) } // Declare selectedEvent here

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Allocates 2/3 of the screen height
        ){
//         Add Event Composable
            AddEventComposable(
                eventsByDay = eventsByDay,
                onEventAdded = { event ->
                    // Handle the event added, like refreshing the UI or triggering a recomposition
                    println("Event Added: $event")
                },
                modifier = Modifier.padding(8.dp)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f) // Allocates 2/3 of the screen height
        ) {
            // Schedule Composable
            Schedule(
                events = eventsByDay,
                modifier = Modifier.fillMaxSize(),
                onSelectEvent = { event -> selectedEvent = event }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f) // Allocates 2/3 of the screen height
        ) {
            EventManagerMenu(
                selectedEvent = selectedEvent,
                onEventSave = { updatedEvent ->
                    // Directly use the updated event to handle save logic
                    val eventsForDay = eventsByDay[updatedEvent.start.toLocalDate()]
                    eventsForDay?.replaceAll { if (it.id == updatedEvent.id) updatedEvent else it }
                }
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    val sampleEvents = remember { SampleEvents.sampleEventsByDay }
    WeekScheduleTheme {
        ScheduleScreen(sampleEvents)
    }
}

