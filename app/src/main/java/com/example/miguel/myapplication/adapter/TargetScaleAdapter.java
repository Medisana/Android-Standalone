package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.TargetScale;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Target Scale information.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class TargetScaleAdapter  extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public TargetScaleAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.target_scale_layout, null,
                    true);
        TextView muscleMass = (TextView) convertView.findViewById(R.id.weigthTargetScale);
        TextView bodyFat = (TextView) convertView.findViewById(R.id.bodyFatTargetScale);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateTargetScale);
        TargetScale targetScale = (TargetScale) data.get(position);
        muscleMass.setText(" " + targetScale.getBodyWeight());
        bodyFat.setText(" " + targetScale.getBodyFat());
        updateDate.setText(" " + CalendarString.calendarToString(targetScale.getUpdatedDate()));
        return convertView;
    }
}
