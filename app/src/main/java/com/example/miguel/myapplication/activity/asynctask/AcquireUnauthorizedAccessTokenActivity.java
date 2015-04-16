package com.example.miguel.myapplication.activity.asynctask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.service.servercommunication.AcquireUnauthorizedAccessToken;
import com.example.miguel.myapplication.util.ConstantsStorage;
import com.example.miguel.myapplication.util.StringUtil;

import java.util.HashMap;

/**
 * Activity used in order to acquire unauthorized access Token/Secret. Here is a inner class who extends
 * AsyncTask, necessary for use internet services.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class AcquireUnauthorizedAccessTokenActivity extends ActionBarActivity {

    private String deviceToken;
    private String deviceSecret;
    private String oauthToken;
    private String oauthSecret;
    private int serverResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentIndormation();
        new AcquireUnauthorizedAccessTokenAT().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ConstantsStorage.WEB_BROWSER_CODE) {
            if (StringUtil.isNotNullOrEmpty(oauthToken) && StringUtil.isNotNullOrEmpty(oauthSecret)) {
                Intent intent = new Intent();
                intent.putExtra(ConstantsStorage.OAUTH_TOKEN, oauthToken);
                intent.putExtra(ConstantsStorage.OAUTH_SECRET, oauthSecret);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                setResult(ConstantsStorage.ACQUIRING_UNAUTHORIZED_ACCESS_ERROR);
                finish();
            }
        }
    }

    /**
     * This method launch the browser activity whit the url in order to log in and allow the application.
     */
    private void launchBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ConstantsStorage.HTTPS_LOGIN_URL
                + "/desiredaccessrights/request?oauth_token=" + oauthToken + "&theme=mobile"));
        onNewIntent(browserIntent);
        startActivityForResult(browserIntent, ConstantsStorage.WEB_BROWSER_CODE);
    }

    /**
     * Retrieve the information of the intent dispatched from LoginActivity.
     */
    private void getIntentIndormation() {
        deviceToken = getIntent().getStringExtra(ConstantsStorage.DEVICE_TOKEN);
        deviceSecret = getIntent().getStringExtra(ConstantsStorage.DEVICE_SECRET);
    }

    /*
     * In Android we can´t call networks services from the main thread so we need this AsyncTask class
     * in order to connect to the server.
     */

    /**
     * This inner class try to get the unauthorized access Token/Secret from the server.
     */
    private class AcquireUnauthorizedAccessTokenAT extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog progressDialog;

        /**
         * This method is executed first, before doInBackground. Basically call a progress dialog
         * with a bar to show while app try the device authentication.
         */
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(AcquireUnauthorizedAccessTokenActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.acquiring_unauthorized_token));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * This method will send device Token/Secret to the server in order to get app unauthorized
         * Token/Secret.
         *
         * @param params -  Void args.
         * @return - true if Token/Secret is retrieved, false if not.
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            AcquireUnauthorizedAccessToken acquireUnauthorizedAccessToken = new AcquireUnauthorizedAccessToken();
            HashMap<String, String> hashMap = acquireUnauthorizedAccessToken.getUnauthorizedAccessToken(deviceToken, deviceSecret);
            serverResponse = acquireUnauthorizedAccessToken.getServerResponse();
            if (hashMap != null) {
                oauthToken = hashMap.get(ConstantsStorage.OAUTH_TOKEN);
                oauthSecret = hashMap.get(ConstantsStorage.OAUTH_SECRET);
                return true;
            } else return false;
        }

        /**
         * This method is executed after doInBackground. If the result of doInBackground is true, then
         * the app launch the browser for log in.
         *
         * @param result - Result of doInBackground.
         */
        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            if (result) {
                if (serverResponse == ConstantsStorage.HTTP_RESPONSE_OK) {
                    if (StringUtil.isNotNullOrEmpty(oauthToken) && StringUtil.isNotNullOrEmpty(oauthSecret))
                        launchBrowser();
                }else {
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
