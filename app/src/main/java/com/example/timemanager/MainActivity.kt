package com.example.timemanager


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.timemanager.ui.theme.WeekScheduleTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt


import com.example.timemanager.calendarView.event.Event
import com.example.timemanager.calendarView.event.BasicEvent
import com.example.timemanager.calendarView.event.SampleEvents
import com.example.timemanager.calendarView.schedule.Schedule
import com.example.timemanager.calendarView.schedule.initializeEventsByDay





//import androidx.activity.compose.setContent
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.unit.dp
//
//data class Person(val name: String, val age: Int)
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApp()
//        }
//    }
//}
//
//@Composable
//fun MyApp() {
//    // State for storing persons grouped by roles
//    val personsByRole = remember {
//        mapOf(
//            "Worker" to mutableStateListOf<Person>(),
//            "Manager" to mutableStateListOf<Person>()
//        )
//    }
//
//    var name by remember { mutableStateOf("") }
//    var age by remember { mutableStateOf("") }
//    var selectedRole by remember { mutableStateOf("Worker") }
//    var showRoleOptions by remember { mutableStateOf(false) }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        // Input for name
//        BasicTextField(
//            value = name,
//            onValueChange = { name = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp),
//            decorationBox = { innerTextField ->
//                Box(Modifier.padding(4.dp)) {
//                    if (name.isEmpty()) Text(text = "Enter name")
//                    innerTextField()
//                }
//            }
//        )
//
//        // Input for age
//        BasicTextField(
//            value = age,
//            onValueChange = { age = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp),
//            decorationBox = { innerTextField ->
//                Box(Modifier.padding(4.dp)) {
//                    if (age.isEmpty()) Text(text = "Enter age")
//                    innerTextField()
//                }
//            }
//        )
//
//        // Role selection button and options
//        Button(onClick = { showRoleOptions = !showRoleOptions }) {
//            Text(text = selectedRole)
//        }
//
//        if (showRoleOptions) {
//            Column {
//                Text(
//                    text = "Worker",
//                    modifier = Modifier
//                        .clickable {
//                            selectedRole = "Worker"
//                            showRoleOptions = false
//                        }
//                        .padding(8.dp)
//                )
//                Text(
//                    text = "Manager",
//                    modifier = Modifier
//                        .clickable {
//                            selectedRole = "Manager"
//                            showRoleOptions = false
//                        }
//                        .padding(8.dp)
//                )
//            }
//        }
//
//        // Button to add Person
//        Button(
//            onClick = {
//                if (name.isNotBlank() && age.toIntOrNull() != null) {
//                    val person = Person(name, age.toInt())
//                    personsByRole[selectedRole]?.add(person)
//                }
//            },
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text(text = "Add Person")
//        }
//
//        // Display all persons grouped by role
//        personsByRole.forEach { (role, people) ->
//            Text(
//                text = "$role:",
//                modifier = Modifier.padding(top = 16.dp)
//            )
//            people.forEach { person ->
//                Text(text = "${person.name}, ${person.age}")
//            }
//        }
//    }
//}








class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeekScheduleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyScheduleScreen()
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
fun MyScheduleScreen() {
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
    // Initialize the events map
//    val eventsByDay = remember { mutableStateMapOf<LocalDate, MutableList<Event>>() }

//    val eventsByDay = remember {
//        mutableStateMapOf<LocalDate, MutableList<Event>>().apply {
//            initialEvents.forEach { event ->
//                val eventDay = event.start.toLocalDate()
//                if (containsKey(eventDay)) {
//                    get(eventDay)?.add(event)
//                } else {
//                    this[eventDay] = mutableListOf(event)
//                }
//            }
//        }
//    }
    Column {
//         Add Event Composable
        AddEventComposable(
            eventsByDay = eventsByDay,
            onEventAdded = { event ->
                // Handle the event added, like refreshing the UI or triggering a recomposition
                println("Event Added: $event")
            },
            modifier = Modifier.padding(8.dp)
        )

        // Schedule Composable
        Schedule(
            events = eventsByDay,
            modifier = Modifier.fillMaxSize()
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScheduleScreenPreview() {
//    val sampleEvents = remember { SampleEvents.sampleEventsByDay }
//    WeekScheduleTheme {
//        ScheduleScreen(sampleEvents)
//    }
//}























/////////////////////////////////////////////////////////////////




//
//
//
//
//
//@Composable
//fun ScheduleScreen() {
//    val events = remember { mutableStateListOf(*sampleEvents.toTypedArray()) }
//    var startTime by remember { mutableStateOf("") }
//    var endTime by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    val randomColor = remember { Color((0xFF000000..0xFFFFFFFF).random()) }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        Schedule(
//            events = events,
//            modifier = Modifier.weight(1f)
//        )
//        AddEventSection(
//            startTime = startTime,
//            endTime = endTime,
//            description = description,
//            onStartTimeChange = { startTime = it },
//            onEndTimeChange = { endTime = it },
//            onDescriptionChange = { description = it },
//            onAddEventClick = {
//                val newEvent = Event(
//                    name = "New Event",
//                    color = randomColor,
//                    start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
//                    end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
//                    description = description
//                )
//                events.add(newEvent)
//            }
//        )
//    }
//}
//
//@Composable
//fun AddEventSection(
//    startTime: String,
//    endTime: String,
//    description: String,
//    onStartTimeChange: (String) -> Unit,
//    onEndTimeChange: (String) -> Unit,
//    onDescriptionChange: (String) -> Unit,
//    onAddEventClick: () -> Unit
//) {
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(16.dp)) {
//        TextField(
//            value = startTime,
//            onValueChange = onStartTimeChange,
//            label = { Text("Start Time (yyyy-MM-dd'T'HH:mm)") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        TextField(
//            value = endTime,
//            onValueChange = onEndTimeChange,
//            label = { Text("End Time (yyyy-MM-dd'T'HH:mm)") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        TextField(
//            value = description,
//            onValueChange = onDescriptionChange,
//            label = { Text("Description") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Button(
//            onClick = onAddEventClick,
//            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
//        ) {
//            Text("Add Event")
//        }
//    }
//}
