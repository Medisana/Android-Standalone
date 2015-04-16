package com.example.miguel.myapplication.activity.showinformation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.dockclass.UserSettings;
import com.example.miguel.myapplication.util.CalendarString;
import com.example.miguel.myapplication.util.ConstantsStorage;

/**
 * This activity show the settings of User returned from the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class UserSettingsActivity extends ActionBarActivity {

    private UserSettings userSettings = new UserSettings();

    private TextView measurementDate;
    private TextView birthday;
    private TextView sex;
    private TextView personalMessages;
    private TextView moduleSerialId;
    private TextView updatedDate;
    private TextView goalSteps;
    private TextView stride;
    private TextView version;
    private TextView id;
    private TextView height;
    private TextView active;
    private TextView lengthUnit;
    private TextView bodyWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user_settings);
        getInformation();
        loadViews();
        if (getInformation()) printInformation();
    }

    private boolean getInformation() {
        try {
            userSettings = (UserSettings) getIntent().getSerializableExtra(ConstantsStorage.USER_SETTINGS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void loadViews() {
        measurementDate = (TextView) findViewById(R.id.measurementDateUserSettings);
        birthday = (TextView) findViewById(R.id.birthdayUserSettings);
        sex = (TextView) findViewById(R.id.sexUserSettings);
        personalMessages = (TextView) findViewById(R.id.personalMessagesUserSettings);
        moduleSerialId = (TextView) findViewById(R.id.moduleSerialIdUserSettings);
        updatedDate = (TextView) findViewById(R.id.updatedDateUserSettings);
        goalSteps = (TextView) findViewById(R.id.goalStepsUserSettings);
        stride = (TextView) findViewById(R.id.strideUserSettings);
        version = (TextView) findViewById(R.id.versionUserSettings);
        id = (TextView) findViewById(R.id.idUserSettings);
        height = (TextView) findViewById(R.id.heighUserSettings);
        active = (TextView) findViewById(R.id.activeUserSettings);
        lengthUnit = (TextView) findViewById(R.id.lengthUnitUserSettings);
        bodyWeight = (TextView) findViewById(R.id.bodyWeigthUserSettings);
    }

    private void printInformation () {
        measurementDate.setText(" " + CalendarString.calendarToString(userSettings.getMeasurementDate()));
        birthday.setText(" " + CalendarString.calendarToString(userSettings.getBirthday()));
        sex.setText(" " + userSettings.getSex());
        personalMessages.setText(" " + userSettings.getPersonalMessages());
        moduleSerialId.setText(" " + userSettings.getModuleSerialId());
        updatedDate.setText(" " + CalendarString.calendarToString(userSettings.getUpdatedDate()));
        goalSteps.setText(" " + userSettings.getGoalSteps().toString());
        stride.setText(" " + userSettings.getStride().toString());
        version.setText(" " + userSettings.getVersion().toString());
        id.setText(" " + userSettings.getId());
        height.setText(" " + userSettings.getHeight().toString());
        active.setText(" " + userSettings.getActive().toString());
        lengthUnit.setText(" " + userSettings.getLengthUnit());
        bodyWeight.setText(" " + userSettings.getBodyWeight().toString());
    }
}
