package com.example.timemanager.ScheduleGridManager

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.TextView

class ScheduleCell(private val context: Context) {

    fun createTimeCell(time: String, rowIndex: Int): View {
        val timeCell = TextView(context).apply {
            text = time
            gravity = Gravity.CENTER
            setBackgroundColor(Color.CYAN) // Color for time cells
            setTextColor(Color.BLACK) // Text color for visibility
            setTextSize(16f)
        }
        val layoutParams = GridLayout.LayoutParams(
            GridLayout.spec(rowIndex),
            GridLayout.spec(0)
        ).apply {
            width = GridLayout.LayoutParams.WRAP_CONTENT
            height = 120 // Height for time cells
        }
        timeCell.layoutParams = layoutParams
        return timeCell
    }

    fun createEventCell(color: Int, rowIndex: Int, colIndex: Int): View {
        val eventCell = TextView(context).apply {
            gravity = Gravity.CENTER
            setBackgroundColor(color) // Background color for event cells
            setTextSize(16f)
        }
        val layoutParams = GridLayout.LayoutParams(
            GridLayout.spec(rowIndex),
            GridLayout.spec(colIndex, 1f)
        ).apply {
            width = 0 // Width is 0 because we are using weight
            height = 120 // Height for event cells
        }
        eventCell.layoutParams = layoutParams
        return eventCell
    }
}
