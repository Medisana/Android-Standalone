package com.example.miguel.myapplication.service.servercommunication;

import com.example.miguel.myapplication.activity.SelectDataTypeActivity;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.CardiodockSettings;
import com.example.miguel.myapplication.dockclass.GlucoseSettings;
import com.example.miguel.myapplication.dockclass.OxymeterSettings;
import com.example.miguel.myapplication.dockclass.TargetScaleSettings;
import com.example.miguel.myapplication.dockclass.UserSettings;
import com.example.miguel.myapplication.service.AuthorizationBuilder;
import com.example.miguel.myapplication.service.HeaderPrinter;
import com.example.miguel.myapplication.util.ConstantsStorage;
import com.example.miguel.myapplication.util.CustomGson;
import com.example.miguel.myapplication.util.ResponseChecker;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class used for retrieve the settings of the account from the server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class RetrieveSettings {

    private BaseModel settings;

    private static final int start = -1;
    private static final int max = 1;
    private static final String dateSince = "0";

    private DefaultHttpClient httpClient;
    private String authorization;
    private HttpGet httpget;
    private HttpResponse response;
    private JSONArray jSONArray;
    private String moduleName;

    private int serverResponse = 2;

    /**
     * Method who generate an ArrayList with the required settings. App only need the last one configuration,
     * so send a synchronized header in order to receive just one JSON with the information.
     * Note that method cannot be called from main thread, so need to be called from AsyncTask class.
     *
     * @param moduleID - Module ID which we want settings.
     * @param newModuleName - Module name which we want settings.
     * @return - ArrayList with the settings.
     */
    public BaseModel getSettings (int moduleID, String newModuleName) {
        settings = new BaseModel();
        moduleName = newModuleName;
        String url = AuthorizationBuilder.createSyncUrl(moduleID, start , max, dateSince);
        httpClient = new DefaultHttpClient();
        try {
            authorization = AuthorizationBuilder
                    .createLoadDataRequestAuthorizationHeader(AuthorizationBuilder
                                    .createSyncUrl(moduleID), start, max,
                            dateSince, SelectDataTypeActivity.deviceToken, SelectDataTypeActivity.deviceSecret,
                            SelectDataTypeActivity.accessToken, SelectDataTypeActivity.accessSecret);
            httpget = new HttpGet(url);
            httpget.setHeader(ConstantsStorage.AUTHORIZATION_STRING, authorization);
            HeaderPrinter.printPost(httpget);
            response = httpClient.execute(httpget);
            serverResponse = ResponseChecker.checkServerResponse(response);
            if (serverResponse == ConstantsStorage.HTTP_RESPONSE_OK) {
                jSONArray = parseJSONString(response.getEntity().getContent());
                if (jSONArray != null) {
                    if (!jSONArray.toString().equals("[]")) {
                        addDataToArray();
                    }
                }
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ConstantsStorage.HTTP_RESPONSE_INTERNAL_SERVER_ERROR;
            return null;
        }
        return settings;
    }

    public int getServerResponse () {
        return serverResponse;
    }

    /**
     * This method transform the HTTP data returned by the server into a JSONArray to work with it.
     *
     * @param inputStream - InputStream with the HTTP information returned by the server.
     * @return - JSONArray whit the information.
     */
    private static JSONArray parseJSONString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                builder.append(line).append("\n");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONTokener tokener = new JSONTokener(builder.toString());
        try {
            return new JSONArray(tokener);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method filter the module user want to get the information in order to call the different
     * methods for load the ArrayList.
     */
    private void addDataToArray() {
        switch (moduleName) {
            case ConstantsStorage.CARDIODOCK_SETTINGS:
                addCardiodockSettingsDataToArray();
                break;
            case ConstantsStorage.GLUCODOCK_GLUCOSE_SETTINGS:
                addGlucoseSettingsDataToArray();
                break;
            case ConstantsStorage.TARGET_SCALES_SETTINGS:
                addTargetScalesSettingsDataToArray();
                break;
            case ConstantsStorage.USER_SETTINGS:
                addUserSettingsDataToArray();
                break;
            case ConstantsStorage.OXYMETERS_SETTINGS:
                addOxymeterSettingsDataToArray();
                break;
        }
    }

    /**
     * This method load the User Settings into the ArrayList.
     */
    private void addUserSettingsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            UserSettings auxiliary = new UserSettings();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), UserSettings.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            settings = auxiliary;
        }
    }

    /**
     * This method load the Target Scales Settings into the ArrayList.
     */
    private void addTargetScalesSettingsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            TargetScaleSettings auxiliary = new TargetScaleSettings();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), TargetScaleSettings.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            settings = auxiliary;
        }
    }

    /**
     * This method load the Cardiodock Settings into the ArrayList.
     */
    private void addCardiodockSettingsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            CardiodockSettings auxiliary = new CardiodockSettings();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), CardiodockSettings.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            settings = auxiliary;
        }
    }

    /**
     * This method load the Glucodock Settings into the ArrayList.
     */
    private void addGlucoseSettingsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            GlucoseSettings auxiliary = new GlucoseSettings();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), GlucoseSettings.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            settings = auxiliary;
        }
    }

    /**
     * This method load the Oxymeter Settings into the ArrayList.
     */
    private void addOxymeterSettingsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            OxymeterSettings auxiliary = new OxymeterSettings();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), OxymeterSettings.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            settings = auxiliary;
        }
    }
}
