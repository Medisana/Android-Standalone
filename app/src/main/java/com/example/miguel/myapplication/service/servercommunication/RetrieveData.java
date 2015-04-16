package com.example.miguel.myapplication.service.servercommunication;

import com.example.miguel.myapplication.activity.SelectDataTypeActivity;
import com.example.miguel.myapplication.dockclass.BaseModel;
import com.example.miguel.myapplication.dockclass.Cardiodock;
import com.example.miguel.myapplication.dockclass.GlucodockGlucose;
import com.example.miguel.myapplication.dockclass.GlucodockInsulin;
import com.example.miguel.myapplication.dockclass.GlucodockMeal;
import com.example.miguel.myapplication.dockclass.Oxymeter;
import com.example.miguel.myapplication.dockclass.TargetScale;
import com.example.miguel.myapplication.dockclass.Thermodock;
import com.example.miguel.myapplication.dockclass.TrackerActivity;
import com.example.miguel.myapplication.dockclass.TrackerPhase;
import com.example.miguel.myapplication.dockclass.TrackerSleep;
import com.example.miguel.myapplication.dockclass.TrackerStats;
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
import java.util.ArrayList;

/**
 * Class used for retrieve the data stored in server.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class RetrieveData {

    private static final int max = 100; // Max objects can be requested to server.
    private static final String defaultDateSince = "0";

    private ArrayList<BaseModel> data = new ArrayList<>();
    private int serverResponse = 2;
    private int start = - 1;
    private DefaultHttpClient httpClient;
    private String authorization;
    private HttpGet httpget;
    private HttpResponse response;
    private JSONArray jSONArray;
    private String moduleName;

    public RetrieveData() {
        data  = new ArrayList<>();
    }

    /**
     * Method who generate an ArrayList with all the required information. This method is recursive
     * because the max objects can be requested by the app is 100. So if its more than 100 in the
     * server app need to do various request.
     * This method will retrieve all the information(defaultDateSince == 0).
     * Note that this method cannot be called from main thread, so need be called from AsyncTask class.
     *
     * @param moduleID - Module ID which we want data.
     * @param newModuleName - Module name which we want data.
     * @return - ArrayList with module data.
     */
    public ArrayList<BaseModel> getData (int moduleID, String newModuleName) {
        return getData(moduleID, newModuleName, defaultDateSince);
    }

    /**
     * Method who generate an ArrayList with all the required information. This method is recursive
     * because the max objects can be requested by the app is 100. So if its more than 100 in the
     * server app need to do various request.
     * This method have a custom dateSince, so you can send here whatever date (in timestamp) you
     * want to get data from.
     * Note that this method cannot be called from main thread, so need be called from AsyncTask class.
     *
     * @param moduleID - Module ID which we want data.
     * @param newModuleName - Module name which we want data.
     * @return - ArrayList with module data.
     */
    public ArrayList<BaseModel> getData (int moduleID, String newModuleName, String dateSince) {
        moduleName = newModuleName;
        String url = AuthorizationBuilder.createSyncUrl(moduleID, start, max, dateSince);
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
            if (response.getStatusLine().getStatusCode() == ConstantsStorage.HTTP_RESPONSE_OK) {
                jSONArray = parseJSONString(response.getEntity().getContent());
                if (jSONArray != null) {
                    if (!jSONArray.toString().equals("[]")) {
                        addDataToArray();
                        // Call method again in order to check up if its data remained.
                        if (jSONArray.length() == max) {
                            start = start + max;
                            getData(moduleID, newModuleName, dateSince);
                        }
                    }
                }
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ConstantsStorage.EXCEPTION_ERROR;
            return null;
        } finally {
            start = 0;
        }
        return data;
    }

    public int getServerResponse() {
        return serverResponse;
    }

    /**
     * This method transform the JSON returned by the server into a JSONArray in order to work with it.
     *
     * @param inputStream - InputStream with the HTTP information returned by the server.
     * @return - JSONArray whit the information.
     */
    private JSONArray parseJSONString(InputStream inputStream) {
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
     * This method filter the module name that we want to get the information in order to call the different
     * methods for load the ArrayList.
     */
    private void addDataToArray() {
        switch (moduleName) {
            case ConstantsStorage.CARDIODOCKS:
                addCardiodocksDataToArray();
                break;
            case ConstantsStorage.GLUCODOCK_GLUCOSES:
                addGlucoseDataToArray();
                break;
            case ConstantsStorage.GLUCODOCK_INSULINS:
                addGlucoseInsulinDataToArray();
                break;
            case ConstantsStorage.GLUCODOCK_MEALS:
                addGlucoseMealsDataToArray();
                break;
            case ConstantsStorage.TARGET_SACLAES:
                addTargetScalesDataToArray();
                break;
            case ConstantsStorage.THERMODOCK:
                addThemodockDataToArray();
                break;
            case ConstantsStorage.TRACKER_ACTIVITY:
                addTrackerActivityDataToArray();
                break;
            case ConstantsStorage.TRACKER_PHASE:
                addTrackerPhaseDataToArray();
                break;
            case ConstantsStorage.TRACKER_SLEEP:
                addTrackerSleepDataToArray();
                break;
            case ConstantsStorage.TRACKER_STATS:
                addTrackerStatsDataToArray();
                break;
            case ConstantsStorage.OXYMETERS:
                addOxymeterDataToArray();
                break;
        }
    }

    /**
     * This method load the CardioDock´s information into the ArrayList.
     */
    private void addCardiodocksDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            Cardiodock auxiliary = new Cardiodock();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), Cardiodock.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the GlucoseDock Glucose´s information into the ArrayList.
     */
    private void addGlucoseDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            GlucodockGlucose auxiliary = new GlucodockGlucose();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), GlucodockGlucose.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the GlucoseDock Insulin´s information into the ArrayList.
     */
    private void addGlucoseInsulinDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            GlucodockInsulin auxiliary = new GlucodockInsulin();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), GlucodockInsulin.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the GlucoseDock Meals´s information into the ArrayList.
     */
    private void addGlucoseMealsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            GlucodockMeal auxiliary = new GlucodockMeal();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), GlucodockMeal.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the Target Scales´s information into the ArrayList.
     */
    private void addTargetScalesDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            TargetScale auxiliary = new TargetScale();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), TargetScale.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the Thermodock´s information into the ArrayList.
     */
    private void addThemodockDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            Thermodock auxiliary = new Thermodock();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), Thermodock.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the Tracker Activity´s information into the ArrayList.
     */
    private void addTrackerActivityDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            TrackerActivity auxiliary = new TrackerActivity();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), TrackerActivity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the Tracker Sleep´s information into the ArrayList.
     */
    private void addTrackerSleepDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            TrackerSleep auxiliary = new TrackerSleep();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), TrackerSleep.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the Tracker Stats´s information into the ArrayList.
     */
    private void addTrackerStatsDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            TrackerStats auxiliary = new TrackerStats();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), TrackerStats.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    /**
     * This method load the Oxymeter´s information into the ArrayList.
     */
    private void addOxymeterDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            Oxymeter auxiliary = new Oxymeter();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), Oxymeter.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }

    private void addTrackerPhaseDataToArray() {
        Gson gson = CustomGson.generateCustomGson();
        for (int i = 0; i < jSONArray.length(); i++) {
            TrackerPhase auxiliary = new TrackerPhase();
            try {
                auxiliary = gson.fromJson(jSONArray.getString(i), TrackerPhase.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.add(auxiliary);
        }
    }
}

