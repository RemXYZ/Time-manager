package com.example.timemanager.ScheduleGridManager

import CalendarThreeDayAdapter
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R


import java.time.LocalDateTime


class ScheduleGridManager (private val recyclerView: RecyclerView, context: Context) {

    init {
        recyclerView.layoutManager = GridLayoutManager(context, 3) // 3 columns grid
        recyclerView.adapter = CalendarThreeDayAdapter(context)
    }

    private lateinit var context: Context;
    private lateinit var mainView: View ;
    private lateinit var gridLayout: GridLayout;
    private val currentColumnNum: Int = 4
    private val currentDayNum: Int = 3
    private val DayNumShifter: Int = 1;
    private val dayColumns: MutableList<DayColumn> = mutableListOf()
    private val columnTitles: MutableList<String> = mutableListOf("Time")
//    constructor(context: Context, mainView : View) : this() {
//        this.context = context
//        this.mainView = mainView
//        this.gridLayout = mainView.findViewById(R.id.scheduleGrid)
//    }
    fun generateDayColumns() {


        val now: LocalDateTime = LocalDateTime.now()
        for (i in 0 - DayNumShifter .. currentDayNum-1- DayNumShifter ){
//            println("What? " + i)
            val day: DayColumn = DayColumn(context, gridLayout);
            dayColumns.add(day);
            day.setDiffDate(i)

            columnTitles.add(day.getFormattedWeekDate())
        }

    }

    fun generateScheduleGrid() {
        // Define the titles for your columns




        generateDayColumns()
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
        addTitles()
        displayDayColumns();

    }

    fun displayDayColumns() {
        // Logic to display the day columns, potentially in a GridLayout or similar view structure
        dayColumns.forEach { dayColumn ->
            // Here you would create views for each day column and add them to your layout
            // Each DayColumn could also be responsible for rendering its own ScheduleCells
        }
    }



    private fun addTitles() {
        var titles: List<String> = columnTitles;
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



}



