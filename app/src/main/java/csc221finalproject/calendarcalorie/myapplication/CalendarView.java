package csc221finalproject.calendarcalorie.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarView extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ArrayList<LocalDate> days;
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;
    public final View parentView;

    public CalendarView(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDay);
        this.onItemListener = onItemListener;
        parentView = itemView.findViewById(R.id.parentView);
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
