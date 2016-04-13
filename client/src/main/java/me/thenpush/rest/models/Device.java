package me.thenpush.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pappacena on 12/04/16.
 */
public class Device {
    @SerializedName("registration_id")
    private String registrationId;

    public Device(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
