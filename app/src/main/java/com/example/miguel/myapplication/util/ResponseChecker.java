package com.example.miguel.myapplication.util;

import org.apache.http.HttpResponse;

/**
 * Helper class for filter response code from server.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class ResponseChecker {

    public static int checkServerResponse (HttpResponse response) {
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode == ConstantsStorage.HTTP_RESPONSE_OK) return ConstantsStorage.HTTP_RESPONSE_OK;
        else if (responseCode == ConstantsStorage.HTTP_RESPONSE_BAD_REQUEST) return ConstantsStorage.HTTP_RESPONSE_BAD_REQUEST;
        else if (responseCode == ConstantsStorage.HTTP_RESPONSE_UNAUTHORIZED) return ConstantsStorage.HTTP_RESPONSE_UNAUTHORIZED;
        else if (responseCode == ConstantsStorage.HTTP_RESPONSE_METHOD_NOT_ALLOWED) return ConstantsStorage.HTTP_RESPONSE_METHOD_NOT_ALLOWED;
        else return ConstantsStorage.HTTP_RESPONSE_INTERNAL_SERVER_ERROR;
    }
}
