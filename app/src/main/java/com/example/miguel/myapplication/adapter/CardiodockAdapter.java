package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.Cardiodock;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Cardiodick information.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 * */
public class CardiodockAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public CardiodockAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.cardiodock_layout, null,
                    true);
        TextView pulseMin = (TextView) convertView.findViewById(R.id.pulseMinCardiodock);
        TextView pulseMax = (TextView) convertView.findViewById(R.id.pulseMaxCardiodock);
        TextView systoleMin = (TextView) convertView.findViewById(R.id.systoleMinCardiodock);
        TextView systoleMax = (TextView) convertView.findViewById(R.id.systoleMaxCardiodock);
        TextView pulse = (TextView) convertView.findViewById(R.id.pulseCardiodock);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateCardiodock);
        Cardiodock cardiodock = (Cardiodock) data.get(position);
        pulseMin.setText(" " + cardiodock.getPulseTargetMin());
        pulseMax.setText(" " + cardiodock.getPulseTargetMax());
        systoleMin.setText(" " + cardiodock.getSystoleTargetMin());
        systoleMax.setText(" " + cardiodock.getSystoleTargetMax());
        pulse.setText(" " + cardiodock.getPulse());
        updateDate.setText(" " + CalendarString.calendarToString(cardiodock.getUpdatedDate()));
        return convertView;
    }
}
