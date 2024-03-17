package com.example.timemanager.ScheduleGridManager

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView

import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import java.time.LocalDate


import com.example.timemanager.ScheduleGridManager.*


class ScheduleGridManager(private val context: Context, private val gridLayout: GridLayout) {
    private val currentColumnNum: Int = 4
    private val currentDayNum: Int = 3
    private var todayEl: DayColumn? = null
    private var yesterdayEl: DayColumn? = null
    private var tomorrowEl: DayColumn? = null
    private val dayColumns = mutableListOf<DayColumn>()
    private val calendar = Calendar.getInstance()
    private var currentTime = 0


    fun getViewColumns() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val today = LocalDate.now()
        val yesterday = today.minus(1, ChronoUnit.DAYS)
        val tomorrow = today.plus(1, ChronoUnit.DAYS)

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
        columnTitles[1] = yesterdayEl?.getFormattedDate().toString()
        columnTitles[2] = todayEl?.getFormattedDate().toString()
        columnTitles[3] = tomorrowEl?.getFormattedDate().toString()

        for(column in columnTitles) {

        }




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




//class ScheduleGridManager(private val context: Context, private val gridLayout: GridLayout) {
//
//    fun generateScheduleGrid() {
//        val timeSlots: List<String> = (0..23).map { hour -> String.format("%02d:00", hour) }
//        val dates: List<String> = listOf("Yesterday", "Today", "Tomorrow")
//
//        // Set the row and column counts for the grid
//        gridLayout.rowCount = timeSlots.size + 1
//        gridLayout.columnCount = 4
//
//        // Create and add the date headers to the grid
//        dates.forEachIndexed { colIndex, date ->
//            val dateView: TextView = TextView(context).apply {
//                text = date
//                gravity = Gravity.CENTER
//                textSize = 16f // You can adjust this as needed
//                setBackgroundColor(Color.LTGRAY) // Set background color for headers
//            }
//            val dateParams: GridLayout.LayoutParams = GridLayout.LayoutParams(
//                GridLayout.spec(0),
//                GridLayout.spec(colIndex + 1)
//            ).apply {
//                width = 0 // This will be changed by weight
//                height = GridLayout.LayoutParams.WRAP_CONTENT
//                setMargins(8, 0, 8, 0) // Apply margins as needed
//            }
//            gridLayout.addView(dateView, dateParams)
//        }
//
//        // Populate the grid with time slots
//        timeSlots.forEachIndexed { rowIndex, time ->
//            // Time headers
//            val timeView: TextView = TextView(context).apply {
//                text = time
//                gravity = Gravity.CENTER_VERTICAL or Gravity.END
//                textSize = 16f
//                setBackgroundColor(Color.LTGRAY) // Set background color for time column
//            }
//            val timeParams: GridLayout.LayoutParams = GridLayout.LayoutParams(
//                GridLayout.spec(rowIndex + 1),
//                GridLayout.spec(0)
//            ).apply {
//                width = GridLayout.LayoutParams.WRAP_CONTENT
//                height = 200 // Adjust height to your preference
//                setMargins(8, 0, 8, 0)
//            }
//            gridLayout.addView(timeView, timeParams)
//
//            // Cells for each date
//            for (colIndex in 1..3) {
//                val cellView: FrameLayout = FrameLayout(context).apply {
//                    setBackgroundColor(Color.DKGRAY) // Change cell background color here
//                }
//                val cellParams: GridLayout.LayoutParams = GridLayout.LayoutParams(
//                    GridLayout.spec(rowIndex + 1),
//                    GridLayout.spec(colIndex)
//                ).apply {
//                    width = GridLayout.LayoutParams.MATCH_PARENT
//                    height = 200 // Match the height of the time slots
//                    setMargins(8, 8, 8, 8)
//                }
//                gridLayout.addView(cellView, cellParams)
//            }
//        }
//    }

