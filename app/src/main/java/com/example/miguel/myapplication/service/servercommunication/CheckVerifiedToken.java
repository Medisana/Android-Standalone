package com.example.miguel.myapplication.service.servercommunication;

import android.util.Log;

import com.example.miguel.myapplication.service.AuthorizationBuilder;
import com.example.miguel.myapplication.service.HeaderPrinter;
import com.example.miguel.myapplication.util.ConstantsStorage;
import com.example.miguel.myapplication.util.ResponseChecker;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Class used for check the verified token and obtain access Token/Secret.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CheckVerifiedToken {

    private String uriOauthRequest;
    private DefaultHttpClient httpClient;
    private String authorization;
    private HttpPost httppost;
    private HttpResponse response;
    private String str;
    private BufferedReader bufferReader;
    private String accessToken;
    private String accessSecret;
    private int serverResponse = 2;

    /**
     * Method that return an HashMap whit access Token/Secret pair.
     * Note that method cannot be called from main thread, so need to be called from AsyncTask class.
     *
     * @param deviceToken - Device Token.
     * @param deviceSecret - Device Secret.
     * @param oauthToken - Unauthorized Token.
     * @param oauthSecret - Unauthorized Secret.
     * @param verifierToken - Verifier Token.
     * @return - HashMap with access Token/Secret.
     */
    public HashMap<String, String> getAccessTokenSecret (String deviceToken, String deviceSecret,
                                                                String oauthToken, String oauthSecret, String verifierToken) {
        uriOauthRequest = ConstantsStorage.HTTPS_AUTH_URL + "/accesses/verify";
        int x = verifierToken.lastIndexOf("oauth_verifier=");
        verifierToken = verifierToken.substring(x + 15);
        httpClient = new DefaultHttpClient();
        try {
            authorization = AuthorizationBuilder
                    .createAccessRequestAuthorizationHeader(deviceToken,
                            deviceSecret, oauthToken, oauthSecret,
                            verifierToken);
            httppost = new HttpPost(uriOauthRequest);
            httppost.setHeader("Content-Type",
                    "application/x-www-form-urlencoded");
            httppost.setHeader(ConstantsStorage.AUTHORIZATION_STRING, authorization);
            HeaderPrinter.printPost(httppost);
            response = httpClient.execute(httppost);
            serverResponse = ResponseChecker.checkServerResponse(response);
            str = null;
            bufferReader = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            try {
                while ((str = bufferReader.readLine()) != null) {
                    accessToken = str.split("&")[0].split("=")[1];
                    accessSecret = str.split("&")[1].split("=")[1];
                    break;
                }
            } catch (Exception e) {
                Log.e("Exception", str);
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpClient.getConnectionManager().shutdown();
        }
        return getHashMap();
    }

    public int getServerResponse () {
        return serverResponse;
    }

    /**
     * Create a HashMap with access Token/Secret pair.
     *
     * @return - HashMap with Token/Secret pair.
     */
    private HashMap<String, String> getHashMap () {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(ConstantsStorage.ACCESS_TOKEN, accessToken);
        hashMap.put(ConstantsStorage.ACCESS_SECRET, accessSecret);
        return hashMap;
    }
}
