package csc221finalproject.calendarcalorie.myapplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Entries {

    // returns all the save calories for the given day in a list
    public static ArrayList<Entries> foodEntriesList = new ArrayList<>();

    public static ArrayList<Entries> foodEntriesForDate(LocalDate date) {
        ArrayList<Entries> entries = new ArrayList<>();

        // for each loop -- the entries from the calories entries,
        // if date of the entry is equal to the date, add it to the entries
        for(Entries entry : foodEntriesList) {
            if(entry.getDate().equals(date)) {
                entries.add(entry);
            }
        }
        return entries;
    }

    private String foodEntry;
    private String calorieEntry;
    private LocalDate date;
    private LocalTime time;

    public Entries(String foodEntry, String calorieEntry, LocalDate date, LocalTime time) {
        this.foodEntry = foodEntry;
        this.calorieEntry = calorieEntry;
        this.date = date;
        this.time = time;
    }

    public String getFoodEntry() {

        return foodEntry;
    }

    public void setFoodEntry(String foodEntry) {

        this.foodEntry = foodEntry;
    }

    public String getCalorieEntry() {

        return calorieEntry;
    }

    public void setCalorieEntry(String calorieEntry) {

        this.calorieEntry = calorieEntry;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public LocalTime getTime() {

        return time;
    }

    public void setTime(LocalTime time) {

        this.time = time;
    }
}
