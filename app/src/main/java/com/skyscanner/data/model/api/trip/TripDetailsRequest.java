package com.skyscanner.data.model.api.trip;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class TripDetailsRequest implements Parcelable {

    @Expose
    @SerializedName("origin")
    private String origin;

    @Expose
    @SerializedName("destination")
    private String destination;

    @Expose
    @SerializedName("departureDate")
    private Long departureDate;

    @Expose
    @SerializedName("returnDate")
    private Long returnDate;

    @Expose
    @SerializedName("ticketCount")
    private int ticketCount;

    public TripDetailsRequest(String origin, String destination, Long departureDate, Long returnDate, int ticketCount) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.ticketCount = ticketCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.origin);
        dest.writeString(this.destination);
        dest.writeValue(this.departureDate);
        dest.writeValue(this.returnDate);
        dest.writeInt(this.ticketCount);
    }

    protected TripDetailsRequest(Parcel in) {
        this.origin = in.readString();
        this.destination = in.readString();
        this.departureDate = (Long) in.readValue(Long.class.getClassLoader());
        this.returnDate = (Long) in.readValue(Long.class.getClassLoader());
        this.ticketCount = in.readInt();
    }

    public static final Parcelable.Creator<TripDetailsRequest> CREATOR = new Parcelable.Creator<TripDetailsRequest>() {
        @Override
        public TripDetailsRequest createFromParcel(Parcel source) {
            return new TripDetailsRequest(source);
        }

        @Override
        public TripDetailsRequest[] newArray(int size) {
            return new TripDetailsRequest[size];
        }
    };

    public void setDepartureDate(Long departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(Long returnDate) {
        this.returnDate = returnDate;
    }

    public Long getDepartureDate() {
        return departureDate;
    }

    public Long getReturnDate() {
        return returnDate;
    }
}
