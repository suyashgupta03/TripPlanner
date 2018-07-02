package com.tripplanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class Supplier {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("offerIndices")
    private int[] offerIndices;

    public int[] getOfferIndices() {
        return offerIndices;
    }

}
