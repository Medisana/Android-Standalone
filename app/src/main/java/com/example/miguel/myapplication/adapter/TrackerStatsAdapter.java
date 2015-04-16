package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.BaseModel;
import com.example.miguel.myapplication.model.TrackerStats;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Tracker Stats information.
 *
 * @author Miguel Francisco García del Moral Muñoz..
 */
public class TrackerStatsAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public TrackerStatsAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.tracker_stats, null,
                    true);
        TextView pulseMax = (TextView) convertView.findViewById(R.id.badSleepQDTrackerStats);
        TextView systoleMin = (TextView) convertView.findViewById(R.id.mediumSleepQDTrackerStats);
        TextView systoleMax = (TextView) convertView.findViewById(R.id.goodSleepQDTrackerStats);
        TextView pulse = (TextView) convertView.findViewById(R.id.excellentSleepQDTrackerStats);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateTrackerStats);
        TrackerStats trackerStats = (TrackerStats) data.get(position);
        pulseMax.setText(" " + trackerStats.getBadSleepQualityDuration());
        systoleMin.setText(" " + trackerStats.getMediumSleepQualityDuration());
        systoleMax.setText(" " + trackerStats.getGoodSleepQualityDuration());
        pulse.setText(" " + trackerStats.getExcellentSleepQualityDuration());
        updateDate.setText(" " + CalendarString.calendarToString(trackerStats.getUpdatedDate()));
        return convertView;
    }
}
