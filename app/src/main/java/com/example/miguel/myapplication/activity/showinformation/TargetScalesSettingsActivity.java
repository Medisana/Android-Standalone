package com.example.miguel.myapplication.activity.showinformation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.TargetScaleSettings;
import com.example.miguel.myapplication.util.CalendarString;
import com.example.miguel.myapplication.util.ConstantsStorage;

/**
 * This activity show the settings of Target Scales returned from the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TargetScalesSettingsActivity extends ActionBarActivity {

    private TargetScaleSettings targetScaleSettings = new TargetScaleSettings();

    private TextView id;
    private TextView enterMode;
    private TextView measurementDate;
    private TextView measureUnit;
    private TextView moduleSerialId;
    private TextView updatedDate;
    private TextView athleteMode;
    private TextView active;
    private TextView targetWeight;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_scales_settings);
        getInformation();
        loadViews();
        if (getInformation()) printInformation();
    }

    private boolean getInformation() {
        try {
            targetScaleSettings = (TargetScaleSettings) getIntent().getSerializableExtra(ConstantsStorage.TARGET_SCALES_SETTINGS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void loadViews() {
        measurementDate = (TextView) findViewById(R.id.measurementDateTargetScaleSettings);
        moduleSerialId = (TextView) findViewById(R.id.moduleSerialTargetScaleSettings);
        updatedDate = (TextView) findViewById(R.id.updateDateTargetScaleSettings);
        version = (TextView) findViewById(R.id.versionTargetScaleSettings);
        id = (TextView) findViewById(R.id.idTargetScaleSettings);
        active = (TextView) findViewById(R.id.activeTargetScaleSettings);
        enterMode = (TextView) findViewById(R.id.enterModeTargetScaleSettings);
        measureUnit = (TextView) findViewById(R.id.measureUnitTargetScaleSettings);
        athleteMode = (TextView) findViewById(R.id.athleteModeTargetScaleSettings);
        targetWeight = (TextView) findViewById(R.id.targetWeighTargetScaleSettings);
    }

    private void printInformation () {
        measurementDate.setText(" " + CalendarString.calendarToString(targetScaleSettings.getMeasurementDate()));
        moduleSerialId.setText(" " + targetScaleSettings.getModuleSerialId());
        updatedDate.setText(" " + CalendarString.calendarToString(targetScaleSettings.getUpdatedDate()));
        version.setText(" " + targetScaleSettings.getVersion().toString());
        id.setText(" " + targetScaleSettings.getId());
        active.setText(" " + targetScaleSettings.getActive().toString());
        enterMode.setText(" " + targetScaleSettings.getEnterMode().toString());
        measureUnit.setText(" " + targetScaleSettings.getMeasureUnit().toString());
        athleteMode.setText(" " + targetScaleSettings.getAthleteMode().toString());
        targetWeight.setText(" " + targetScaleSettings.getTargetWeight().toString());
    }
}
