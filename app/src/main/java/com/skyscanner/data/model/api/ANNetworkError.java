package com.skyscanner.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by suyashg on 13/06/18.
 */

public class ANNetworkError {

    @Expose
    @SerializedName("timestamp")
    private long timestamp;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("error")
    private String error;

    @Expose
    @SerializedName("exception")
    private String exception;

    @Expose
    @SerializedName("errors")
    private List<CustomANError> errors;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("path")
    private String path;

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<CustomANError> getErrors() {
        return errors;
    }
}
