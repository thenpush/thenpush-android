package me.thenpush.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pappacena on 12/04/16.
 */
public class Device {
    @SerializedName("request_id")
    private String requestId;

    public Device(String registrationId) {
        this.requestId = registrationId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
