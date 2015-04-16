package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.TrackerPhase;
import com.example.miguel.myapplication.dockclass.TrackerPhaseEntry;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;
import java.util.List;

/**Adapter for the Tracker Phase information.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TrackerPhaseAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public TrackerPhaseAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.tracker_phase_layout, null,
                    true);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateTrackerPhase);
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.trackerPhaseEntryLayout);
        TrackerPhase trackerPhase = (TrackerPhase) data.get(position);
        updateDate.setText(" " + CalendarString.calendarToString(trackerPhase.getUpdatedDate()));
        List<TrackerPhaseEntry> trackerPhaseEntry = trackerPhase.getTrackerPhaseEntries();
        for (int i = 0; i < trackerPhaseEntry.size(); i++) {
            TextView duration = new TextView(context);
            duration.setText(context.getString(R.string.duration) + " " + trackerPhaseEntry.get(i).getDuration()
                    + " " + context.getString(R.string.calories) + " " + trackerPhaseEntry.get(i).getCalories()
                    + " " + context.getString(R.string.distance) + " " + trackerPhaseEntry.get(i).getDistance());
            layout.addView(duration);
        }
        return convertView;
    }
}
