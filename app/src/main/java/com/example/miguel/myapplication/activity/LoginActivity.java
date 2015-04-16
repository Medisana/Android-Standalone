package com.example.miguel.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguel.myapplication.R;
import com.example.miguel.myapplication.activity.asynctask.AcquireUnauthorizedAccessTokenActivity;
import com.example.miguel.myapplication.activity.asynctask.CheckVerifiedTokenActivity;
import com.example.miguel.myapplication.activity.asynctask.ExchangeTokenActivity;
import com.example.miguel.myapplication.service.SimpleCrypto;
import com.example.miguel.myapplication.util.ConstantsStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This is the first activity, used to log in and get the access rights in order to work with the server.
 * When log in is done and the app  have rights to access the data this activity start SelectDataTypeActivity.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class LoginActivity extends ActionBarActivity {

    private static String verifierToken;

    private TextView adviceTextView;

    private String fileName = "credentials";

    private String oauthToken = "";
    private String oauthSecret = "";
    private String accessToken = "";
    private String accessSecret = "";
    private String deviceToken = "";
    private String deviceSecret = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adviceTextView = (TextView) findViewById(R.id.loginActivityAdviceTextView);
    }

    /**
     * When another activity, launched by this one, finish with result this method will be called.
     * Basically filter the results, where it comes from and what is the result. In case of error
     * show a Toast with the error.
     *
     * @param requestCode - Code of the activity finished.
     * @param resultCode  - The result code from the activity finished.
     * @param data        - Intent with the data from the activity finished.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ConstantsStorage.EXCHANGE_TOKEN_ACTIVITY_CODE) {
                deviceToken = data.getStringExtra(ConstantsStorage.DEVICE_TOKEN);
                deviceSecret = data.getStringExtra(ConstantsStorage.DEVICE_SECRET);
                launchAcquireUnauthorizedAccessTokenActivity();
            } else if (requestCode == ConstantsStorage.ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE) {
                oauthToken = data.getStringExtra(ConstantsStorage.OAUTH_TOKEN);
                oauthSecret = data.getStringExtra(ConstantsStorage.OAUTH_SECRET);
                launchCheckVerifiedTokenActivity();
            } else if (requestCode == ConstantsStorage.CHECK_VERIFIED_TOKEN_CODE) {
                accessToken = data.getStringExtra(ConstantsStorage.ACCESS_TOKEN);
                accessSecret = data.getStringExtra(ConstantsStorage.ACCESS_SECRET);
                saveLoginRights();
                launchSelectDataTypeActivity();
            } else if (requestCode == ConstantsStorage.SELECT_DATA_TYPE_ACTIVITY_CODE) {
                oauthToken = "";
                oauthSecret = "";
                accessToken = "";
                accessSecret = "";
                deviceToken = "";
                deviceSecret = "";
                adviceTextView.setText("");
                Toast.makeText(this, R.string.logged_out, Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_BAD_REQUEST) {
            printBadRequestAdvice(requestCode);
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_UNAUTHORIZED) {
            printUnauthorizedAdvice(requestCode);
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_METHOD_NOT_ALLOWED) {
            printMethodNotAllowedAdvice(requestCode);
        } else if (resultCode == ConstantsStorage.HTTP_RESPONSE_INTERNAL_SERVER_ERROR) {
            adviceTextView.setText(getString(R.string.internal_server_error));
        } else if (resultCode == ConstantsStorage.EXCEPTION_ERROR) {
            printExceptionErrorAdvice(requestCode);
        }
    }

    /**
     * Launch AcquireUnauthorizedAccessTokenActivity with the new device Token/Secret pair.
     */
    private void launchAcquireUnauthorizedAccessTokenActivity() {
        Intent intent = new Intent(this, AcquireUnauthorizedAccessTokenActivity.class);
        intent.putExtra(ConstantsStorage.DEVICE_TOKEN, deviceToken);
        intent.putExtra(ConstantsStorage.DEVICE_SECRET, deviceSecret);
        startActivityForResult(intent, ConstantsStorage.ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE);
    }

    /**
     * Launch CheckVerifiedTokenActivity with the new unauthorized Token/Secret pair in order to get
     * access Token/Secret pair who allow the application work with server.
     */
    private void launchCheckVerifiedTokenActivity() {
        Intent intent = new Intent(this, CheckVerifiedTokenActivity.class);
        intent.putExtra(ConstantsStorage.DEVICE_TOKEN, deviceToken);
        intent.putExtra(ConstantsStorage.DEVICE_SECRET, deviceSecret);
        intent.putExtra(ConstantsStorage.OAUTH_TOKEN, oauthToken);
        intent.putExtra(ConstantsStorage.OAUTH_SECRET, oauthSecret);
        intent.putExtra(ConstantsStorage.VERIFIER_TOKEN, verifierToken);
        startActivityForResult(intent, ConstantsStorage.CHECK_VERIFIED_TOKEN_CODE);
    }

    /**
     * Launch SelectDataTypeActivity with the new access Token/Secret pair.
     */
    private void launchSelectDataTypeActivity() {
        oauthToken = "";
        oauthSecret = "";
        verifierToken = "";
        Intent intent = new Intent(this, SelectDataTypeActivity.class);
        intent.putExtra(ConstantsStorage.DEVICE_TOKEN, deviceToken);
        intent.putExtra(ConstantsStorage.DEVICE_SECRET, deviceSecret);
        intent.putExtra(ConstantsStorage.ACCESS_TOKEN, accessToken);
        intent.putExtra(ConstantsStorage.ACCESS_SECRET, accessSecret);
        startActivityForResult(intent, ConstantsStorage.SELECT_DATA_TYPE_ACTIVITY_CODE);
    }

    /**
     * Method that generate a file and store encrypted device Token/Secret and access Token/Secret.
     */
    private void saveLoginRights () {
        try {
            String deviceTokenEncrypted = SimpleCrypto.encrypt(ConstantsStorage.DEVICE_TOKEN, deviceToken);
            String deviceSecretEncrypted = SimpleCrypto.encrypt(ConstantsStorage.DEVICE_SECRET, deviceSecret);
            String accessTokenEncrypted = SimpleCrypto.encrypt(ConstantsStorage.ACCESS_TOKEN, accessToken);
            String accessSecretEncrypted = SimpleCrypto.encrypt(ConstantsStorage.ACCESS_SECRET, accessSecret);
            deleteFile(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFilesDir() + "/" + fileName));
            try {
                bufferedWriter.write(deviceTokenEncrypted);
                bufferedWriter.newLine();
                bufferedWriter.write(deviceSecretEncrypted);
                bufferedWriter.newLine();
                bufferedWriter.write(accessTokenEncrypted);
                bufferedWriter.newLine();
                bufferedWriter.write(accessSecretEncrypted);
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method called when Login button is pressed. Check if device is online, if is online start the
     * authentication process.
     *
     * @param v - View method is called from.
     */
    public void launchLogin(View v) {
        if (isOnline()) {
            Intent intent = new Intent(this, ExchangeTokenActivity.class);
            startActivityForResult(intent, ConstantsStorage.EXCHANGE_TOKEN_ACTIVITY_CODE);
        } else
            Toast.makeText(this, R.string.dont_connect_internet, Toast.LENGTH_LONG).show();
    }

    /**
     * When Saved Credentials button is clicked, read the file whit credentials and launch Select Data
     * Activity. If file not exist show a message.
     *
     * @param v - View method is called from.
     */
    public void savedCredentialsClicked(View v) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFilesDir() + "/" + fileName));
            StringBuilder text = new StringBuilder();
            String line;
            int count = 1;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                switch (count) {
                    case 1:
                        deviceToken = SimpleCrypto.decrypt(ConstantsStorage.DEVICE_TOKEN, line);
                        break;
                    case 2:
                        deviceSecret = SimpleCrypto.decrypt(ConstantsStorage.DEVICE_SECRET, line);
                        break;
                    case 3:
                        accessToken = SimpleCrypto.decrypt(ConstantsStorage.ACCESS_TOKEN, line);
                        break;
                    case 4:
                        accessSecret = SimpleCrypto.decrypt(ConstantsStorage.ACCESS_SECRET, line);
                        break;
                }
                count = count + 1;
            }
            launchSelectDataTypeActivity();
        } catch (Exception e) {
            e.printStackTrace();
            adviceTextView.setText(getString(R.string.no_credentials_saved));
        }
    }

    /**
     * This method check if device have internet access.
     *
     * @return - True if online, false if not.
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Method for set the verifier Token.
     *
     * @param uriTokens - Verifier token.
     */
    public static void setVerifierToken(String uriTokens) {
        verifierToken = uriTokens;
    }

    /**
     * Print a message of bad request.
     *
     * @param requestCode - Code of the activity where come from.
     */
    private void printBadRequestAdvice(int requestCode) {
        if (requestCode == ConstantsStorage.EXCHANGE_TOKEN_ACTIVITY_CODE)
            adviceTextView.setText(getString(R.string.bad_request_advice_first) + " " + getString(R.string.exchanging_token)
                    + getString(R.string.bad_request_advice_second));
        else if (requestCode == ConstantsStorage.ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.bad_request_advice_first) + " " + getString(R.string.acquiring_unauthorized_token)
                    + getString(R.string.bad_request_advice_second));
        else if (requestCode == ConstantsStorage.CHECK_VERIFIED_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.bad_request_advice_first) + " " + getString(R.string.checking_verified_token)
                    + getString(R.string.bad_request_advice_second));
    }

    /**
     * Print a message of unauthorized.
     *
     * @param requestCode - Code of the activity where come from.
     */
    private void printUnauthorizedAdvice(int requestCode) {
        if (requestCode == ConstantsStorage.EXCHANGE_TOKEN_ACTIVITY_CODE)
            adviceTextView.setText(getString(R.string.unauthorized_advice_first) + " " + getString(R.string.exchanging_token)
                    + getString(R.string.unauthorized_advice_second));
        else if (requestCode == ConstantsStorage.ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.unauthorized_advice_first) + " " + getString(R.string.acquiring_unauthorized_token)
                    + getString(R.string.unauthorized_advice_second));
        else if (requestCode == ConstantsStorage.CHECK_VERIFIED_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.unauthorized_advice_first) + " " + getString(R.string.checking_verified_token)
                    + getString(R.string.unauthorized_advice_second));
    }

    /**
     * Print a message of method not allowed.
     *
     * @param requestCode - Code of the activity where come from.
     */
    private void printMethodNotAllowedAdvice(int requestCode) {
        if (requestCode == ConstantsStorage.EXCHANGE_TOKEN_ACTIVITY_CODE)
            adviceTextView.setText(getString(R.string.method_not_allowed_advice_first) + " " + getString(R.string.exchanging_token)
                    + getString(R.string.method_not_alloed_advice_second));
        else if (requestCode == ConstantsStorage.ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.method_not_allowed_advice_first) + " " + getString(R.string.acquiring_unauthorized_token)
                    + getString(R.string.method_not_alloed_advice_second));
        else if (requestCode == ConstantsStorage.CHECK_VERIFIED_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.method_not_allowed_advice_first) + " " + getString(R.string.checking_verified_token)
                    + getString(R.string.method_not_alloed_advice_second));
    }

    /**
     * If any exception is throw, show a message.
     *
     * @param requestCode - Code of the activity where come from.
     */
    private void printExceptionErrorAdvice(int requestCode) {
        if (requestCode == ConstantsStorage.EXCHANGE_TOKEN_ACTIVITY_CODE)
            adviceTextView.setText(getString(R.string.exception_error_first) + " " + getString(R.string.exchanging_token)
            + getString(R.string.exception_error_second));
        else if (requestCode == ConstantsStorage.ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.exception_error_first) + " " + getString(R.string.acquiring_unauthorized_token)
                    + getString(R.string.exception_error_second));
        else if (requestCode == ConstantsStorage.CHECK_VERIFIED_TOKEN_CODE)
            adviceTextView.setText(getString(R.string.exception_error_first) + " " + getString(R.string.checking_verified_token)
                    + getString(R.string.exception_error_second));
    }
}
