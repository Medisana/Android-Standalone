package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.BaseModel;
import com.example.miguel.myapplication.model.GlucodockGlucose;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Glucodock information.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class GlucodockGlucoseAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public GlucodockGlucoseAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.glucodock_glucose_layout, null,
                    true);
        TextView bloodGlucose = (TextView) convertView.findViewById(R.id.bloodGlucoseGlucosedockGlucose);
        TextView bloodGlucoseMin = (TextView) convertView.findViewById(R.id.bloodGlucoseMinGlucosedockGlucose);
        TextView bloodGlucoseMax = (TextView) convertView.findViewById(R.id.bloodGlucoseMaxGlucosedockGlucose);
        TextView mealStatus = (TextView) convertView.findViewById(R.id.mealStatusGlucosedockGlucose);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateGlucosedockGlucose);
        GlucodockGlucose glucodockGlucose = (GlucodockGlucose) data.get(position);
        bloodGlucose.setText(" " + glucodockGlucose.getBloodGlucose());
        bloodGlucoseMin.setText(" " + glucodockGlucose.getBloodGlucoseTargetMin());
        bloodGlucoseMax.setText(" " + glucodockGlucose.getBloodGlucoseTargetMax());
        mealStatus.setText(" " + glucodockGlucose.getMealStatus());
        updateDate.setText(" " + CalendarString.calendarToString(glucodockGlucose.getUpdatedDate()));
        return convertView;
    }


}
