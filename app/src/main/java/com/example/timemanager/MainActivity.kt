package com.example.timemanager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

import com.example.timemanager.ScheduleGridManager.ScheduleGridManager
//import com.example.timemanager.simple_calendar_java.CalendarManager
//import com.example.timemanager.simple_calendar_java.CalendarDailyManager
//import com.example.timemanager.simple_calendar_java.CalendarAdapter
//import com.example.timemanager.simple_calendar_java.CalendarUtils
//import com.example.timemanager.simple_calendar_java.CalendarUtils.daysInMonthArray
//import com.example.timemanager.simple_calendar_java.CalendarUtils.monthYearFromDate
//import com.example.timemanager.simple_calendar_java.CustomCalendarView
//import com.example.timemanager.simple_calendar_java.WeekViewActivity

class MainActivity : AppCompatActivity() {
//    private lateinit var customCalendarView: CalendarManager
//    private lateinit var customCalendarDaily: CalendarDailyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val main: View = findViewById(R.id.main)
//        customCalendarDaily = CalendarDailyManager(this, main)
//        ScheduleGridManager(this, main).generateScheduleGrid()
        val recyclerView = findViewById<RecyclerView>(R.id.scheduleRecyclerView)
        ScheduleGridManager(recyclerView, this)

    }


}