package com.example.miguel.myapplication.activity.showinformation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.adapter.CardiodockAdapter;
import com.example.miguel.myapplication.adapter.GlucodockGlucoseAdapter;
import com.example.miguel.myapplication.adapter.GlucodockInsulinAdapter;
import com.example.miguel.myapplication.adapter.GlucodockMealAdapter;
import com.example.miguel.myapplication.adapter.OxymeterAdapter;
import com.example.miguel.myapplication.adapter.TargetScaleAdapter;
import com.example.miguel.myapplication.adapter.ThermodockAdapter;
import com.example.miguel.myapplication.adapter.TrackerActivityAdapter;
import com.example.miguel.myapplication.adapter.TrackerPhaseAdapter;
import com.example.miguel.myapplication.adapter.TrackerSleepAdapter;
import com.example.miguel.myapplication.adapter.TrackerStatsAdapter;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.util.ConstantsStorage;

import java.util.ArrayList;

/**
 * Activity who contain a ListView to show the information returned by the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class DataListViewActivity extends ActionBarActivity {

    private static ArrayList<BaseModel> data = new ArrayList<>();
    private ListView dataListView;
    private TextView noDataTextView;
    private String moduleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list_view);
        loadViews();
        loadInformation();
        filterDataType();
    }

    private void loadViews() {
        dataListView = (ListView) findViewById(R.id.dataListView);
        noDataTextView = (TextView) findViewById(R.id.noDataDataListView);
        dataListView.setEmptyView(noDataTextView);
    }

    public static void setData (ArrayList<BaseModel> newData) {
        data = newData;
    }
    private void loadInformation() {
        moduleName = getIntent().getStringExtra(ConstantsStorage.MODULE_NAME);
    }

    /**
     * Here, the module name is filter in order to call the appropriate adapter for the ListView.
     */
    private void filterDataType() {
        if (data != null)
            if (!data.isEmpty())
                switch (moduleName) {
                    case ConstantsStorage.CARDIODOCKS:
                        dataListView.setAdapter(new CardiodockAdapter(this, data));
                        break;
                    case ConstantsStorage.GLUCODOCK_GLUCOSES:
                        dataListView.setAdapter(new GlucodockGlucoseAdapter(this, data));
                        break;
                    case ConstantsStorage.GLUCODOCK_INSULINS:
                        dataListView.setAdapter(new GlucodockInsulinAdapter(this, data));
                        break;
                    case ConstantsStorage.GLUCODOCK_MEALS:
                        dataListView.setAdapter(new GlucodockMealAdapter(this, data));
                        break;
                    case ConstantsStorage.OXYMETERS:
                        dataListView.setAdapter(new OxymeterAdapter(this, data));
                        break;
                    case ConstantsStorage.TARGET_SACLAES:
                        dataListView.setAdapter(new TargetScaleAdapter(this, data));
                        break;
                    case ConstantsStorage.THERMODOCK:
                        dataListView.setAdapter(new ThermodockAdapter(this, data));
                        break;
                    case ConstantsStorage.TRACKER_ACTIVITY:
                        dataListView.setAdapter(new TrackerActivityAdapter(this, data));
                        break;
                    case ConstantsStorage.TRACKER_PHASE:
                        dataListView.setAdapter(new TrackerPhaseAdapter(this, data));
                        break;
                    case ConstantsStorage.TRACKER_SLEEP:
                        dataListView.setAdapter(new TrackerSleepAdapter(this, data));
                        break;
                    case ConstantsStorage.TRACKER_STATS:
                        dataListView.setAdapter(new TrackerStatsAdapter(this, data));
                        break;
                }

    }
}
