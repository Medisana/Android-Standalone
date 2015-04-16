package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.Oxymeter;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Oxzmeter information.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class OxymeterAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public OxymeterAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.oxymeter_layout, null,
                    true);
        TextView systoleMin = (TextView) convertView.findViewById(R.id.saturationOxymeter);
        TextView systoleMax = (TextView) convertView.findViewById(R.id.saturationMinOxymeter);
        TextView pulse = (TextView) convertView.findViewById(R.id.saturationMaxOxymeter);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateOxymeter);
        Oxymeter oxymeter = (Oxymeter) data.get(position);
        systoleMin.setText(" " + oxymeter.getSaturation());
        systoleMax.setText(" " + oxymeter.getSaturationTargetMin());
        pulse.setText(" " + oxymeter.getSaturationTargetMax());
        updateDate.setText(" " + CalendarString.calendarToString(oxymeter.getUpdatedDate()));
        return convertView;
    }
}
