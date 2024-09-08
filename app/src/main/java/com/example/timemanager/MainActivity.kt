package com.example.timemanager


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            WeekScheduleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Schedule(events = sampleEvents)
//                }
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
                    Schedule(events = sampleEvents)
                }
            }
        }
    }
}


val sampleEvents = SampleEvents.sampleEvents


@Composable
fun MyScheduleScreen() {
    // Create a mutable state for the list of events
    val events = remember { mutableStateListOf(*SampleEvents.sampleEvents.toTypedArray()) }

    Column {
        // Button to add a new event
        Button(onClick = {
            // Create a new event
            val newEvent = Event(
                name = "Dynamic Event",
                color = Color.Magenta,
                start = LocalDateTime.now().withHour(18).withMinute(0),
                end = LocalDateTime.now().withHour(19).withMinute(0),
                description = "This event was added dynamically."
            )

            // Add the new event to the mutable state list
            events.add(newEvent)
        }) {
            Text("Add Event")
        }

        // Pass the updated list to the Schedule composable
        Schedule(events = events)
    }
}






























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
