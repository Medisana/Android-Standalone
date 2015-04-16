package com.example.miguel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.GlucodockMeal;
import com.example.miguel.myapplication.util.CalendarString;

import java.util.ArrayList;

/**
 * Adapter for the Glucodock Meal information.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class GlucodockMealAdapter extends BaseAdapter {

    private ArrayList<BaseModel> data = new ArrayList<>();
    private Context context;

    public GlucodockMealAdapter (Context context, ArrayList<BaseModel> data) {
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
            convertView = inflater.inflate(R.layout.glucodock_meal_layout, null,
                    true);
        TextView carbohydrates = (TextView) convertView.findViewById(R.id.carbohydratesGlucoseMeal);
        TextView updateDate = (TextView) convertView.findViewById(R.id.updateDateGlucoseMeal);
        GlucodockMeal glucodockMeal = (GlucodockMeal) data.get(position);
        carbohydrates.setText(" " + glucodockMeal.getCarbohydrates());
        updateDate.setText(" " + CalendarString.calendarToString(glucodockMeal.getUpdatedDate()));
        return convertView;
    }
}
