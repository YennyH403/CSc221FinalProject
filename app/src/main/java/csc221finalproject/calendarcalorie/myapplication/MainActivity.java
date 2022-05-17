package csc221finalproject.calendarcalorie.myapplication;

import static csc221finalproject.calendarcalorie.myapplication.CalendarUtilities.daysInMonthArray;
import static csc221finalproject.calendarcalorie.myapplication.CalendarUtilities.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private TextView textView; // textview for the light and dark mode
    private RecyclerView calendarRecyclerView;
    private Switch dmSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // sets the style for our theme
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkMode);
        } else {
            setTheme(R.style.Theme_CSc221FinalProject);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        CalendarUtilities.selectedDate = LocalDate.now();
        setMonthView();
        dmSwitch = findViewById(R.id.mode);
        textView = findViewById(R.id.darkModeText);

        // double check this switch and its if statements
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            dmSwitch.setChecked(true);
        }
        // when toggled, the text is supposed to change
        dmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    textView.setText("Dark Mode");
                    reset();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    textView.setText("Light Mode");
                    reset();
                }
            }
        });
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTextView);
    }

    // sets the month view from our calendar utilities and recyclerview
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtilities.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtilities.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    // onclick actions that allows us to go back to the previous month
    public void previousMonthAction(View view) {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.minusMonths(1);
        setMonthView();
    }

    // onclick actions that allows us to go forward a month
    public void nextMonthAction(View view) {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.plusMonths(1);
        setMonthView();
    }

    // this is what shows the highlighted date when clicked
    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null) {
            CalendarUtilities.selectedDate = date;
            setMonthView();
        }
    }

    // goes to the weekly view
    public void weeklyAction(View view) {
        startActivity(new Intent(this, WeeklyView.class));
    }
}