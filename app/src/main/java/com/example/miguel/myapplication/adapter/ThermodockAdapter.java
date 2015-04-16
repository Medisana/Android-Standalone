package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.BaseModel;
import com.example.miguel.myapplication.model.Thermodock;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Thermodock information.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class ThermodockAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public ThermodockAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.thermodock_layout, null,
                    true);
        TextView bodyTemperature = (TextView) convertView.findViewById(R.id.bodyTemperatureThermodock);
        TextView bodyTemperatureMin = (TextView) convertView.findViewById(R.id.bodyTemperatureMinThermodock);
        TextView bodyTemperatureMax = (TextView) convertView.findViewById(R.id.bodyTemperatureMaxThermodock);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateThermodock);
        Thermodock thermodock = (Thermodock) data.get(position);
        bodyTemperature.setText(" " + thermodock.getBodyTemperature());
        bodyTemperatureMin.setText(" " + thermodock.getBodyTemperatureTargetMin());
        bodyTemperatureMax.setText(" " + thermodock.getBodyTemperatureTargetMax());
        updateDate.setText(" " + CalendarString.calendarToString(thermodock.getUpdatedDate()));
        return convertView;
    }
}
