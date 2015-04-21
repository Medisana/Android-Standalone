package com.example.miguel.myapplication.service.servercommunication;

import android.os.Build;

import com.example.miguel.myapplication.service.AuthorizationBuilder;
import com.example.miguel.myapplication.service.HeaderPrinter;
import com.example.miguel.myapplication.util.ConstantsStorage;
import com.example.miguel.myapplication.util.ResponseChecker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Class used for retrieve device Token/Secret.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class ExangeToken {

    private HttpClient httpClient;
    private HttpPost httppost;
    private HttpResponse response;
    private BufferedReader bufferReader;
    private String str;
    private String deviceToken;
    private String deviceSecret;
    private int serverResponse = 2;

    /**
     * This method connect to the server in order to create a HashMap whit the device Token/Secret.
     * Note this method cannot be called from main thread, so need to be called from AsyncTask class.
     *
     * @param uniqueID
     * @return - HasMap with device Token/Secret.
     */
    public HashMap<String, String> exangeToken(String uniqueID) {
        httpClient = new DefaultHttpClient();
        final String uriDeviceRequest;
        uriDeviceRequest = ConstantsStorage.HTTPS_AUTH_URL + "/devices";
        httppost = new HttpPost(uriDeviceRequest);
        final String authorization;
        try {
            authorization = AuthorizationBuilder
                    .createUnauthorizedAccessRequestAuthorizationHeader(
                            uriDeviceRequest, ConstantsStorage.TEST_APPLICATION_TOKEN,
                            ConstantsStorage.TEST_APPLICATION_SECRET);
            httppost.setHeader(ConstantsStorage.AUTHORIZATION_STRING, authorization);
            httppost.setHeader("device_id", uniqueID);
            httppost.setHeader("device_name", Build.MODEL);
            httppost.setHeader("User-Agent", ConstantsStorage.AGENT_NAME);
            HeaderPrinter.printPost(httppost);
            response = httpClient.execute(httppost);
            serverResponse = ResponseChecker.checkServerResponse(response);
            if (serverResponse == ConstantsStorage.HTTP_RESPONSE_OK) {
                bufferReader = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));

                final StringBuffer strBuffer = new StringBuffer();
                while ((str = bufferReader.readLine()) != null) {
                    strBuffer.append(str);
                }
                str = strBuffer.toString();
                if (str.split("&").length < 2) {
                    try {
                        throw new Exception(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                deviceToken = str.split("&")[0].split("=")[1];
                deviceSecret = str.split("&")[1].split("=")[1];
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
        return generateHashMap();
    }

    public int getServerResponse() {
        return serverResponse;
    }

    /**
     * This method create the HashMap with the device Token/Secret.
     *
     * @return - HashMap with device Token/Secret.
     */
    private HashMap<String, String> generateHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(ConstantsStorage.DEVICE_TOKEN, deviceToken);
        hashMap.put(ConstantsStorage.DEVICE_SECRET, deviceSecret);
        return hashMap;
    }
}
