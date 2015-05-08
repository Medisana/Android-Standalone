package com.example.miguel.myapplication.util;

import java.util.ArrayList;

/**
 * Class for storage all constants used by different classes.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class ConstantsStorage {

    /*
     * Please note that you also have to change the application Token/Service in
	 * service.servercommunication.ExchangeTokenActivity.java if you switch the server.
	 *
	 * - test-cloud.vitadock.com is the test server to which you can
	 * register yourself as developer during registration. Please note that this
	 * is an own server with no connection to cloud.vitadock.com.
	 *
	 * - cloud.vitadock.com is the production server. After registration please
	 * contact us at cloud@vitadock.com to get upgraded to a developer account.
	 */

    // Production server authentication url: "cloud.vitadock.com/auth";
    // Test server authentication url: "test-cloud.vitadock.com/auth";
    public static final String EXTERNAL_AUTH_URL = "cloud.vitadock.com/auth";

    // Prodction server login url: "cloud.vitadock.com";
    // Test server login url: "test-cloud.vitadock.com";
    public static final String EXTERNAL_LOGIN_URL = "cloud.vitadock.com";

    // Production server data url: "cloud.vitadock.com/data";
    // Test server data url: "test-cloud.vitadock.com/data";
    public static final String EXTERNAL_DATA_URL = "cloud.vitadock.com/data";

    public static final String HTTPS_AUTH_URL = "https://" + EXTERNAL_AUTH_URL;
    public static final String HTTPS_LOGIN_URL = "https://"
            + EXTERNAL_LOGIN_URL;
    public static final String HTTPS_DATA_URL = "https://" + EXTERNAL_DATA_URL;

    // Application`s Token/Secret for production server.
    public static final String PRODUCTION_APPLICATION_TOKEN = "";
    public static final String PRODUCTION_APPLICATION_SECRET = "";

    // Application´s Token/Secret for test server.
    public static final String TEST_APPLICATION_TOKEN = "";
    public static final String TEST_APPLICATION_SECRET = "";

    // ArrayList with the module names
    public static final ArrayList<String> MODULE_NAMES = new ArrayList<String>(){{
            add("cardiodocks");
            add("glucodockglucoses");
            add("glucodockinsulins");
            add("glucodockmeals");
            add("targetscales");
            add("thermodocks");
            add("activitydocks");
            add("tracker/activity");
            add("tracker/sleep");
            add("");
            add("orders");
            add("mailings");
            add("cardiodocks/settings");
            add("glucodockglucoses/settings");
            add("glucodockinsulins/settings");
            add("glucodockmeals/settings");
            add("targetscales/settings");
            add("thermodocks/settings");
            add("user/settings");
            add("");add("");add("");add("");add("");add("");add("");add("");add("");add("");add("");
            add("tracker/phase");
            add("tracker/stats");
            add("oxymeters");
            add("oxymeters/settings");
            add("rt_pulse");
    }};

    public static final String CARDIODOCKS = "cardiodocks";
    public static final String GLUCODOCK_GLUCOSES = "glucodockglucoses";
    public static final String GLUCODOCK_INSULINS = "glucodockinsulins";
    public static final String GLUCODOCK_MEALS = "glucodockmeals";
    public static final String TARGET_SACLAES = "targetscales";
    public static final String THERMODOCK = "thermodocks";
    public static final String TRACKER_ACTIVITY = "tracker/activity";
    public static final String TRACKER_PHASE = "tracker/phase";
    public static final String TRACKER_SLEEP = "tracker/sleep";
    public static final String TRACKER_STATS = "tracker/stats";
    public static final String OXYMETERS = "oxymeters";

    public static final String OXYMETERS_SETTINGS = "oxymeters/settings";
    public static final String CARDIODOCK_SETTINGS = "cardiodocks/settings";
    public static final String TARGET_SCALES_SETTINGS = "targetscales/settings";
    public static final String GLUCODOCK_GLUCOSE_SETTINGS = "glucodockglucoses/settings";
    public static final String USER_SETTINGS = "user/settings";

    public static final ArrayList<String> SETTINGS_MODULES = new ArrayList<String>(){{
        add(CARDIODOCK_SETTINGS);
        add(GLUCODOCK_GLUCOSE_SETTINGS);
        add(TARGET_SCALES_SETTINGS);
        add(OXYMETERS_SETTINGS);
        add(USER_SETTINGS);
    }};

    public static final ArrayList<String> DATA_MODULES = new ArrayList<String>(){{
        add(CARDIODOCKS);
        add(GLUCODOCK_GLUCOSES);
        add(GLUCODOCK_INSULINS);
        add(GLUCODOCK_MEALS);
        add(TARGET_SACLAES);
        add(THERMODOCK);
        add(TRACKER_ACTIVITY);
        add(TRACKER_SLEEP);
        add(TRACKER_STATS);
        add(OXYMETERS);
    }};

    // Strings for the Activity Intents communication.

    public static final String DEVICE_TOKEN = "deviceToken";
    public static final String DEVICE_SECRET = "deviceSecret";
    public static final String OAUTH_TOKEN = "oauthToken";
    public static final String OAUTH_SECRET = "oauthSecret";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String ACCESS_SECRET = "accessSecret";
    public static final String VERIFIER_TOKEN = "verifierToken";
    public static final String MODULE_NAME = "moduleName";
    public static final String DATA_MODEL = "dataModel";
    public static final String TYPE_RETURNED = "typeReturned";
    public static final String SETTINGS_STRING = "settings";
    public static final String DATA_STRING = "data";

    // Codes for data type
    public static final int SETTINGS = 1;
    public static final int DATA = 2;

    public static final String AUTHORIZATION_STRING = "Authorization";

    /*
     * Note that you need to replace this value with your app`s Callback URI. Also you need to change
     * that in the AndroidManifest. You can see this value in your account, setting/applications,
     * manage own applications, show application(binocular icon).
     */
    public static final String CALL_BACK_URL = "vdodemo" + "://?";

    /*
     * It is the Agent Name, usually the application name + version. We use that in order to check
     * which app access, modify, create, login, etc.
     */
    public static final String AGENT_NAME = "TestApp" + "0.1";

    // Activity codes
    public static final int EXCHANGE_TOKEN_ACTIVITY_CODE = 1;
    public static final int ACQUIRE_UNAUTHORIZED_ACCESS_TOKEN_CODE = 2;
    public static final int WEB_BROWSER_CODE = 3;
    public static final int CHECK_VERIFIED_TOKEN_CODE = 4;
    public static final int SELECT_DATA_TYPE_ACTIVITY_CODE = 5;
    public static final int GET_DATA_ACTIVITY_CODE = 6;

    // Error codes (Starts at 2 because activity RESULT_FIRST_USER is 1)
    public static final int EXCEPTION_ERROR = 2;
    public static final int ACQUIRING_UNAUTHORIZED_ACCESS_ERROR = 3;

    // HTTP response codes from server.
    public static final int HTTP_RESPONSE_OK = 200;
    public static final int HTTP_RESPONSE_BAD_REQUEST = 400;
    public static final int HTTP_RESPONSE_UNAUTHORIZED = 401;
    public static final int HTTP_RESPONSE_METHOD_NOT_ALLOWED = 405;
    public static final int HTTP_RESPONSE_INTERNAL_SERVER_ERROR = 500;
}
