package com.example.miguel.myapplication.activity.asynctask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.service.servercommunication.ExangeToken;
import com.example.miguel.myapplication.util.ConstantsStorage;
import com.example.miguel.myapplication.util.StringUtil;

import java.util.HashMap;

/**
 * Activity used in order to acquire device Token/Secret. Here is a inner class who extends
 * AsyncTask, necessary for use internet services.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */

public class ExchangeTokenActivity extends ActionBarActivity {

    private String deviceToken;
    private String deviceSecret;
    private String uniqueID;
    private int serverResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uniqueID = getUniqueID();
        new ExchangeTokenAT().execute();
    }

    public String getUniqueID(){
        return Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /*
     * In Android we can´t call networks services from the main thread so we need this AsyncTask class
     * in order to connect to the server.
     */

    /**
     * Class who do the exchange Token for the mobile application.
     */
    private class ExchangeTokenAT extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog progressDialog;

        /**
         * This method is executed first, before doInBackground. Basically call a progress dialog
         * with a bar to show while app try the device authentication.
         */
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ExchangeTokenActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.exchanging_token));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * In this method we get deviceToken and deviceSecret we need to get the access rights.
         *
         * @param params - Void args.
         * @return - true if the server response, false if not.
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            ExangeToken exangeToken = new ExangeToken();
            HashMap<String, String> hashMap = exangeToken.exangeToken(uniqueID);
            serverResponse = exangeToken.getServerResponse();
            if (hashMap != null) {
                deviceToken = hashMap.get(ConstantsStorage.DEVICE_TOKEN);
                deviceSecret = hashMap.get(ConstantsStorage.DEVICE_SECRET);
                return true;
            } else return false;
        }

        /**
         * This is what the app will do when doInBackground finish. #in this case create a intent to
         * put device Token/Secret and finish activity.
         *
         * @param result - doInBackground result.
         */
        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            if (result) {
                if (serverResponse == ConstantsStorage.HTTP_RESPONSE_OK) {
                    if (StringUtil.isNotNullOrEmpty(deviceToken) && StringUtil.isNotNullOrEmpty(deviceSecret)) {
                        Intent intent = new Intent();
                        intent.putExtra(ConstantsStorage.DEVICE_TOKEN, deviceToken);
                        intent.putExtra(ConstantsStorage.DEVICE_SECRET, deviceSecret);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    setResult(serverResponse);
                    finish();
                }
            } else {
                setResult(ConstantsStorage.EXCEPTION_ERROR);
                finish();
            }
        }
    }
}
