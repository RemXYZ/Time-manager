package com.example.timemanager.simple_calendar_java;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timemanager.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarDailyAdapter
{

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;

    private final View dailyView;
    private final View mainView;
    private final Context context;


    public CalendarDailyAdapter(Context context, View mainView) {
        dailyView = mainView.findViewById(R.id.calendarDailyView);
        this.mainView = mainView;
        this.context = context;
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setDayView();
    }

    private void initWidgets()
    {

        monthDayText = dailyView.findViewById(R.id.monthDayText);
        dayOfWeekTV = dailyView.findViewById(R.id.dayOfWeekTV);
        hourListView = dailyView.findViewById(R.id.hourListView);
        bindOnClickListeners();
    }

    private void bindOnClickListeners(){
        Button nextDayButton = mainView.findViewById(R.id.nextDayButton);
        Button previousDayButton = mainView.findViewById(R.id.previousDayButton);
        Button newEventButton = mainView.findViewById(R.id.newEventButton);

        nextDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDayAction(v);
            }
        });

        previousDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousDayAction(v);
            }
        });

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEventAction(v);
            }
        });
    }

    private void setDayView()
    {
        monthDayText.setText(CalendarUtils.monthDayFromDate(CalendarUtils.selectedDate));
        String dayOfWeek = CalendarUtils.selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter();
    }

    private void setHourAdapter()
    {
        HourAdapter hourAdapter = new HourAdapter(this.context, hourEventList());
        hourListView.setAdapter(hourAdapter);
    }

    private ArrayList<HourEvent> hourEventList()
    {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0; hour < 24; hour++)
        {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Event> events = Event.eventsForDateAndTime(CalendarUtils.selectedDate, time);
            HourEvent hourEvent = new HourEvent(time, events);
            list.add(hourEvent);
        }

        return list;
    }

    public void previousDayAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }

    public void newEventAction(View view)
    {
        context.startActivity(new Intent(context, EventEditActivity.class));
    }
}