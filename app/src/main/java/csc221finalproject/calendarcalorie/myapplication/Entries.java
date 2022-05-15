package csc221finalproject.calendarcalorie.myapplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Entries {

    // returns all the save calories for the given day in a list
    public static ArrayList<Entries> foodEntriesList = new ArrayList<>();
    // public static ArrayList<Entries> calorieEntriesList = new ArrayList<>();

    public static ArrayList<Entries> foodEntriesForDate(LocalDate date) {
        ArrayList<Entries> entries = new ArrayList<>();

        // for each loop -- the entries from the calories entries, if date of the entry is equal to the date, add it to the entries
        for(Entries entry : foodEntriesList) {
            if(entry.getDate().equals(date)) {
                entries.add(entry);
            }
        }
        return entries;
    }

    /**
     *  public static ArrayList<Entries> calorieEntriesForDate(LocalDate date) {
     *         ArrayList<Entries> entries = new ArrayList<>();
     *
     *         // for each loop -- the entries from the calories entries, if date of the entry is equal to the date, add it to the entries
     *         for(Entries entry : calorieEntriesList) {
     *             if(entry.getDate().equals(date)) {
     *                 entries.add(entry);
     *             }
     *         }
     *         return entries;
     *     }
     */

    private String name1;
    private String name2;
    private LocalDate date;
    private LocalTime time;

    public Entries(String name1, String name2, LocalDate date, LocalTime time) {
        this.name1 = name1;
        this.name2 = name2;
        this.date = date;
        this.time = time;
    }

    public String getName1() {

        return name1;
    }

    public void setName1(String name1) {

        this.name1 = name1;
    }

    public String getName2() {

        return name2;
    }

    public void setName2(String name2) {

        this.name2 = name2;
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
