package com.example.miguel.myapplication.activity.asynctask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.activity.SelectDataTypeActivity;
import com.example.miguel.myapplication.model.BaseModel;
import com.example.miguel.myapplication.service.servercommunication.RetrieveData;
import com.example.miguel.myapplication.service.servercommunication.RetrieveSettings;
import com.example.miguel.myapplication.util.ConstantsStorage;

import java.util.ArrayList;

/**
 * Class used to request information to the server. This class need moduleId and moduleName in order
 * to request the information to the server, the activity collect this information from the Intent,
 * so you need to send the intent whit the information.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class GetDataActivity extends ActionBarActivity {

    private ArrayList<BaseModel> data;

    private String moduleName;
    private BaseModel settings;
    private int typeReturned = 0;
    private int serverResponse;
    private int moduleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
    }

    /**
     * This method get the information of the Intent who send the MainActivity.
     * These parameters are using to get the data from the server.
     */
    private void getIntentData() {
        moduleID = getIntent().getIntExtra(ConstantsStorage.DATA_MODEL, 1);
        moduleName = getIntent().getStringExtra(ConstantsStorage.MODULE_NAME);
        new GetDataModel().execute();
    }

    /*
     * In Android we can´t call networks services from the main thread so we need this AsyncTask class
     * in order to connect to the server.
     */

    /**
     * This class will connect with the server in order to retrieve the required information.
     */
    private class GetDataModel extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog progressDialog;

        /**
         * This method is executed first, before doInBackground.
         * Basically call a progress dialog with a bar to show while app try to get data from the
         * server.
         */
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(GetDataActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.getting_data));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * This method is were we do all we need for call internet services. First check if user want
         * settings or data from an device, then call the appropriate class.
         *
         * @param params - Void args.
         * @return - true if the server response, false if not.
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            if (ConstantsStorage.SETTINGS_MODULES.contains(moduleName)) {
                RetrieveSettings retrieveSettings = new RetrieveSettings();
                settings = retrieveSettings.getSettings(moduleID, moduleName);
                serverResponse = retrieveSettings.getServerResponse();
                typeReturned = ConstantsStorage.SETTINGS;
                if (settings != null) return true;
                else return false;
            } else {
                RetrieveData retrieveData = new RetrieveData();
                data = retrieveData.getData(moduleID, moduleName);
                serverResponse = retrieveData.getServerResponse();
                typeReturned = ConstantsStorage.DATA;
                if (data != null) return true;
                else return false;
            }
        }

        /**
         * This method is called after doInBackground. When all the data is returned from the server
         * and saved into ArrayList, the activity finish. In case that ArrayList is null the result
         * is an error. In case that the ArrayList is empty the result is data not found.
         *
         * @param result - Result from doInBackground
         */
        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            Intent intent = new Intent();
            intent.putExtra(ConstantsStorage.TYPE_RETURNED, typeReturned);
            if (result) {
                if (typeReturned == ConstantsStorage.SETTINGS) {
                    intent.putExtra(ConstantsStorage.SETTINGS_STRING, settings);
                    intent.putExtra(ConstantsStorage.MODULE_NAME, moduleName);
                    setResult(RESULT_OK, intent);
                    finish();
                } else if (typeReturned == ConstantsStorage.DATA) {
                    SelectDataTypeActivity.setData(data);
                    intent.putExtra(ConstantsStorage.MODULE_NAME, moduleName);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } else {
                setResult(ConstantsStorage.EXCEPTION_ERROR, intent);
                finish();
            }
        }
    }
}
