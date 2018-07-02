package com.tripplanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class TicketClass {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
