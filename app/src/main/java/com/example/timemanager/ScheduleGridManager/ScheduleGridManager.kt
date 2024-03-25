package com.example.timemanager.ScheduleGridManager

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView


import java.time.LocalDate


import com.example.timemanager.ScheduleGridManager.*


class ScheduleGridManager(private val context: Context, private val gridLayout: GridLayout) {
    private val currentColumnNum: Int = 4
    private val currentDayNum: Int = 3


    fun getViewColumns() {

        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val tomorrow = today.plusDays(1)

//        val now = LocalDateTime.now()
//        val yesterday = now.minusDays(1)
//        val tomorrow = now.plusDays(1)

        println("Yesterday: $yesterday")
        println("Today: $today")
        println("Tomorrow: $tomorrow")

//        for (date in currentColumnNum-1 downTo 0) {
//
//            // Musze po prostu dodac licznik daty do objektu i tam niech tworzy text
//            // I tam beda sie znajdowac inne encje, takie jak wlasnie emoji
//            dayColumns.add(DayColumn(calendar, currentTime))
//
//        }
//// Today's date
//        var todayEl: String = dateFormat.format(calendar.time)
//        todayEl = DayColumn(calendar)
////        println("Current time: $currentTime")
//
//// Yesterday's date
//        calendar.add(Calendar.DAY_OF_YEAR, --currentTime)
//        var yesterdayDate = dateFormat.format(calendar.time)
//        todayEl = DayColumn(calendar)
//
//        currentTime+=3
//// Tomorrow's date
//        calendar.add(Calendar.DAY_OF_YEAR, currentTime)
//        var tomorrowDate = dateFormat.format(calendar.time)



//        println("Current time: $currentTime")
    }

    fun generateScheduleGrid() {
        // Define the titles for your columns
        val columnTitles = mutableListOf("Time", "Yesterday", "Today", "Tomorrow")



        getViewColumns()
//        columnTitles[1] = yesterdayEl?.getFormattedDate().toString()
//        columnTitles[2] = todayEl?.getFormattedDate().toString()
//        columnTitles[3] = tomorrowEl?.getFormattedDate().toString()
//
//        for(column in columnTitles) {

//        }




        // RENDER PART
        // Set column count to 4 (Time + 3 days)
        gridLayout.columnCount = currentColumnNum
        gridLayout.useDefaultMargins = true

        // Add titles to the grid
        addTitles(columnTitles)

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

    private fun addTitles(titles: List<String>) {
        titles.forEachIndexed { index, title ->
            val titleView = TextView(context).apply {
                text = title
                gravity = Gravity.CENTER
                setBackgroundColor(Color.CYAN) // Color for titles
                setTextSize(16f)
                // Ensure text color contrasts with background color for visibility
                setTextColor(Color.BLACK)
            }
            val params = GridLayout.LayoutParams(
                GridLayout.spec(0, GridLayout.FILL), // Specify that this is the first row for headers
                GridLayout.spec(index, 1f) // Column index with weight
            ).apply {
                width = GridLayout.LayoutParams.WRAP_CONTENT // Use column weight
                height = GridLayout.LayoutParams.WRAP_CONTENT // Set the header height
            }
            gridLayout.addView(titleView, params)
        }
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
}



