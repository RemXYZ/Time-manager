package com.example.timemanager

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.timemanager.ScheduleGridManager.ScheduleGridManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Wyrownuje content wzgledem przyciskow, kamery i td.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val scheduleGrid = findViewById<GridLayout>(R.id.scheduleGrid)
        val scheduleGridManager = ScheduleGridManager(this, scheduleGrid)
        scheduleGridManager.populateScheduleGrid()
    }
}