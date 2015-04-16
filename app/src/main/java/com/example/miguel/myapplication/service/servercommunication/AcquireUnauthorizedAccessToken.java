package com.example.miguel.myapplication.service.servercommunication;

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
 * Class who connect to the server in order to get unauthorized access Token/Secret.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class AcquireUnauthorizedAccessToken {

    private DefaultHttpClient httpClient;
    private String uriOauthRequest;
    private String authorization;
    private String deviceToken;
    private String deviceSecret;
    private HttpPost httppost;
    private HttpResponse response;
    private BufferedReader bufferReader;
    private String str;
    private String oauthToken;
    private String oauthSecret;
    private int serverResponse = 2;

    /**
     * Method for connect to server and request the unauthorized access Token/Secret.
     * Note this method cannot be called from main thread, so need to be called from AsyncTask class.
     *
     * @param newDeviceToken  - Device Token needed to obtain the unauthorized access Token/Secret.
     * @param newDeviceSecret - Device Secret needed to obtain the unauthorized access Token/Secret.
     * @return - HashMap with unauthorized access Token/Secret.
     */
    public HashMap<String, String> getUnauthorizedAccessToken(String newDeviceToken, String newDeviceSecret) {
        deviceToken = newDeviceToken;
        deviceSecret = newDeviceSecret;
        httpClient = new DefaultHttpClient();
        uriOauthRequest = ConstantsStorage.HTTPS_AUTH_URL
                + "/unauthorizedaccesses";
        try {
            authorization = AuthorizationBuilder
                    .createUnauthorizedAccessRequestAuthorizationHeader(
                            uriOauthRequest, deviceToken, deviceSecret);
            httppost = new HttpPost(uriOauthRequest);
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setHeader(ConstantsStorage.AUTHORIZATION_STRING, authorization);
            HeaderPrinter.printPost(httppost);
            response = httpClient.execute(httppost);
            serverResponse = ResponseChecker.checkServerResponse(response);
            bufferReader = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            final StringBuffer strBuffer = new StringBuffer();
            while ((str = bufferReader.readLine()) != null) {
                strBuffer.append(str);
            }
            str = strBuffer.toString();
            if (str.split("&").length < 2) {
                throw new Exception(str);
            }
            oauthToken = str.split("&")[0].split("=")[1];
            oauthSecret = str.split("&")[1].split("=")[1];
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

    public int getServerResponse() {
        return serverResponse;
    }

    /**
     * Create the HashMap with the Token/Secret.
     *
     * @return - HashMap with Token/Secret.
     */
    private HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(ConstantsStorage.OAUTH_TOKEN, oauthToken);
        hashMap.put(ConstantsStorage.OAUTH_SECRET, oauthSecret);
        return hashMap;
    }
}
