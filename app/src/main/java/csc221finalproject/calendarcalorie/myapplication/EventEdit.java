package csc221finalproject.calendarcalorie.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;

// user inputs food name and the calories associated with the food
// will be able to display the time of input

public class EventEdit extends AppCompatActivity {

    private EditText foodName, calorieCount;
    private TextView eventDate, eventTime;
    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();

        time = LocalTime.now();
        eventDate.setText("Date: " + CalendarUtilities.formattedDate(CalendarUtilities.selectedDate));
        eventTime.setText("Time: " + CalendarUtilities.formattedTime(time));

    }

    // defines the layout
    private void initWidgets() {
        foodName = findViewById(R.id.foodName);
        calorieCount = findViewById(R.id.foodCalorie);
        eventTime = findViewById(R.id.eventTime);
        eventDate = findViewById(R.id.eventDate);
    }

    // The save button, this is where the entries are stored and entered
    public void saveEventAction(View view) {
        String entry1 = foodName.getText().toString();
        String entry2 = calorieCount.getText().toString();
        Entries entries1 = new Entries(entry1, entry2, CalendarUtilities.selectedDate, time);
        Entries.foodEntriesList.add(entries1);
        finish();
    }
}