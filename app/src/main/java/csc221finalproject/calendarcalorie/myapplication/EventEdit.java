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
// will be able to display the time/date of input

public class EventEdit extends AppCompatActivity {

    private EditText foodName, calorieCount;
    private TextView eventDate, eventTime, totalCalorieCount;
    private LocalTime time;

// the calorie input
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();

        time = LocalTime.now();
        eventDate.setText("Date: " + CalendarUtilities.formattedDate(CalendarUtilities.selectedDate));
        eventTime.setText("Time: " + CalendarUtilities.formattedTime(time));

        // this is on the event edit, should be in the count it button
        /**
         * Button countIt= findViewById(R.id.addIt);
         *         countIt.setOnClickListener(new View.OnClickListener() {
         *             @Override
         *             public void onClick(View view) {
         *                 addCalorieCount();
         *             }
         *         });
         */
    }

    /**
     * private void addCalorieCount() {
     *         double count = Double.valueOf(calorieCount.getText().toString());
     *         double result = 0;
     *         for(int i = 0; i < count; i++) {
     *             result += count;
     *         }
     *         totalCalorieCount.setText("The result is: " + result);
     *     }
     */

    private void initWidgets() {
        foodName = findViewById(R.id.foodName);
        calorieCount = findViewById(R.id.foodCalorie);
        eventTime = findViewById(R.id.eventTime);
        eventDate = findViewById(R.id.eventDate);
        // totalCalorieCount = findViewById(R.id.totalCalorieCount);
    }

    public void saveEventAction(View view) {
        String entry1 = foodName.getText().toString();
        String entry2 = calorieCount.getText().toString();
        Entries entries1 = new Entries(entry1, entry2, CalendarUtilities.selectedDate, time);
        Entries.foodEntriesList.add(entries1);
        finish();
    }


}