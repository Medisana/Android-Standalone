package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.GlucodockInsulin;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Glucodock Insulin information.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class GlucodockInsulinAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public GlucodockInsulinAdapter(Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.glucodock_insulins_layout, null,
                    true);
        TextView insulinType = (TextView) convertView.findViewById(R.id.insulineTypeGlucoseInsulin);
        TextView insulin = (TextView) convertView.findViewById(R.id.insulinGlucoseInsulin);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateGlucoseInsulin);
        GlucodockInsulin glucodockInsulin = (GlucodockInsulin) data.get(position);
        insulinType.setText(" " + glucodockInsulin.getInsulinTypeName());
        insulin.setText(" " + glucodockInsulin.getInsulin());
        updateDate.setText(" " + CalendarString.calendarToString(glucodockInsulin.getUpdatedDate()));
        return convertView;
    }
}
