package csc221finalproject.calendarcalorie.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// user inputs food name and the calories associated with the food
// will be able to display the time/date of input

public class EventEdit extends AppCompatActivity {

    private EditText foodName;
    private TextView calorieCount, evenTime;

// our event edit can be the calorie counter implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
    }

    public void saveEventAction(View view) {
    }
}