package csc221finalproject.calendarcalorie.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * The EntryAdapter is used to connect the Entries together.
 */
public class EntryAdapter extends ArrayAdapter<Entries> {

    public EntryAdapter(@NonNull Context context, List<Entries> entries) {
        super(context, 0, entries);
    }

    // Gets the view for the list of entries made
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Entries entry = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView entryCell = convertView.findViewById(R.id.entryCell);
        String entryTitle = entry.getName1() + " -- " + entry.getName2() + " Calories" + "\n" + "Time: " + CalendarUtilities.formattedTime(entry.getTime());
        entryCell.setText(entryTitle);
        return convertView;
    }
}
