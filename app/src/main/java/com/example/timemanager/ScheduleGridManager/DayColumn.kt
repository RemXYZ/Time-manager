package com.example.timemanager.ScheduleGridManager

import android.content.Context
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.Locale
import android.widget.GridLayout


import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DayColumn(private val context: Context, private val gridLayout: GridLayout) {
    private val events = mutableListOf<Event>()
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val zoneId = ZoneId.systemDefault() // Or specify a ZoneId, e.g., ZoneId.of("Europe/Paris")
    private var date: ZonedDateTime = ZonedDateTime.now(zoneId)
    val scheduleCells = Array(24) { ScheduleCell(context) } // Assuming each cell represents one hour

//    private val date: LocalDateTime = LocalDateTime.now();


//    init{
//
//    }
//    init {
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.adapter = ScheduleCellAdapter(context)
//    }

    fun addEvent(event: Event) {
//        val startHour = event.startTime.hour
//        val endHour = event.endTime.hour

        // Loop through the hours the event spans and add the event to each cell
//        for (hour in startHour..endHour) {
//            scheduleCells[hour].addEvent(event)
//        }
    }

    fun removeEvent(event: Event) {
        events.remove(event)
    }

    fun findEventsByDate(date: LocalDateTime): List<Event> {
        return events.filter { event ->
            event.getStartTime().toLocalDate() == date.toLocalDate()
        }
    }

    private fun sortEventsByStartTime() {
        events.sortBy { it.getStartTime() }
    }

    fun setDiffDate(difDayArg: Int) {


        val difDay: Long = difDayArg.toLong()
        date = date.plusDays(difDay);

    }

    // Czy warto robić inną instancje klasy, aby dodaqć tam metodę nie dokońca związaną z tematem tej klasy?
    fun getFormattedWeekDate() : String{
//         Define a formatter with the desired pattern
        val formatter = DateTimeFormatter.ofPattern("EEEE\ndd.MM.yy")

        // Format the LocalDateTime instance to a String using the formatter
        val formattedDateTime = date.format(formatter)

        println("Formatted DateTime: $formattedDateTime");
        return formattedDateTime;
//         Print the formatted date-time string
    }

    fun getDate(): ZonedDateTime {
        return date
    }

    private fun addHours() {
        // Add time slots and events for each hour
        for (hour in 0..23) {
            val time = String.format("%02d:00", hour)
            addHourCells(hour + 1, time) // Increment row index by 1 to account for the title row
            addEventCells(hour + 1) // Increment row index by 1 to account for the title row
        }

        // Add an additional row for 24:00
        addHourCells(24, "24:00")
        addEventCells(24)
    }

    private fun addHourCells(rowIndex: Int, time: String) {
        val scheduleCell = ScheduleCell(context)
        val timeCell = scheduleCell.createTimeCell(time, rowIndex)
        gridLayout.addView(timeCell)
    }

    private fun addEventCells(rowIndex: Int) {
        val scheduleCell = ScheduleCell(context)
        val colors = listOf(Color.LTGRAY, Color.GRAY, Color.DKGRAY)
        for (colIndex in 1..3) {
            val eventCell = scheduleCell.createEventCell(colors[colIndex - 1], rowIndex, colIndex)
            gridLayout.addView(eventCell)
        }
    }

    // Other functionalities related to a day can be added here, such as getting the list of events
}
