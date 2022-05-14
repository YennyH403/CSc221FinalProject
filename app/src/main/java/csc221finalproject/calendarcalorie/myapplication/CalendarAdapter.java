package csc221finalproject.calendarcalorie.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarView>
{
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    /**
     * An array list of strings to represent the days of the month
     * */
    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    /**
     * The Calendar View uses onCreateViewHolder to create a new ViewHolder
     * initializes some private fields uses by RecyclerView.
     * While RecyclerView is the ViewGroup that contains the views corresponding to the data.
     * Also allows us to create a scrolling list.
     * RecyclerView is designed to display long list of items.
     * */
    public CalendarView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // layout inflater uses the layout XML file into its corresponding objects.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(days.size() > 15) { // month view
            layoutParams.height = (int) (parent.getHeight() * 0.16666666);
        } else {
            layoutParams.height = (int) parent.getHeight();
        }
        // layoutParams.height = (int) (parent.getHeight() * 0.166666666); // each cell is 1/6 of the view
        return new CalendarView(view, onItemListener, days);
    }

    /**
     * onBindViewHolder is called by the RecyclerView to display the data at the specified position
     */
    @Override
    public void onBindViewHolder(@NonNull CalendarView holder, int position) {
        final LocalDate date = days.get(position);
        if(date == null) {
            holder.dayOfMonth.setText("");
        } else {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(CalendarUtilities.selectedDate)) {
                holder.parentView.setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    /**
     * A listener interface for receiving item events.
     */
    public interface OnItemListener {
        // item click event handler to perform specific actions on client side
        void onItemClick(int position, LocalDate date);
    }
}