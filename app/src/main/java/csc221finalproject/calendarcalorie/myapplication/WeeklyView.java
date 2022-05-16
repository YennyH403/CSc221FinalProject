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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeeklyView extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private TextView totalCount;
    private RecyclerView calendarRecyclerView;
    private ListView entryList;

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
        entryList = findViewById(R.id.entryList);
        totalCount = findViewById(R.id.totalCalorieCount);

    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtilities.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtilities.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEntryAdapter();
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

    @Override
    public void onResume() {
        super.onResume();
        setEntryAdapter();
    }

    private void setEntryAdapter() {
        ArrayList<Entries> dailyEntries = Entries.foodEntriesForDate(CalendarUtilities.selectedDate);
        EntryAdapter entryAdapter = new EntryAdapter(getApplicationContext(), dailyEntries);
        entryList.setAdapter(entryAdapter);
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEdit.class));
    }

    public void newCountAction(View view) {
        double totalCalories = 0;
        for(Entries entry : Entries.foodEntriesList) {
            totalCalories += Double.parseDouble(entry.getName2());
        }

       totalCount.setText(String.valueOf("Total Calories " + totalCalories));
        // here should be the code to display the changes on the screen
    }

    public void newRecipeAction(View view) {
        startActivity(new Intent(this, RecipeView.class));
    }

    public void newWorkoutAction(View view) {
       startActivity(new Intent(this, WorkoutView.class));
    }
}