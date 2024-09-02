package com.example.timemanager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreeDayCalendarView()
        }
    }
}


@Composable
fun ThreeDayCalendarView() {
    // Example: Restrict to 3 days only
    val startDate = LocalDate.now()
    val endDate = startDate.plusDays(2) // Show only three days

    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstDayOfWeek = DayOfWeek.MONDAY
    )

    WeekCalendar(
        state = state,
        dayContent = { day ->
            DayContent(day.date)
        }
    )
}

@Composable
fun CalendarView() {
    // Get the current year and month
    val currentYearMonth = YearMonth.now()

    // Define the start and end dates using YearMonth
    val startDate = currentYearMonth.atDay(1) // Start of the current month
    val endDate = currentYearMonth.atEndOfMonth() // End of the current month

    // Create state for the calendar
    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstDayOfWeek = DayOfWeek.MONDAY
    )

    // Create the week calendar view
    WeekCalendar(
        state = state,
        dayContent = { day ->
            DayContent(day.date)
        }
    )

}

@Composable
fun DayContent(date: LocalDate) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.White)
    ) {
        BasicText(
            text = date.dayOfMonth.toString(),
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
        )

        // Add hour slots and events
        for (hour in 1..24) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .background(Color(0xFFE0E0E0))
            ) {
                // Placeholder for Event Content
                BasicText(
                    text = "$hour:00",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

