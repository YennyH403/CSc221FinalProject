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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeeklyView extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    // controls for layout
    private TextView monthYearText;
    private TextView totalCalorieCount;
    private RecyclerView calendarRecyclerView;
    private ListView entryList;
    private Button btnRecipe, btnWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);
        initWidgets();
        setWeekView();

        btnRecipe = findViewById(R.id.btnRecipe);
        btnWorkout = findViewById(R.id.btnWorkout);

        // Setting an on click listener to check if buttons are working
        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WeeklyView.this, "You clicked Recipe!", Toast.LENGTH_SHORT).show();
            }
        });

        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WeeklyView.this, "You clicked Workout!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // defining the controls
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTextView);
        entryList = findViewById(R.id.entryList);
        totalCalorieCount = findViewById(R.id.totalCalorieCount);
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

    // highlights the date
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

    // goes to the event edit
    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEdit.class));
    }

    // count it button action
    public void newCountAction(View view) {
        double totalCalories = 0;
        for(Entries entry : Entries.foodEntriesList) {
            totalCalories += Double.parseDouble(entry.getName2());
        }
    // here should be the code to display the changes on the screen
        totalCalorieCount.setText(String.valueOf("Total Calories: " + totalCalories));
    }

}