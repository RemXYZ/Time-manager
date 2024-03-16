package com.example.timemanager.ScheduleGridManager

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat



class ScheduleGridManager(private val context: Context, private val gridLayout: GridLayout) {

    fun populateScheduleGrid() {
        // Define the titles for your columns
        val columnTitles = listOf("Time", "Yesterday", "Today", "Tomorrow")

        // Set column count to 4 (Time + 3 days)
        gridLayout.columnCount = 4
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
        val timeView = TextView(context).apply {
            text = time
            gravity = Gravity.CENTER
            setBackgroundColor(Color.CYAN) // Color for time cells
            setTextSize(16f)
        }
        val timeParams = GridLayout.LayoutParams(
            GridLayout.spec(rowIndex), // Row index
            GridLayout.spec(0) // Column index for time
        ).apply {
            width = GridLayout.LayoutParams.WRAP_CONTENT
            height = 120 // Increased row height
        }
        gridLayout.addView(timeView, timeParams)
    }

    private fun addEventCells(rowIndex: Int) {
        // Add cells for each hour and date with color coding for better visibility
        val colors = listOf(Color.LTGRAY, Color.GRAY, Color.DKGRAY)
        for (colIndex in 1..3) {
            val eventCellView = TextView(context).apply {
                gravity = Gravity.CENTER
                setBackgroundColor(colors[colIndex - 1])
                setTextSize(16f)
            }
            val eventCellParams = GridLayout.LayoutParams(
                GridLayout.spec(rowIndex), // Row index
                GridLayout.spec(colIndex, 1f) // Column index with weight
            ).apply {
                width = 0 // Width is 0 because we are using weight
                height = 120 // Increased row height
            }
            gridLayout.addView(eventCellView, eventCellParams)
        }
    }
}




//class ScheduleGridManager(private val context: Context, private val gridLayout: GridLayout) {
//
//    fun populateScheduleGrid() {
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

