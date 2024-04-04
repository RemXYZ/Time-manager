package com.example.timemanager.simple_calendar_java;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timemanager.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarManager {
    private final View calendarView;
    private final View mainView;
    private final Context context;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;



    public CalendarManager(Context context, View mainView) {
        calendarView = mainView.findViewById(R.id.customCalendarView);
        this.mainView = mainView;
        this.context = context;
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = calendarView.findViewById(R.id.calendarRecyclerView);
        monthYearText = calendarView.findViewById(R.id.monthYearTV);

        Button previousMonthButton = calendarView.findViewById(R.id.previousMonthButton);
        previousMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonthAction(v);
            }
        });

        Button nextMonthButton = calendarView.findViewById(R.id.nextMonthButton);
        nextMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonthAction(v);
            }
        });

        Button weeklyButton = calendarView.findViewById(R.id.weeklyButton);
        weeklyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weeklyAction(v);
            }
        });
    }
    private void setMonthView() {
        monthYearText.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = CalendarUtils.daysInMonthArray();
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, new CalendarAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position, LocalDate date) {
                CalendarUtils.selectedDate = date;
                setMonthView();
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this.context, 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    public void weeklyAction(View view) {
        Intent intent = new Intent(this.context, WeekViewActivity.class);
        this.context.startActivity(intent);
    }
}