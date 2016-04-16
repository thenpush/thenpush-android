package me.thenpush.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pappacena on 16/04/16.
 */
public class PushReceipt {
    private String uuid;
    @SerializedName("registration_id")
    private String registrationId;

    public PushReceipt(String uuid, String registrationId) {
        this.registrationId = registrationId;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
