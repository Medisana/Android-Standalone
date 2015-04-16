package com.example.miguel.myapplication.activity.showinformation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.GlucoseSettings;
import com.example.miguel.myapplication.util.CalendarString;
import com.example.miguel.myapplication.util.ConstantsStorage;

/**
 * This activity show the settings of Glucose returned from the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class GlucoseSettingsActivity extends ActionBarActivity {

    private GlucoseSettings glucoseSettings = new GlucoseSettings();

    private TextView id;
    private TextView measurementDate;
    private TextView moduleSerialId;
    private TextView bloodGlucoseTargetMax;
    private TextView updatedDate;
    private TextView active;
    private TextView bloodGlucoseTargetMin;
    private TextView glucoseMeasureUnit;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_settings);
        getInformation();
        loadViews();
        if (getInformation()) printInformation();
    }

    private boolean getInformation() {
        try {
            glucoseSettings = (GlucoseSettings) getIntent().getSerializableExtra(ConstantsStorage.GLUCODOCK_GLUCOSE_SETTINGS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void loadViews() {
        measurementDate = (TextView) findViewById(R.id.measurementDateGlucoseSettings);
        moduleSerialId = (TextView) findViewById(R.id.moduleIdGlucoseSettings);
        updatedDate = (TextView) findViewById(R.id.updateDateGlucoseSettings);
        version = (TextView) findViewById(R.id.versionGlucoseSettings);
        id = (TextView) findViewById(R.id.idGlucoseSettings);
        active = (TextView) findViewById(R.id.activeGlucoseSettings);
        bloodGlucoseTargetMin = (TextView) findViewById(R.id.bloodMinGlucoseSettings);
        bloodGlucoseTargetMax = (TextView) findViewById(R.id.bloodMaxGlucoseSettings);
        glucoseMeasureUnit = (TextView) findViewById(R.id.glucoseMeasureGlucoseSettings);
    }

    private void printInformation () {
        measurementDate.setText(" " + CalendarString.calendarToString(glucoseSettings.getMeasurementDate()));
        moduleSerialId.setText(" " + glucoseSettings.getModuleSerialId());
        updatedDate.setText(" " + CalendarString.calendarToString(glucoseSettings.getUpdatedDate()));
        version.setText(" " + glucoseSettings.getVersion().toString());
        id.setText(" " + glucoseSettings.getId());
        active.setText(" " + glucoseSettings.getActive().toString());
        bloodGlucoseTargetMin.setText(" " + glucoseSettings.getBloodGlucoseTargetMin());
        bloodGlucoseTargetMax.setText(" " + glucoseSettings.getBloodGlucoseTargetMin());
        glucoseMeasureUnit.setText(" " + glucoseSettings.getGlucoseMeasureUnit());
    }
}
