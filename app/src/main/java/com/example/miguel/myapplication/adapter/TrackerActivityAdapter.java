package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.BaseModel;
import com.example.miguel.myapplication.model.TrackerActivity;
import com.example.miguel.myapplication.model.TrackerActivityEntry;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the Tracker Activity information.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TrackerActivityAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public TrackerActivityAdapter (Context context, ArrayList<BaseModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(convertView==null)
            convertView = inflater.inflate(R.layout.tracker_activity_layout, null,
                    true);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateTrackerActivity);
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.trackerActivityEntryLayout);
        TrackerActivity trackerActivity = (TrackerActivity) data.get(position);
        updateDate.setText(" " + CalendarString.calendarToString(trackerActivity.getUpdatedDate()));
        List<TrackerActivityEntry> trackerActivityEntries = trackerActivity.getTrackerActivityEntries();
        for (int i = 0; i < trackerActivityEntries.size(); i++) {
            TextView duration = new TextView(context);
            duration.setText(context.getString(R.string.duration) + " " + trackerActivityEntries.get(i).getDuration()
            + " " + context.getString(R.string.calories) + " " + trackerActivityEntries.get(i).getCalories()
            + " " + context.getString(R.string.distance) + " " + trackerActivityEntries.get(i).getDistance());
            layout.addView(duration);
        }
        return convertView;
    }
}
