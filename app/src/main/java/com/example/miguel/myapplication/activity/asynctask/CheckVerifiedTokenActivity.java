package com.example.miguel.myapplication.activity.asynctask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.service.servercommunication.CheckVerifiedToken;
import com.example.miguel.myapplication.util.ConstantsStorage;
import com.example.miguel.myapplication.util.StringUtil;

import java.util.HashMap;

/**
 * Activity used in order to acquire verified Token/Secret. Here is a inner class who extends
 * AsyncTask, necessary for use internet services.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CheckVerifiedTokenActivity extends ActionBarActivity {

    private String verifierToken;
    private String deviceToken;
    private String deviceSecret;
    private String oauthToken;
    private String oauthSecret;
    private String accessToken;
    private String accessSecret;
    private int serverResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentIndormation();
        new CheckVerifiedTokenAT().execute();
    }

    private void getIntentIndormation() {
        deviceToken = getIntent().getStringExtra(ConstantsStorage.DEVICE_TOKEN);
        deviceSecret = getIntent().getStringExtra(ConstantsStorage.DEVICE_SECRET);
        oauthToken = getIntent().getStringExtra(ConstantsStorage.OAUTH_TOKEN);
        oauthSecret = getIntent().getStringExtra(ConstantsStorage.OAUTH_SECRET);
        verifierToken = getIntent().getStringExtra(ConstantsStorage.VERIFIER_TOKEN);
    }

    /*
     * In Android we can´t call networks services from the main thread so we need this AsyncTask class
     * in order to connect to the server.
     */

    /**
     * This inner class verified the unauthorized Token/Secret and the device Token/Secret for get the
     * access Token/Secret who allows the application to work whit the server.
     */
    private class CheckVerifiedTokenAT extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog progressDialog;

        /**
         * This method is executed first, before doInBackground. Basically call a progress dialog
         * with a bar to show while app try the device authentication.
         */
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(CheckVerifiedTokenActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.checking_verified_token));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * This method is were we do all we need for call internet services.
         *
         * @param params Void args.
         * @return true if the server response, false if not.
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            CheckVerifiedToken checkVerifiedToken = new CheckVerifiedToken();
            HashMap<String, String> hashMap = checkVerifiedToken.getAccessTokenSecret(
                    deviceToken, deviceSecret, oauthToken, oauthSecret, verifierToken
            );
            serverResponse = checkVerifiedToken.getServerResponse();
            if (hashMap != null) {
                accessToken = hashMap.get(ConstantsStorage.ACCESS_TOKEN);
                accessSecret = hashMap.get(ConstantsStorage.ACCESS_SECRET);
                return true;
            } else return false;
        }

        /**
         * Method executed after doInBackground, at this point we must to have deviceToken, device secret,
         * accessToken and accessSecret. If this variables are not null and not empty, we launch
         * the MainActivity intent with this information.
         *
         * @param result Result from doInBackground.
         */
        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            if (result) {
                if (serverResponse == ConstantsStorage.HTTP_RESPONSE_OK) {
                    if (StringUtil.isNotNullOrEmpty(accessToken) && StringUtil.isNotNullOrEmpty(accessToken)) {
                        Intent intent = new Intent();
                        intent.putExtra(ConstantsStorage.ACCESS_TOKEN, accessToken);
                        intent.putExtra(ConstantsStorage.ACCESS_SECRET, accessSecret);
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
