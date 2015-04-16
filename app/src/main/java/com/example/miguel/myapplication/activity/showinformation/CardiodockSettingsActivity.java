package com.example.miguel.myapplication.activity.showinformation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.model.CardiodockSettings;
import com.example.miguel.myapplication.util.CalendarString;
import com.example.miguel.myapplication.util.ConstantsStorage;

/**
 * This activity show the settings of Cardiodock returned from the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CardiodockSettingsActivity extends ActionBarActivity {

    private CardiodockSettings cardiodockSettings = new CardiodockSettings();

    private TextView id;
    private TextView measurementDate;
    private TextView systoleTargetMax;
    private TextView pulseTargetMin;
    private TextView pulseTargetMax;
    private TextView moduleSerialId;
    private TextView systoleTargetMin;
    private TextView updatedDate;
    private TextView diastoleTargetMin;
    private TextView active;
    private TextView diastoleTargetMax;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardiodock_settings);
        getInformation();
        loadViews();
        if (getInformation()) printInformation();
    }


    private boolean getInformation() {
        try {
            cardiodockSettings = (CardiodockSettings) getIntent().getSerializableExtra(ConstantsStorage.CARDIODOCK_SETTINGS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void loadViews() {
        measurementDate = (TextView) findViewById(R.id.measurementDateCardiodockSettings);
        moduleSerialId = (TextView) findViewById(R.id.moduleCardiodockSettings);
        updatedDate = (TextView) findViewById(R.id.updateDateCardiodockSettings);
        version = (TextView) findViewById(R.id.versionCardiodockSettings);
        id = (TextView) findViewById(R.id.idCardiodockSettings);
        systoleTargetMin = (TextView) findViewById(R.id.systoleMinCardiodockSettings);
        systoleTargetMax = (TextView) findViewById(R.id.sytoleMaxCardiodockSettings);
        diastoleTargetMin = (TextView) findViewById(R.id.diastoleMinCardiodockSettings);
        diastoleTargetMax = (TextView) findViewById(R.id.diastoleMaxCardiodockSettings);
        pulseTargetMin = (TextView) findViewById(R.id.pulseMinCardiodockSettings);
        pulseTargetMax = (TextView) findViewById(R.id.pulseMaxCardiodockSettings);
        active = (TextView) findViewById(R.id.activeCardiodockSettings);
    }

    private void printInformation () {
        measurementDate.setText(" " + CalendarString.calendarToString(cardiodockSettings.getMeasurementDate()));
        moduleSerialId.setText(" " + cardiodockSettings.getModuleSerialId());
        updatedDate.setText(" " + CalendarString.calendarToString(cardiodockSettings.getUpdatedDate()));
        version.setText(" " + cardiodockSettings.getVersion().toString());
        id.setText(" " + cardiodockSettings.getId());
        systoleTargetMin.setText(" " + cardiodockSettings.getSystoleTargetMin().toString());
        systoleTargetMax.setText(" " + cardiodockSettings.getSystoleTargetMax().toString());
        diastoleTargetMin.setText(" " + cardiodockSettings.getDiastoleTargetMin().toString());
        diastoleTargetMax.setText(" " + cardiodockSettings.getDiastoleTargetMax().toString());
        pulseTargetMin.setText(" " + cardiodockSettings.getPulseTargetMin().toString());
        pulseTargetMax.setText(" " + cardiodockSettings.getPulseTargetMax().toString());
        active.setText(" " + cardiodockSettings.getActive().toString());
    }
}
