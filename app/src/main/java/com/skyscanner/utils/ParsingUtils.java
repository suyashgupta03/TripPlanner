package com.skyscanner.utils;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.skyscanner.data.model.api.ANNetworkError;
import com.skyscanner.data.model.api.ApiError;

import static com.skyscanner.utils.AppConstants.ERROR_DEFAULT_CODE;
import static com.skyscanner.utils.AppConstants.ERROR_DEFAULT_MSG;
import static com.skyscanner.utils.AppConstants.ERROR_DEFAULT_STATUS;

/**
 * Created by suyashg on 09/06/18.
 */

public class ParsingUtils {

    private ParsingUtils() {
        // This class is not publicly instantiable
    }

    public static ApiError parseError(Throwable throwable) {
        ApiError defaultError = new ApiError(ERROR_DEFAULT_CODE, ERROR_DEFAULT_STATUS, ERROR_DEFAULT_MSG);
        if (throwable instanceof ANError) {
            String errorBody = ((ANError) throwable).getErrorBody();
            try {
                ANNetworkError anNetworkError = new Gson().fromJson(errorBody, ANNetworkError.class);
                ApiError apiError = new ApiError(anNetworkError.getStatus(), anNetworkError.getError(),
                        anNetworkError.getErrors().get(0).getDefaultMessage());
                return apiError;
            } catch (JsonIOException e) {
                return defaultError;
            } catch (JsonSyntaxException ex) {
                return defaultError;
            }
        }
        return defaultError;
    }
}
