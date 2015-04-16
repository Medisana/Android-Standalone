package com.example.miguel.myapplication.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.miguel.myapplication.activity.LoginActivity;

/**
 * Helper activity who handle the callback from the browser after user sign up and allowed. Called when
 * browser activity finnish.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CallbackHandler extends Activity {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleUrl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleUrl();
    }

    /**
     * This method handle the url retrieved by the server after the user allow, clean the url and
     * set verifier Token into LoginActivity.
     */
    private void handleUrl() {
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(ConstantsStorage.CALL_BACK_URI)) {
            String uriTokens = uri.toString().replace(ConstantsStorage.CALL_BACK_URI, "");
            LoginActivity.setVerifierToken(uriTokens);
            finish();
        }
    }
}
