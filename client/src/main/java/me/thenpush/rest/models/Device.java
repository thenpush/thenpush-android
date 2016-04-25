package me.thenpush.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by pappacena on 12/04/16.
 */
public class Device {
    @SerializedName("registration_id")
    private String registrationId;
    private HashMap data;
    private String[] tags;

    public Device(String registrationId, String[] tags, HashMap data) {
        this.registrationId = registrationId;
        this.tags = tags;
        this.data = data;
    }

    public Device(String registrationId) {
        this(registrationId, null, null);
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
