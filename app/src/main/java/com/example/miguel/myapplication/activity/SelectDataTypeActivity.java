package com.example.miguel.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.activity.asynctask.GetDataActivity;
import com.example.miguel.myapplication.activity.showinformation.CardiodockSettingsActivity;
import com.example.miguel.myapplication.activity.showinformation.DataListViewActivity;
import com.example.miguel.myapplication.activity.showinformation.GlucoseSettingsActivity;
import com.example.miguel.myapplication.activity.showinformation.OxymeterSettingsActivity;
import com.example.miguel.myapplication.activity.showinformation.TargetScalesSettingsActivity;
import com.example.miguel.myapplication.activity.showinformation.UserSettingsActivity;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.util.ConstantsStorage;

import java.util.ArrayList;

/**
 * This activity is launched after the user log in. Here the user can choose what type of data
 * want request to the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class SelectDataTypeActivity extends ActionBarActivity {

    // Device Token/Secret
    public static String deviceToken = "";
    public static String deviceSecret = "";

    // Access Token/Secret
    public static String accessToken = "";
    public static String accessSecret = "";

    private ArrayList<String> arrayModuleNames = new ArrayList<>();
    private Spinner spinnerModuleName;
    private TextView adviceTextView;
    private int typeReturned;
    private BaseModel baseModel;
    private static ArrayList<BaseModel> data = new ArrayList<>();
    private String moduleName;

    public static void setData (ArrayList<BaseModel> newData){
        data = newData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_data_type_activity);
        loadViews();
        getIntentInformation();
    }

    @Override
    public void onBackPressed() {
        logOut();
    }

    /**
     * Filter the result from GetDataActivity.
     *
     * @param requestCode - Code of the activity finished.
     * @param resultCode - Code of the response.
     * @param data - Intent with the data.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        typeReturned = data.getIntExtra(ConstantsStorage.TYPE_RETURNED, 1);
        moduleName = data.getStringExtra(ConstantsStorage.MODULE_NAME);
        if (resultCode == RESULT_OK) {
            if (typeReturned == ConstantsStorage.SETTINGS) {
                baseModel = (BaseModel) data.getSerializableExtra(ConstantsStorage.SETTINGS_STRING);
                filterClassNameSettings();
            } else if (typeReturned == ConstantsStorage.DATA) {
                Intent dataIntent = new Intent(this, DataListViewActivity.class);
                DataListViewActivity.setData(this.data);
                dataIntent.putExtra(ConstantsStorage.MODULE_NAME, moduleName);
                startActivity(dataIntent);
            }
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_BAD_REQUEST) {
            printBadRequestAdvice();
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_UNAUTHORIZED) {
            printUnauthorizedAdvice();
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_METHOD_NOT_ALLOWED) {
            printMethodNotAllowedAdvice();
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_INTERNAL_SERVER_ERROR) {

        } else if (resultCode == ConstantsStorage.EXCEPTION_ERROR) {

        }
    }

    /**
     * Here class name settings are filter in order to load corespondent activity.
     */
    private void filterClassNameSettings () {
        switch (moduleName) {
            case ConstantsStorage.USER_SETTINGS:
                Intent userSettingsIntent = new Intent(this, UserSettingsActivity.class);
                userSettingsIntent.putExtra(ConstantsStorage.USER_SETTINGS,baseModel);
                startActivity(userSettingsIntent);
                break;
            case ConstantsStorage.CARDIODOCK_SETTINGS:
                Intent cardiodockSettingsIntent = new Intent(this, CardiodockSettingsActivity.class);
                cardiodockSettingsIntent.putExtra(ConstantsStorage.CARDIODOCK_SETTINGS, baseModel);
                startActivity(cardiodockSettingsIntent);
                break;
            case ConstantsStorage.GLUCODOCK_GLUCOSE_SETTINGS:
                Intent glucoseSettingsIntent = new Intent(this, GlucoseSettingsActivity.class);
                glucoseSettingsIntent.putExtra(ConstantsStorage.GLUCODOCK_GLUCOSE_SETTINGS, baseModel);
                startActivity(glucoseSettingsIntent);
                break;
            case ConstantsStorage.OXYMETERS_SETTINGS:
                Intent oxymeterSettingsIntent = new Intent(this, OxymeterSettingsActivity.class);
                oxymeterSettingsIntent.putExtra(ConstantsStorage.OXYMETERS_SETTINGS, baseModel);
                startActivity(oxymeterSettingsIntent);
                break;
            case ConstantsStorage.TARGET_SCALES_SETTINGS:
                Intent targetScaleSettingsIntent = new Intent(this, TargetScalesSettingsActivity.class);
                targetScaleSettingsIntent.putExtra(ConstantsStorage.TARGET_SCALES_SETTINGS, baseModel);
                startActivity(targetScaleSettingsIntent);
                break;
        }
    }

    /**
     * Load all views of the activity.
     */
    private void loadViews() {
        spinnerModuleName = (Spinner) findViewById(R.id.spinnerModuleName);
        adviceTextView = (TextView) findViewById(R.id.adviceSelectDataTextView);
        loadModuleNamesList();
        loadSpinner();
    }

    /**
     * Load the ArrayList with the strings to show in the spinner.
     */
    private void loadModuleNamesList() {
        for (int i = 0; i < ConstantsStorage.DATA_MODULES.size(); i++) {
            arrayModuleNames.add(ConstantsStorage.DATA_MODULES.get(i));
        }
        for (int i = 0; i < ConstantsStorage.SETTINGS_MODULES.size(); i++) {
            arrayModuleNames.add(ConstantsStorage.SETTINGS_MODULES.get(i));
        }
    }

    /**
     * Load the spinner with the strings of the ArrayList.
     */
    private void loadSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayModuleNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModuleName.setAdapter(dataAdapter);
    }

    /**
     * Get the information of the Intent which come from LoginActivity.
     */
    private void getIntentInformation() {
        deviceToken = getIntent().getStringExtra(ConstantsStorage.DEVICE_TOKEN);
        deviceSecret = getIntent().getStringExtra(ConstantsStorage.DEVICE_SECRET);
        accessToken = getIntent().getStringExtra(ConstantsStorage.ACCESS_TOKEN);
        accessSecret = getIntent().getStringExtra(ConstantsStorage.ACCESS_SECRET);
    }

    /**
     * When the user click the 'Get data' button app starts an activity who connect the server
     * in order to get the required information.
     *
     * @param v - The view of the button.
     */
    public void clickedGetSomeData(View v) {
        Intent intent = new Intent(this, GetDataActivity.class);
        intent.putExtra(ConstantsStorage.DATA_MODEL, ConstantsStorage.MODULE_NAMES.indexOf(spinnerModuleName.getSelectedItem().toString()));
        intent.putExtra(ConstantsStorage.MODULE_NAME, spinnerModuleName.getSelectedItem().toString());
        startActivityForResult(intent, ConstantsStorage.GET_DATA_ACTIVITY_CODE);
    }

    /**
     * Method called when Logout button clicked.
     *
     * @param v - View of the button.
     */
    public void logoutClicked(View v) {
        logOut();
    }

    /**
     * This method clear the Token/Access from activity and finish that backing to LoginActivity.
     */
    private void logOut() {
        cleanTokens();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Clear device Token/Secret and access Token/Secret.
     */
    private void cleanTokens() {
          deviceToken = "";
          deviceSecret = "";
          accessToken = "";
          accessSecret = "";
    }

    /*
     * This methods are used to show different messages of error who can retrieve the server.
     */
    private void printBadRequestAdvice () {
        if (typeReturned == ConstantsStorage.SETTINGS)
            adviceTextView.setText(getString(R.string.bad_request_advice_first) + " " + getString(R.string.getting_settings)
            + getString(R.string.bad_request_advice_second));
        else if (typeReturned == ConstantsStorage.DATA)
            adviceTextView.setText(getString(R.string.bad_request_advice_first) + " " + getString(R.string.getting_data)
                    + getString(R.string.bad_request_advice_second));
    }

    private void printUnauthorizedAdvice () {
        if (typeReturned == ConstantsStorage.SETTINGS)
            adviceTextView.setText(getString(R.string.unauthorized_advice_first) + " " + getString(R.string.getting_settings)
            + getString(R.string.unauthorized_advice_second));
        else if (typeReturned == ConstantsStorage.DATA)
            adviceTextView.setText(getString(R.string.unauthorized_advice_first) + " " + getString(R.string.getting_data)
                    + getString(R.string.unauthorized_advice_second));
    }

    private void printMethodNotAllowedAdvice () {
        if (typeReturned == ConstantsStorage.SETTINGS)
            adviceTextView.setText(getString(R.string.method_not_allowed_advice_first) + " " + getString(R.string.getting_settings)
            + getString(R.string.method_not_alloed_advice_second));
        else if (typeReturned == ConstantsStorage.DATA)
            adviceTextView.setText(getString(R.string.method_not_allowed_advice_first) + " " + getString(R.string.getting_data)
                    + getString(R.string.method_not_alloed_advice_second));
    }
}
