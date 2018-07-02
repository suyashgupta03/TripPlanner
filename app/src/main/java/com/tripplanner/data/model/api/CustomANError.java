package com.tripplanner.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 13/06/18.
 */

public class CustomANError {

    @Expose
    @SerializedName("codes")
    private String[] codes;

    @Expose
    @SerializedName("arguments")
    private String arguments;

    @Expose
    @SerializedName("defaultMessage")
    private String defaultMessage;

    @Expose
    @SerializedName("field")
    private String field;

    @Expose
    @SerializedName("rejectedValue")
    private String rejectedValue;

    @Expose
    @SerializedName("bindingFailure")
    private boolean bindingFailure;

    @Expose
    @SerializedName("code")
    private String code;

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
