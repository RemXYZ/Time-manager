package com.example.timemanager


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import java.time.LocalDate

class MainActivityCopy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            CustomThreeDayCalendarView()
//        }
    }
}
//
//// Data model for events
//data class Event(val id: Int, val name: String, var offset: Offset)
//
//@Composable
//fun CustomThreeDayCalendarView() {
//    // State to hold events for each day
//    val days = remember { listOf(LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)) }
//    var events by remember {
//        mutableStateOf(
//            listOf(
//                Event(1, "Meeting", Offset(0f, 0f)),
//                Event(2, "Workout", Offset(0f, 100f))
//            )
//        )
//    }
//
//    Row(modifier = Modifier.fillMaxSize()) {
//        // Column for hours
//        Column(
//            modifier = Modifier
//                .width(60.dp)
//                .fillMaxHeight()
//                .background(Color(0xFF202020))
//        ) {
//            for (hour in 9..14) {
//                Box(
//                    modifier = Modifier
//                        .height(60.dp)
//                        .fillMaxWidth()
//                        .padding(4.dp)
//                        .background(Color(0xFF202020))
//                ) {
//                    BasicText(
//                        text = "$hour:00",
//                        modifier = Modifier.padding(8.dp),
//                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp, color = Color.White)
//                    )
//                }
//            }
//        }
//
//        // Create a column for each day
//        days.forEach { day ->
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(8.dp)
//                    .background(Color(0xFFEFEFEF))
//            ) {
//                BasicText(
//                    text = day.toString(),
//                    modifier = Modifier.padding(8.dp),
//                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
//                )
//                TimeSlots(events, onEventDrag = { event, newOffset ->
//                    events = events.map {
//                        if (it.id == event.id) it.copy(offset = newOffset) else it
//                    }
//                })
//            }
//        }
//    }
//}
//
//@Composable
//fun TimeSlots(events: List<Event>, onEventDrag: (Event, Offset) -> Unit) {
//    // Create time slots
//    for (hour in 9..14) { // 9 AM to 2 PM
//        Box(
//            modifier = Modifier
//                .height(60.dp)
//                .fillMaxWidth()
//                .padding(4.dp)
//                .background(Color.White)
//        ) {
//            // Events placed inside the respective row
//            events.forEach { event ->
//                if (eventShouldBeInHour(event, hour)) {
//                    DraggableEvent(event = event, onDrag = onEventDrag)
//                }
//            }
//        }
//    }
//}
//
//// Determine if the event should be in the current hour slot
//fun eventShouldBeInHour(event: Event, hour: Int): Boolean {
//    // Add logic here to determine if an event belongs in this hour slot
//    // Placeholder: For simplicity, we assume all events belong in all slots
//    return true
//}
//
//@Composable
//fun DraggableEvent(event: Event, onDrag: (Event, Offset) -> Unit) {
//    var offsetX by remember { mutableStateOf(event.offset.x) }
//    var offsetY by remember { mutableStateOf(event.offset.y) }
//
//    Box(
//        modifier = Modifier
//            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//            .background(Color.Cyan)
//            .pointerInput(Unit) {
//                detectDragGestures { change, dragAmount ->
//                    change.consume() // Consume the gesture event
//                    offsetX += dragAmount.x
//                    offsetY += dragAmount.y
//                    onDrag(event, Offset(offsetX, offsetY))
//                }
//            }
//            .padding(8.dp)
//    ) {
//        BasicText(text = event.name, style = MaterialTheme.typography.bodySmall)
//    }
//}
//
//
