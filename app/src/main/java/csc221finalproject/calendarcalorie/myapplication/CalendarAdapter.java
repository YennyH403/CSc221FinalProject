package csc221finalproject.calendarcalorie.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarView>
{
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    /**
     * An array list of strings to represent the days of the month
     * */
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
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
        layoutParams.height = (int) (parent.getHeight() * 0.166666666); // each cell is 1/6 of the view
        return new CalendarView(view, onItemListener);
    }

    /**
     * onBindViewHolder is called by the RecyclerView to display the data at the specified position
     */
    @Override
    public void onBindViewHolder(@NonNull CalendarView holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    /**
     * A listener interface for receiving item events.
     */
    public interface OnItemListener {
        // item click event handler to perform specific actions on client side
        void onItemClick(int position, String dayText);
    }
}