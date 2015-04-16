package com.example.miguel.myapplication.activity.showinformation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.OxymeterSettings;
import com.example.miguel.myapplication.util.CalendarString;
import com.example.miguel.myapplication.util.ConstantsStorage;

/**
 * This activity show the settings of Oxymeter returned from the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class OxymeterSettingsActivity extends ActionBarActivity {

    private OxymeterSettings oxymeterSettings = new OxymeterSettings();

    private TextView id;
    private TextView measurementDate;
    private TextView pulseTargetMin;
    private TextView pulseTargetMax;
    private TextView moduleSerialId;
    private TextView saturationTargetMax;
    private TextView updatedDate;
    private TextView active;
    private TextView saturationTargetMin;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxymeter_settings);
        getInformation();
        loadViews();
        if (getInformation()) printInformation();
    }

    private boolean getInformation() {
        try {
            oxymeterSettings = (OxymeterSettings) getIntent().getSerializableExtra(ConstantsStorage.OXYMETERS_SETTINGS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void loadViews() {
        measurementDate = (TextView) findViewById(R.id.measuremetnDateOxymeterSettings);
        moduleSerialId = (TextView) findViewById(R.id.measuremetnDateOxymeterSettings);
        updatedDate = (TextView) findViewById(R.id.updateDateOxymeterSettings);
        version = (TextView) findViewById(R.id.versionOxymeterSettings);
        id = (TextView) findViewById(R.id.idOxymeterSettings);
        active = (TextView) findViewById(R.id.activeOxymeterSettings);
        pulseTargetMin = (TextView) findViewById(R.id.pulseMinOxymeterSettings);
        pulseTargetMax = (TextView) findViewById(R.id.pulseMaxOxymeterSettings);
        saturationTargetMin = (TextView) findViewById(R.id.saturationMinOxymeterSettings);
        saturationTargetMax = (TextView) findViewById(R.id.saturationMaxOxymeterSettings);
    }

    private void printInformation () {
        measurementDate.setText(" " + CalendarString.calendarToString(oxymeterSettings.getMeasurementDate()));
        moduleSerialId.setText(" " + oxymeterSettings.getModuleSerialId());
        updatedDate.setText(" " + CalendarString.calendarToString(oxymeterSettings.getUpdatedDate()));
        version.setText(" " + oxymeterSettings.getVersion().toString());
        id.setText(" " + oxymeterSettings.getId());
        active.setText(" " + oxymeterSettings.getActive().toString());
        pulseTargetMin.setText(" " + oxymeterSettings.getPulseTargetMin().toString());
        pulseTargetMax.setText(" " + oxymeterSettings.getPulseTargetMax().toString());
        saturationTargetMin.setText(" " + oxymeterSettings.getSaturationTargetMin().toString());
        saturationTargetMax.setText(" " + oxymeterSettings.getSaturationTargetMax().toString());
    }
}
