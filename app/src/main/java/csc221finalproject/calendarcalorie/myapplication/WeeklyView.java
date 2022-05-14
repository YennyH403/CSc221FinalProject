package csc221finalproject.calendarcalorie.myapplication;

import static csc221finalproject.calendarcalorie.myapplication.CalendarUtilities.daysInMonthArray;
import static csc221finalproject.calendarcalorie.myapplication.CalendarUtilities.daysInWeekArray;
import static csc221finalproject.calendarcalorie.myapplication.CalendarUtilities.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyView extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);
        initWidgets();
        setWeekView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTextView);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtilities.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtilities.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousWeekAction(View view) {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtilities.selectedDate = date;
        setWeekView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEdit.class));
    }

    public void newRecipeAction(View view) {
    }

    public void newWorkoutAction(View view) {
    }
}