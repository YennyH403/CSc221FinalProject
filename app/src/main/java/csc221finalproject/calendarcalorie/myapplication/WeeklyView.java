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

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView entryList;
    // private Button btnCount;

    private Button btnRecipe, btnWorkout;
    // private ListView recipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);
        initWidgets();
        setWeekView();

        btnRecipe = findViewById(R.id.btnRecipe);
        btnWorkout = findViewById(R.id.btnWorkout);
        // recipeView = findViewById(R.id.recipeView);

        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // RequestQueue queue = Volley.newRequestQueue(WeeklyView.this);
                //                                String url = "https://www.metaweather.com/api/location/search/?query=london";
                //
                //                                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                //                                    @Override
                //                                    public void onResponse(JSONArray response) {
                //                                        Toast.makeText(WeeklyView.this, response.toString(), Toast.LENGTH_SHORT).show();
                //                                    }
                //                                }, new Response.ErrorListener() {
                //                                    @Override
                //                                    public void onErrorResponse(VolleyError error) {
                //                                        Toast.makeText(WeeklyView.this, "Something wrong", Toast.LENGTH_SHORT).show();
                //                                    }
                //                                });
                //                                queue.add(request);
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

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTextView);
        entryList = findViewById(R.id.entryList);
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

}